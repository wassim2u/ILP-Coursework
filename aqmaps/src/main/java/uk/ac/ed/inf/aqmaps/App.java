package uk.ac.ed.inf.aqmaps;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import com.mapbox.geojson.Point;



public class App 
{
	private static int numberOfExpectedArguments = 7;
	private static LocalDate date;
	private static Point startPoint;
	private static int randomSeed;
	private static int portNumber; //accessed statically

	
	private App(String[] args) {
		  date = readDateFromArguments(args[0],args[1],args[2]);
	      startPoint = createStartingPoint(args[3],args[4]);
	      randomSeed = initialiseSeed(args[5]);
	      portNumber = readPortNumber(args[6]);
	      
	}
	
	/**
	 * This function checks the number of arguments and would exit the program if the number is not equal to the value defined at the 
	 * variable numberOfExpectedArguments.
	 */
	private static void checkNumberOfArguments(String[] args) throws IllegalArgumentException{
		try{
			if(args.length != App.numberOfExpectedArguments ) {	
				throw new IllegalArgumentException("Expected " + App.numberOfExpectedArguments + " arguments but received " + args.length);
			}
        }
		catch(Exception e) {
			System.out.println(e);
			exitArgumentError();
		}
		
	}
	
	
	private static LocalDate readDateFromArguments(String dayString, String monthString, String yearString) {
		int day = 0, month = 0, year = 0 ;
		try{
			day = Integer.parseInt(dayString);
			month = Integer.parseInt(monthString);
			year = Integer.parseInt(yearString);
		}
		catch(Exception e) {
			System.out.println("The dates should be in the format DD MM YY in integer numbers");
			exitArgumentError(); 
		}
			
		return LocalDate.of(year,month,day);

	}
	
	private static Point createStartingPoint (String latString, String longString) {
		double latitude =0.0 , longitude = 0.0;
		try {
			//TODO: CHECK STARTING POINT COORDINATES IF RIGHT OR WRONG. CHECK NOFLYZONES OR SOMETHING
			latitude= Double.parseDouble(latString);
			longitude = Double.parseDouble(longString);
		}
		catch(Exception e){
			System.out.println("Please pass latitude and longitude values in the correct format as numbers.");
			exitArgumentError();
		}
		return Point.fromLngLat(longitude,latitude);
		
	}
	
	private static int initialiseSeed(String seedString) {
    	int randomSeed = 0;
		try {
        	randomSeed = Integer.parseInt(seedString);
    	}
    	catch(Exception e){
    		System.out.println("Please pass the value for random seed as an integer number.");
			exitArgumentError();
    	}
		return randomSeed;
	}
	
	private static int readPortNumber(String portString) {
    	int port = 80;
		try {
	    	port = Integer.parseInt(portString);
    	}
    	catch(Exception e){
    		System.out.println("Please pass the value for port number as an integer number.");
			exitArgumentError();
    	}
		
    	return port;
	}
	

	//Ran when the arguments passed are not in the correct format as shown in the print statement below.
	private static void exitArgumentError() {
		System.out.println("To run the application, please input the following arguments:");
		System.out.println("java -jar aqmaps.jar DD MM YY latitude longitude random_seed port_number");
		System.exit(1); 
	}
	
	public static LocalDate getDate() {
		return date;
	}
	public static Point getStartPoint() {
		return startPoint;
	}
	public static int randomSeed() {
		return randomSeed;
	}
	
	public static int getPortNumber() {
		return portNumber;
	}
	

    public static void main(String[] args)
    {
    	//Initialisation Phase: Check the arguments are in the correct format and initialise the variables.
        checkNumberOfArguments(args); 
        App app = new App(args);
        
    	//Parse data from webserver that contains information on the location of the sensors we have to visit on that day
		Sensor[] listOfSensors = JsonParser.parseAirQualityData(App.getPortNumber(), app.getDate());

    	//Initialise a new Drone object, by passing in the starting location and the list of sensors to visit.
//    	Drone drone = new Drone(app.startPoint, listOfSensors, no);    	
    	
    	
    	
    	//Testing
		
		RoutePlanner t = new RoutePlanner(App.getStartPoint(),listOfSensors);
    	t.twoOpt();
    	System.out.println(Arrays.toString(t.generateRoute().toArray()));
    	System.out.println(t.calculateTourCost(t.gettourIndex()));
//    	//Parse Information from the webserver to the respective class objects from Json/GeoJson Strings.
		What3WordsDetails w3waddress = JsonParser.parseWhat3WordsDetails(App.getPortNumber(), "slips/mass/baking"); 
		NoFlyZone offLimitZones = JsonParser.parseNoFlyZones(App.getPortNumber()); 
    	Drone drone = new Drone(App.getStartPoint(), listOfSensors, offLimitZones);    	
    	var path = drone.returnCompletePath();
    	for (Point p : path) {
    		System.out.println("Lng " + p.longitude() +  " ;Lat " + p.latitude());
    	}
    	System.out.println(drone.getNumberOfMoves());
    	
    	
    
       
    }
    
}
