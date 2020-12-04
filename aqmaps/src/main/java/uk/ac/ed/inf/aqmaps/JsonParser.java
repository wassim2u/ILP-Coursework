package uk.ac.ed.inf.aqmaps;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


//The following is used to access web server contents
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

//Exceptions Libraries
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.ConnectException;

/**
 * Utility class that handles parsing data from the webserver from Json into appropriate Object types
 */
public final class JsonParser {
	
	/** One HttpClient is created, shared between all HttpRequests.*/
	private static final HttpClient client = HttpClient.newHttpClient();
	/** Current Server name is set at the beginning. Currently called "localhost". */
	private static String serverName = "localhost";
	/** The HTTP Response code that indicates that the server could not find the page requested. */
	private static final int NOTFOUNDSTATUS = 404;
	
	/**
	 * Helper method that checks the HTTP Status code returned by the HTTP response. 
	 * @param status The response code.
	 * @return true if status code is 404. 
	 */
	private static boolean checkResponseNotFound(int status) {
		return status == NOTFOUNDSTATUS;
	}
	
	/**
	 * Helper function that returns the body content of a JSON file formatted as a JSON String. 
	 * @param portNumber The port number, should be equivalent to the port number that the server is in in order to have a successful connection.
	 * @param path The file path on the webserver to retrieve the required body content.
	 * @return Returns the Body content found from the JSON File.
	 */
	private static String getBodyContent(int portNumber, String path) {
		var urlString = "http://" + serverName + ":" +portNumber + "/" + path;
		HttpResponse<String> response = null;
		try {
			// Create a new HttpClient with default settings.
			var request = HttpRequest.newBuilder()
			.uri(URI.create(urlString))
			.build();
			response = client.send(request, BodyHandlers.ofString());
			
			//Catch Null errors in the off chance that it happens; Exit the system if null and print source of error
			if (response == null){
				throw new NullPointerException("Response recieved is of type null");
			}
			
		}
		//Executed when the argument passed is not correct
		catch (IllegalArgumentException e) {
			System.out.println("URL passed is not syntactically correct!");
			System.out.println("Please check the urlString argument that is passed when calling URI.create() method.");
			e.printStackTrace(); 
			System.exit(1); //Exit the program, indicating that it exited due to an error.
		}
		//Executed when web server is not running or not running on the correct port number.
		catch (ConnectException e) {
			System.out.println("Fatal error: Unable to connect to " +
					serverName + " at port " + portNumber + ".");
			System.exit(1); //Exit the program, indicating that it exited due to an error.
		}
		//Catch other Errors that may be thrown by HttpClient object sending request
		catch(IOException|InterruptedException|NullPointerException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
		//TODO: Check what you should do in case Status code is 404. Check if there are any status codes you need. Should you throw errors?
		if ( checkResponseNotFound(response.statusCode()) ) {
				System.out.println("Status: 404 [Not Found]");
				System.out.println("File Not Found. Check the URL path for any spelling mistakes.");
				System.out.println("URL passed: " + urlString);
				System.exit(0);		
		}
			
		return response.body();
	}
	
	/**
	 * Parses JSON content related to the location of a sensor and converts it to a new object of type What3WordDetails
	 * @param portNumber The port number which the server is in.
	 * @param what3WordsLocation The String location of the sensor, with each word separated by a dot.
	 * @return What3WordsDetails Object with attributes conveying information about the location of a sensor.
	 */
	public static What3WordsDetails parseWhat3WordsDetails(int portNumber,String what3WordsLocation) {
		var jsonFileName = "details.json";
		var folderName = "words";
		var w3wPath = changeLocationFormatToFilePath(what3WordsLocation);
		var jsonFilePath = folderName + "/" + w3wPath + "/" + jsonFileName;
		String body = getBodyContent(portNumber, jsonFilePath);
		var details = new Gson().fromJson(body, What3WordsDetails.class);
		return details;
	}
	
	/**
	 * Removes the dots in the String to a forward slash to represent the format of a file path for our webserver
	 * @param w3w The what3Words location in a dot format. 
	 * @return A New String with the format of a file path (with forward slashes).
	 */
	private static String changeLocationFormatToFilePath(String w3w) {
		 return w3w.replace('.', '/');
	}
	
	/**
	 * Parses JSON content with air quality readings of different sensors and their location and converts them to Sensor objects.
	 * @param portNumber The port number which the server is in.
	 * @return An array of Sensors
	 */
	public static Sensor[] parseAirQualityData(int portNumber) {
		var jsonFileName = "air-quality-data.json";
		var folderName = "maps";
		
		var jsonFilePath = folderName + "/" + App.getYearString() + "/" + App.getMonthString() + "/" + App.getDayString() + "/" + jsonFileName;
		String body =  getBodyContent(portNumber, jsonFilePath);
		Type listType = new TypeToken<Sensor[]>() {}.getType();
				// Use the ”fromJson(String, Type)” method
				Sensor[] sensorsData =new Gson().fromJson(body, listType);
				
		return sensorsData;
	}
	/**
	 * Parses JSON content related to Off Limit Zones, zones inside the confinement areas that the drone should not fly over .
	 * @param portNumber The port number which the server is in.
	 * @return A new object of type NoFlyZone
	 */
	public static NoFlyZone parseNoFlyZones(int portNumber)  {
		var jsonFileName = "no-fly-zones.geojson";
		var folderName = "buildings";
		var jsonFilePath = folderName + "/" + jsonFileName;
		String body =  getBodyContent(portNumber, jsonFilePath);
		return new NoFlyZone(body);
	}	
	
	
		
	
}
