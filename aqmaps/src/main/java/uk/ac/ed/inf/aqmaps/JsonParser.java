package uk.ac.ed.inf.aqmaps;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDate;
import java.util.ArrayList;
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
			
		System.out.println(response.body());
		String body = response.body();
		var p = new Gson().fromJson(body, What3WordsDetails.class);
		Type listType =
				new TypeToken<ArrayList<What3WordsDetails>>(){}.getType();
		// Use the ”fromJson(String, Type)” method
		ArrayList<What3WordsDetails> studentList = new Gson().fromJson(body, listType);
		System.out.println(studentList.get(0));
		return response.body();
	}
	
	/*
	 * Helper method that checks the HTTP Status code returned by the HTTP response. 
	 * @returns True if status code is 404. 
	 */
	
	private static boolean checkResponseNotFound(int status) {
		return status == NOTFOUNDSTATUS;
	}
	
	public static String parseWhat3WordsDetails(int portNumber,String what3WordsLocation) throws IOException, InterruptedException {
		var jsonFileName = "details.json";
		var folderName = "words";
		var jsonFilePath = folderName + "/" + what3WordsLocation + "/" + jsonFileName;
		return getBodyContent(portNumber, jsonFilePath);
	}
	
	public static String parseAirQualityData(int portNumber, LocalDate date) throws IOException, InterruptedException {
		var jsonFileName = "air-quality-data.json";
		var folderName = "maps";
		var jsonFilePath = folderName + "/" + date.getYear() + "/" + date.getMonth() + "/" + date.getDayOfMonth() + "/" + jsonFileName;
		return getBodyContent(portNumber, jsonFilePath);
	}
	
	public static String parseNoFlyZones(int portNumber)  {
		var jsonFileName = "no-fly-zones.geojson";
		var folderName = "buildings";
		var jsonFilePath = folderName + "/" + jsonFileName;
		return getBodyContent(portNumber, jsonFilePath);
	}	
	
	
}
