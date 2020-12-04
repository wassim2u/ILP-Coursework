package uk.ac.ed.inf.aqmaps;


import java.time.LocalDate;
import java.util.List;

import com.mapbox.geojson.Point;



/**
 * Represents the application interface, and is the starting point of the program. 
 * The class is mainly used to check the argument values passed and store them for the duration of the program. 
 */
public class App 
{
	
	/**
     * The number of arguments expected when starting the application from command line. Needs to be changed if there becomes different requirements
	 * or if there is a need for flexibility. 
	 */
	private static int numberOfExpectedArguments = 7;
	/** The date given in the arguments. Used later for accessing the list of sensors that need to be visited on a given date. */
	private static LocalDate date;
	 /** The starting point of the drone given in the arguments. */
	private static Point startPoint;
	 /** The random seed number given in the arguments. Current implementation does not make use of it as the program returns the same results. */
	private static int randomSeed;
	 /** The port number given in the arguments. Needs to match with the webserver's port number in order to connect and parse information from it successfully. */
	private static int portNumber;
	
	
	
	private App(String[] args) {
		  date = readDateFromArguments(args[0],args[1],args[2]);
	      startPoint = createStartingPoint(args[3],args[4]);
	      randomSeed = initialiseSeed(args[5]);
	      portNumber = readPortNumber(args[6]);
	      
	}
	
	/**
	 * This function checks the number of arguments and would exit the program if the number is not equal to the value defined at the 
	 * variable numberOfExpectedArguments.
	 * @param args The arguments passed in the terminal
	 * @throws IllegalArgumentException if the number of arguments do not match up with the required number set in numberOfExpectedArguments.
	 */
	private static void checkNumOfArguments(String[] args) throws IllegalArgumentException{
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
	
	/**
	 * Reads the date values from the arguments in the format DD MM YY.
	 * @param dayString The day passed in the argument
	 * @param monthString The month passed in the argument
	 * @param yearString The year passed in the argument
	 * @throws NumberFormatException if the program fails to parse from String to Integer if the string values given are not numbers (eg. Will accept 06, but not June). 
	 * @return Returns LocalDate containing the three parameters passed.
	 */
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
	/**
	 * Reads the longitude and latitude values passed and creates a new Point to encapsulate both values.
	 * @param latString The latitude passed in the arguments
	 * @param longString The longitude passed in the arguments
	 * @throws IllegalArgumentException if the values given were invalid (eg. Outside the confinement area). 
	 * @return Returns Point object containing the two parameters passed of longitude and latitude.
	 */
	private static Point createStartingPoint (String latString, String longString) {
		double latitude =0.0 , longitude = 0.0;
		try {
			latitude= Double.parseDouble(latString);
			longitude = Double.parseDouble(longString);
			if (! NoFlyZone.checkWithinBoundary(Point.fromLngLat(longitude, latitude))) {
				throw new IllegalArgumentException("Invalid Starting Point - Outside the drone confinement area");
			}
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Please pass latitude and longitude values in the correct format as numbers.");
			exitArgumentError();
		}
		return Point.fromLngLat(longitude,latitude);
		
	}
	/**
	 * Reads the random seed value passed in the arguments to stores it in the program.
	 * @param seedString the random seed value
	 * @throws NumberFormatException if the value given were invalid (Not an integer number).
	 * @return value of type int representing the random seed.
	 */
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
	/**
	 * Reads the port number value passed in the arguments to parse it into integer and store it in the program. 
	 * @param portString the port number passed in the arguments. 
	 * @throws NumberFormatException if the value given were invalid (Not an integer number).
	 * @return value of type int representing the port number.
	 */
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
	

	/**
	 * Ran when the arguments passed are not in the correct format. Notifies the user of the format to input the arguments.
	 */
	private static void exitArgumentError() {
		System.out.println("To run the application, please input the following arguments:");
		System.out.println("java -jar aqmaps.jar DD MM YY latitude longitude random_seed port_number");
		System.exit(1); 
	}
	
	public static String getYearString() {
		return Integer.toString(date.getYear());
	}
	
	public static String getMonthString() {
		var monthString = Integer.toString(date.getMonthValue());

		//Ensure that if a number is less than 10, we have a leading zero before it to match up the format of dates in the webserver.
		if (isSingleDigit(date.getMonthValue())) {
			monthString = addLeadingZeroToString(monthString);
		}
		return monthString;
	}
	
	public static String getDayString() {
		var dayString = Integer.toString(date.getDayOfMonth());

		if (isSingleDigit(date.getDayOfMonth())) {
			dayString = addLeadingZeroToString(dayString);
		}
		
		return dayString;
	}
	
	/**
	 * Add one leading zero if the number is less than 10. 
	 * Used for parsing air quality data. This is to match the String date formats in the webserver to access the json file successfully in the maps folder.
	 * @param numString A number in String type.
	 * @return return the new String with a leading zero if it is a single digit. Otherwise, it returns the same String value passed in argument.
	 */
	private static String addLeadingZeroToString(String numString) {
		if (Integer.parseInt(numString) < 10){
			numString = "0" + numString;
		}
		return numString;
	}
	

	/**
	 * Check if it is a single digit. Used for parsing air quality data by matching up the format in the webserver to access the json file needed using dates successfully.
	 * @param number A number of type int.
	 * @return True if it is a single digit. Otherwise, return false.
	 */
	private static boolean isSingleDigit(int number) {
		return number < 10;
	}
	
	public static LocalDate getDate() {
		return date;
	}
	
	
	
	public static Point getStartPoint() {
		return startPoint;
	}
	public static int getRandomSeed() {
		return randomSeed;
	}
	
	public static int getPortNumber() {
		return portNumber;
	}
	
	
	
	

    public static void main(String[] args)
    {
    	//Initialisation Phase: Check the arguments are in the correct format and initialise the variables.
    	checkNumOfArguments(args); 
    	date = readDateFromArguments(args[0],args[1],args[2]);
	    startPoint = createStartingPoint(args[3],args[4]);
	    randomSeed = initialiseSeed(args[5]);
	    portNumber = readPortNumber(args[6]);
        System.out.println("Received arguments successfully.");
        
    	//Parse data from webserver that contains information on the location of the sensors we have to visit on that day
		Sensor[] listOfSensors = JsonParser.parseAirQualityData(App.getPortNumber());
    	//Parse data from webserver that contains information on the off limit areas the drone should avoid .
		NoFlyZone restrictedAreas = JsonParser.parseNoFlyZones(App.getPortNumber()); 

        System.out.println("...........");

		//Create a new Drone object
        Drone drone = new Drone(App.getStartPoint(), listOfSensors, restrictedAreas);
        //Set whether to log the flight paths for this day. 
    	boolean recordFlight = true;
    	//Return the list of path
    	List<Point> path = drone.returnCompletePath(recordFlight);
    	
    	
    	//Create GeoJSON readings output files, which is a map of the path of the drone with markers indicating location of sensors.
    	GeoJsonDeserialiser.createGeoJSONMapReadings(path, listOfSensors);

    	
  
    	
    
       
    }
    
}
