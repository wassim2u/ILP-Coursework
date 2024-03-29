package uk.ac.ed.inf.heatmap;

import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;

public class App 
{
	// Grid dimension size, default is 10 by 10 (as assumed). You can change the GRID_SIZE here.
	private static final int GRID_SIZE = 10; 
	
	//Grid containing predictions made by the researchers to the heat map application
	private static int[][] predictions = new int[GRID_SIZE][GRID_SIZE]; 
	
	//The minimum and maximum limit for the values in the predictions grid.
	private static final int MIN_LIMIT = 0;
	private static final int MAX_LIMIT = 255;
	
	/*
	 * The numbers describe the range of values that the latitudes and longitudes could take
	 * The drones must stay strictly within these limits. 
	 * A drone at one of the limit locations or outside of the ranges will be considered as malfunctioning.
	 */
	private static final double NORTHERN_BOUNDARY = 55.946233; //Currently latitude for Forrest Hill and KFC
	private static final double SOUTHERN_BOUNDARY = 55.942617; //Currently latitude for Top of the Meadows and Buccleuch St. Bus Stop
	private static final double WESTERN_BOUNDARY = -3.192473;  //Currently Longitude for Forrest Hill and Top of the Meadows
	private static final double EASTERN_BOUNDARY = -3.184319;  //Currently Longitude for KFC and Buccleuch St. Bus Stop
	private static final double RANGE_LONGITUDE = Math.abs(EASTERN_BOUNDARY - WESTERN_BOUNDARY); //width of the area enclosed by the boundaries
	private static final double RANGE_LATITUDE =  Math.abs(NORTHERN_BOUNDARY - SOUTHERN_BOUNDARY);	//height of the area enclosed by the boundaries
	
	//Map colour names to Hex values
	private static final Map<String,String> colourNameToHex = Map.of(
			"Green", 		"#00ff00",
			"Medium Green", "#40ff00",
			"Light Green", 	"#80ff00",
			"Lime Green", 	"#c0ff00",
			"Gold", 		"#ffc000",
			"Orange", 		"#ff8000",
			"Red-Orange", 	"#ff4000",
			"Red", 			"#ff0000"
	);
	
	//Specified fill_opacitiy property value to add to all the features
	private static final String FILL_OPACITY = "0.75"; 
	
	
	//Take the numbers in the text file passed as the argument and store them in a (GRID_SIZE by GRID_SIZE) String array  
	private static void initialiseGrid(String filename) {
		try {
			File predictionsFile = new File(filename);
	    	Scanner reader = new Scanner(predictionsFile);
	    	int row = 0 ; int col = 0;
	    	for (row = 0; row < predictions.length; row++) {
	    		String[] line = reader.nextLine().replace(" ","").split(","); //Remove any white space and split the string into a String array of numbers after removing the comma
	    		
	    		//Check whether the number of columns is equal to the specified number GRID_SIZE
	    		int numberOfColumns = line.length;
	    		if (checkGridSize(numberOfColumns,reader)) {
	    			reader.close();
	    			throw new Exception("Grid size is not " + GRID_SIZE + " by " + GRID_SIZE);
	    		}
	    		for (col = 0; col<numberOfColumns; col++) {
	    			predictions[row][col] = Integer.parseInt(line[col]); //Parse each number in the array from String to int type
	    			//Check whether the value is not within MIN_LIMIT and MAX_LIMIT, and throw an error if outside the range. 
	    			if(checkValue(predictions[row][col])) { 
	    		    	reader.close();
	    				throw new Exception("The value \"" + line[col] + "\" in (row " + row + ", column " + col +") is not in the range 0 and 255.\n");
	    			}
	    		}
	    	}
    		//As an extra test at the end of the for loop after we traverse all the lines in the file, check whether the number of rows is the specified GRID_SIZE.
	    	if (checkGridSize(row,reader)) {
    			reader.close();
    			throw new Exception("Grid size is not " + GRID_SIZE + " by " + GRID_SIZE);
    		}
	    	
	    	reader.close();
		}
		catch(Exception e){
   	      	e.printStackTrace();
   	      	System.out.println("Please provide a "+ GRID_SIZE + " by "+ GRID_SIZE + " grid of integer numbers in a text file with the correct range of values 0 <= x <= 255.");
   	      	System.exit(-1); //Unsuccessful termination of program
    	}	
	}
	
	//If one of the values in predictions is greater than MAX_VALUE or less than MIN_VALUE, return True. Otherwise, return False.
	private static boolean checkValue(int val) {
		return (val < MIN_LIMIT || val > MAX_LIMIT);
	}
	
	/*
	 * The implementation assumes that the dimensions of the file text to be inputed in the argument should be 10 by 10.
	 * This method is used twice when initialising Grid (predictions array), one for checking the row size and another for column size.
	 */
	private static boolean checkGridSize(int size, Scanner reader) {
		return size!=GRID_SIZE;
	}
	
	/*
	 * The points created will be such that they are all between boundaries and with adjacent points being evenly spaced,
	 * except for the last row or column of points. We must account for a point going over or under the specified end of boundary (east or south)
	 * which may be caused by rounding errors due to inexactness of computers to represent real numbers.
	 */
	private static List<List<Point>> createPoints() {
		List<List<Point>> outputCoords = new ArrayList<List<Point>>(GRID_SIZE+1); //initialise list containing the Point objects to be added
		
		//Divide the space between the boundaries into 10 rows and 10 columns, in order to be able to create evenly spaced points when we increment or decrement the value
		double incrementLong = RANGE_LONGITUDE/ (double) GRID_SIZE; 
		double decrementLatitude = RANGE_LATITUDE / (double) GRID_SIZE;
		
		//Start from the top left intersection of the two longitude and latitude lines
        double currentLong = WESTERN_BOUNDARY;  
        double currentLat = NORTHERN_BOUNDARY;
        
        int row =0 ; int col =0;
        while (row<GRID_SIZE+1) {
        	outputCoords.add(new ArrayList<Point>(GRID_SIZE+1));
        	//At the southern boundary, when we reach the last row of values that were given in the prediction file,
        	//assign the variable currentLat to the latitude degree specified for the southern boundary limit.
        	//Recall this is to account for any rounding errors that might happen
        	if (row==GRID_SIZE) {
        		currentLat = SOUTHERN_BOUNDARY;
        	}
        	while(col<GRID_SIZE+1) {
        		//Similar to the reason above, create a point with the value specified as the Eastern Boundary when we reach the last column
        		if (col==GRID_SIZE) {
            		outputCoords.get(row).add(col, Point.fromLngLat(EASTERN_BOUNDARY, currentLat));
            		col++;
        		}
        		else {
        			outputCoords.get(row).add(col, Point.fromLngLat(currentLong,currentLat));
        			currentLong += incrementLong;
        			col++;
        		}
        	}
        	currentLat -= decrementLatitude; 
			currentLong = WESTERN_BOUNDARY;
			row++; 
			col=0;
        }
		
	return outputCoords;
	}

	/*
	 * returns a Json String with Features from a 2D list of points
	 */
	private static String createGEOJsonPolygonsfromPoints(List<List<Point>> pointCoords) {
		List<Feature> listOfFeatures = new ArrayList<>();
		for(int row=1;row<pointCoords.size();row++) { 
			for(int col=1;col<pointCoords.get(row).size();col++) {
				Point upperLeftPoint = pointCoords.get(row-1).get(col-1);
				Point upperRightPoint = pointCoords.get(row-1).get(col);
				Point bottomLeftPoint = pointCoords.get(row).get(col-1);
				Point bottomRightPoint = pointCoords.get(row).get(col);
				
				//Create polygon from List of points
				Polygon polygon = createPolygon(upperLeftPoint,upperRightPoint,bottomRightPoint,bottomLeftPoint);
				
				//Turn polygon to feature
				Feature feat = Feature.fromGeometry((Geometry) polygon);
				
				//Add attributes to the feature created
	        	addAppropriateProperties(row-1,col-1,feat);
	        	
	        	//Add it to the list of features
	        	listOfFeatures.add(feat);
			}
		}
		
		//Turn the list of Features with the added properties to Json String
		return (FeatureCollection.fromFeatures(listOfFeatures)).toJson(); 
	}
	//Helper function to create a Polygon from List of points. The order passed in the parameters is important.
	private static Polygon createPolygon(Point upperLeft, Point upperRight, Point bottomRight, Point bottomLeft) {
		//To create a polygon, the first and last point in the list should be the same enclosing the linear ring.
		//So, first point is reiterated at the end to close our polygon.
		List<Point> polygonPointCoords =
				new ArrayList<>(List.of(upperLeft,upperRight,bottomRight,bottomLeft,upperLeft));
		
		return Polygon.fromLngLats(List.of(polygonPointCoords));
	}
	//Helper function to add suitable attributes
	private static void addAppropriateProperties(int row, int col, Feature feat) {
		String colourVal = mapValueToColour(predictions[row][col]);
		feat.addStringProperty("fill-opacity", FILL_OPACITY);
		feat.addStringProperty("rgb-string", colourVal);
		feat.addStringProperty("fill", colourVal);
	}
	
	//Helper function to assign Hex value in order to add a colour attribute
	private static String mapValueToColour(int val) {
		if (val>=0 && val<32) {
			return colourNameToHex.get("Green"); 
		}
		else if(val>=32 && val<64) {
			return colourNameToHex.get("Medium Green"); 
		}
		else if(val>=64 && val<96) {
			return colourNameToHex.get("Light Green"); 
		}
		else if(val>=96 && val<128) {
			return colourNameToHex.get("Lime Green"); 
		}
		else if(val>=128 && val<160) {
			return colourNameToHex.get("Gold"); 
		}
		else if(val>=160 && val<192) {
			return colourNameToHex.get("Orange"); 
		}
		else if(val>=192 && val<224) {
			return colourNameToHex.get("Red-Orange"); 
		}
		else { //val>=224 && val<256
			return colourNameToHex.get("Red"); 
		}
	}
	
	private static void createNewFile(String JsonString, String filename) throws IOException {
		FileWriter myWriter = new FileWriter(filename);
    	myWriter.write(JsonString);
    	myWriter.close();
    	System.out.println("Successfully created " + filename + " file.");
	}
	
    public static void main( String[] args )
    {
        if (args.length<1) {
        	System.out.println("Please pass a file as an argument in the following format:");
        	System.out.println("java -jar heatmap.jar /path/to/predictions.txt");
        }
        else {
        	//initialises the static predictions array using the text file provided
            initialiseGrid(args[0]); 
            
            //Create points confined within the area of the boundaries
        	List<List<Point>> pointCoords= createPoints();
        	
        	//Convert the points to GeoJson String with Polygons having appropriate attributes
        	String geoJson = createGEOJsonPolygonsfromPoints(pointCoords);
        	try {
				createNewFile(geoJson,"heatmap.geojson");
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1); //Unsuccessful termination of program
			}
        	System.exit(0); //Successful termination of program. A heatmap.geojson file should be created at this point.

        }
    }
}
