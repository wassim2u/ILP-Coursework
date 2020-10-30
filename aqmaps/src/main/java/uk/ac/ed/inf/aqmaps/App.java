package uk.ac.ed.inf.aqmaps;

import java.time.LocalDate;
import com.mapbox.geojson.Point;



public class App 
{
	private static int numberOfExpectedArguments = 7;

	
	/**
	 * 
	 */
	private static void checkNumberOfArguments(String[] args) throws IllegalArgumentException{
		if(args.length != App.numberOfExpectedArguments ) {	
            throw new IllegalArgumentException("Expected " + App.numberOfExpectedArguments + " arguments but recieved " + args.length);
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
    	try {
        	checkNumberOfArguments(args);
        	LocalDate date = readDateFromArguments(args[0],args[1],args[2]);
        	Point startPoint = createStartingPoint(args[3],args[4]);
        	int randomSeed = initialiseSeed(args[5]);
    		int portNumber = readPortNumber(args[6]);
    		
    	}
    	catch(Exception e) {
			e.printStackTrace();
			System.out.println("To run the application, please input the following arguments:");
			System.out.println("java -jar aqmaps.jar DD MM YY latitude longitude random_seed port_number");
			System.exit(-1); 
		}
    
    }
}
