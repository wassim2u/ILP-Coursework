package uk.ac.ed.inf.aqmaps;

import java.io.IOException;
import java.time.LocalDate;
import com.mapbox.geojson.Point;



public class App 
{
	private static int numberOfExpectedArguments = 7;
	
	/**
	 * This function checks the number of arguments 
	 */
	private static void checkNumberOfArguments(String[] args) throws IllegalArgumentException{
		if(args.length != App.numberOfExpectedArguments ) {	
            throw new IllegalArgumentException("Expected " + App.numberOfExpectedArguments + " arguments but received " + args.length);
            }
		}
	
	
	private static LocalDate readDateFromArguments(String dayString, String monthString, String yearString) {
		int day = Integer.parseInt(dayString);
		int month = Integer.parseInt(monthString);
		int year = Integer.parseInt(yearString);
		return LocalDate.of(year,month,day);
	}
	
	private static Point createStartingPoint (String latString, String longString) {
		int latitude= Integer.parseInt(latString);
		int longitude = Integer.parseInt(longString);
		return Point.fromLngLat(longitude,latitude);
	}
	
	private static int initialiseSeed(String seedString) {
    	int randomSeed = Integer.parseInt(seedString);
    	return randomSeed;
	}
	
	private static int readPortNumber(String portString) {
    	int port = Integer.parseInt(portString);
    	return port;
	}
	

    public static void main(String[] args)
    {
    	LocalDate date; Point startPoint; int randomSeed; int portNumber; //Define these local variables
    	try {
    		//Read arguments passed from the terminal and initialise the variables defined earlier
        	checkNumberOfArguments(args);
        	date = readDateFromArguments(args[0],args[1],args[2]);
        	startPoint = createStartingPoint(args[3],args[4]);
        	randomSeed = initialiseSeed(args[5]);
    		portNumber = readPortNumber(args[6]);
        	
    		
    		var p = JsonParser.parseWhat3WordsDetails(portNumber, "slips/mass/baking");
    		
    	}
    	catch(Exception e) {
			e.printStackTrace();
			System.out.println("To run the application, please input the following arguments:");
			System.out.println("java -jar aqmaps.jar DD MM YY latitude longitude random_seed port_number");
			System.exit(1); 

    	}
    }
    
}
