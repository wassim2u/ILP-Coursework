package uk.ac.ed.inf.aqmaps;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mapbox.geojson.FeatureCollection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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


public class JsonParser {
	
	//One HttpClient is created, shared between all HttpRequests.
	private static final HttpClient client = HttpClient.newHttpClient();
	//Current Server 
	private static String serverName = "localhost";
	
	private static final int NOTFOUNDSTATUS = 404;
	
	/*
	 * Helper method that checks the HTTP Status code returned by the HTTP response. 
	 * @returns True if status code is 404. 
	 */
	private static boolean checkResponseNotFound(int status) {
		return status == NOTFOUNDSTATUS;
	}
	
	//Helper function that returns the body content formatted as a JSON String. 
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
	
	
	public static What3WordsDetails parseWhat3WordsDetails(int portNumber,String what3WordsLocation) {
		var jsonFileName = "details.json";
		var folderName = "words";
		var jsonFilePath = folderName + "/" + what3WordsLocation + "/" + jsonFileName;
		String body = getBodyContent(portNumber, jsonFilePath);
		var details = new Gson().fromJson(body, What3WordsDetails.class);
		return details;
	}
	
	public static ArrayList<Sensor> parseAirQualityData(int portNumber, LocalDate date) {
		var jsonFileName = "air-quality-data.json";
		var folderName = "maps";
		var monthString = Integer.toString(date.getMonthValue());
		var dayString = Integer.toString(date.getDayOfMonth());
		//Ensure that if a number is less than 10, we have a leading zero before it to match up the format of dates in the webserver.
		if (isSingleDigit(date.getMonthValue())) {
			monthString = addLeadingZero(monthString);
		}
		if (isSingleDigit(date.getDayOfMonth())) {
			dayString = addLeadingZero(dayString);
		}
		
		var jsonFilePath = folderName + "/" + date.getYear() + "/" + monthString + "/" + dayString + "/" + jsonFileName;
		String body =  getBodyContent(portNumber, jsonFilePath);
		Type listType = new TypeToken<List<Sensor>>() {}.getType();
				// Use the ”fromJson(String, Type)” method
				ArrayList<Sensor> sensorsData =new Gson().fromJson(body, listType);
				
		return sensorsData;
	}
	//
	public static NoFlyZone parseNoFlyZones(int portNumber)  {
		var jsonFileName = "no-fly-zones.geojson";
		var folderName = "buildings";
		var jsonFilePath = folderName + "/" + jsonFileName;
		String body =  getBodyContent(portNumber, jsonFilePath);
		return new NoFlyZone(body);
	}	
	
	/*
	 * Add one leading zero if the number is less than 10. 
	 * Used for parsing air quality data. This is to match the String date formats in the webserver to access the json file successfully in the maps folder.
	 */
	private static String addLeadingZero(String numString) {
		if (Integer.parseInt(numString) < 10){
			numString = "0" + numString;
		}
		return numString;
	}
	
	//Check if it is a single digit. Used for parsing air quality data by matching up the format to access the json file needed using dates successfully.
	private static boolean isSingleDigit(int number) {
		return number < 10;
	}
		
	
}
