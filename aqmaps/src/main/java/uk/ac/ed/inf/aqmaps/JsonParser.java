package uk.ac.ed.inf.aqmaps;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class JsonParser {
	

	public String connectToPort(int portNumber) throws IOException, InterruptedException {
		
		// Create a new HttpClient with default settings.
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("http://localhost:" + portNumber + "/maps"))
		.build();
		// The response object is of class HttpResponse<String>
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.getClass());
		System.out.println(response.statusCode());
		System.out.println(response.body());
		String body = response.body();
		
		
		return response.body();
		
		

	}
	
	
	
	
}
