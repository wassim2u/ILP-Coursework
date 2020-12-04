package uk.ac.ed.inf.aqmaps;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;

/**
 * Utility class used to generate GeoJson strings which contain methods to make Features of sensor locations and the final flight path of the drone.
 */
public final class GeoJsonDeserialiser {
	
	/**
	 * Mapping of colour strings to their hexadecimal String representation.
	 */
	private static final Map<String,String> colourNameToHex = Map.of(
			"Green", 		"#00ff00",
			"Medium Green", "#40ff00",
			"Light Green", 	"#80ff00",
			"Lime Green", 	"#c0ff00",
			"Gold", 		"#ffc000",
			"Orange", 		"#ff8000",
			"Red-Orange", 	"#ff4000",
			"Red", 			"#ff0000",
			"Black",	    "#000000",
			"Gray", 		"#aaaaaa"
	);
	
	
	/**
	 * Helper function to add appropriate attributes given a feature and a sensor
	 * @param feat Feature to add properties to
	 * @param sensor The sensor used to help add properties to the feature created from sensor
	 */
	private static void addAppropriateProperties(Feature feat, Sensor sensor) {
		String colourVal = mapReadingToColour(sensor);
		String symbol = mapReadingToSymbol(sensor);
		feat.addStringProperty("marker-color", colourVal);
		feat.addStringProperty("fill", colourVal);
		feat.addStringProperty("location", sensor.getw3wLocation());
		feat.addStringProperty("marker-symbol", symbol);

	}
	
	/**
	 * Helper function to make mappings of readings to the appropriate colour properties. The function also evaluates the state of the sensor when reading is not appropriate (Not visited or Low battery).
	 * @param sensor The sensor to get readings from 
	 * @return The colour hexadecimal String value depending on the state of the sensor
	 */
		private static String mapReadingToColour(Sensor sensor) {
			if (!sensor.isVisited()) {
				return colourNameToHex.get("Gray");
			}
			if(sensor.getBattery()<10) {
				return colourNameToHex.get("Black");
			}
			
			String valString = sensor.getReading();
			if (valString == null) {
				return colourNameToHex.get("Black");
			}
			var val = Float.parseFloat(valString);
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
		/**
		 * Helper function to Map reading to the appropriate marker symbols for each feature for GEOJSON.  
		 * @param sensor The sensor to get readings from.
		 * @return Returns appropriate String marker depending on status of sensor battery and the reading returned.
		 */
		private static String mapReadingToSymbol(Sensor sensor) {
			if(sensor.getBattery()<10) {
				return "cross";
			}
			String valString = sensor.getReading();
			if (valString == null) {
				return "";
			}
			var val = Float.parseFloat(valString);
			if (val>=0 && val<128) {
				return "lighthouse";
			}
			else if(val>=128 && val<256) {
				return "danger";
			}
			else {
				return "";
			}

		}

	
		/**
		 * Create a Feature from a list of points that are turned into a LineString
		 * @param path The list of points
		 * @return Feature representing the line (list of points)
		 */
	private static Feature createLinePath(List<Point> path){
		LineString line = LineString.fromLngLats(path);
		//Turn LineString to feature
		Feature feat = Feature.fromGeometry((Geometry) line);
		return feat;
	}
	
	/**
	 * Create new features using the coordinates of the sensor location, and add the appropriate attributes.
	 * @param listOfSensors The list of sensors to mark on the map
	 * @return the list of features generated.
	 */
	public static List<Feature>  markSensorsOnMap(Sensor[] listOfSensors) {
		List<Feature> listOfFeatures = new ArrayList<>();
		for (Sensor sensor: listOfSensors) {
			Point sensorPoint = sensor.locateSensorCoordinates();
			//Turn Point to feature
			Feature feat = Feature.fromGeometry((Geometry) sensorPoint);
			//Add attributes to the feature created
	    	addAppropriateProperties(feat, sensor);
	    	
	    	listOfFeatures.add(feat);
		}
		return listOfFeatures;
	}
	
	/**
	 * From a list of points and a list of sensors, generate a GEOJSON map
	 *  with lines representing the path the drone has taken and the list of sensors to mark their locations with appropriate attributes on the map
	 * @param path The complete pathing taken by the drone  
	 * @param listOfSensors the list of sensors the drone needs to visit on its path.
	 */
	
	public static void createGeoJSONMapReadings(List<Point> path, Sensor[] listOfSensors) {

		var lineFeature = createLinePath(path);
		var features = markSensorsOnMap(listOfSensors);
		features.add(lineFeature);
		//Turn the list of Features of the line path with the individual sensor points having appropriate properties to Json String
		var geoJson = (FeatureCollection.fromFeatures(features)).toJson(); 
		
		
    	try {
    		var year =App.getYearString();
    		var month =App.getMonthString();
    		var day =App.getDayString();
    		var filename = "readings-" + day + "-" + month + "-" + year;
    		var extensionType = "geojson";
    		var isNewFileCreated = true;
			FileUtilities.createOrAppendToFile(geoJson,filename,extensionType, isNewFileCreated);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1); //Unsuccessful termination of program
		}
	}
	
	
	
}
