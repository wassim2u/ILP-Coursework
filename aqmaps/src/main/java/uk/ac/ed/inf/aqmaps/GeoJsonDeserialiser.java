package uk.ac.ed.inf.aqmaps;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;

public class GeoJsonDeserialiser {
	
	//Map colour names to Hex values
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
	
	
	//Helper function to add suitable attributes
	private static void addAppropriateProperties(Feature feat, Sensor sensor) {
		String colourVal = mapReadingToColour(sensor);
		String symbol = mapReadingToSymbol(sensor);
		feat.addStringProperty("marker-color", colourVal);
		feat.addStringProperty("fill", colourVal);
		feat.addStringProperty("location", sensor.getw3wLocation());
		feat.addStringProperty("marker-symbol", symbol);

	}
	
	//Helper function to assign Hex value in order to add a colour attribute
		private static String mapReadingToColour(Sensor sensor) {
			if (!sensor.isVisited()) {
				return colourNameToHex.get("Gray");
			}
			if(sensor.getBattery()<10) {
				return colourNameToHex.get("Black");
			}
			
			float val = sensor.getReading();
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
		
		private static String mapReadingToSymbol(Sensor sensor) {
			if(sensor.getBattery()<10) {
				return "cross";
			}
			
			float val = sensor.getReading();
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

	
	public static Feature createLinePath(List<Point> path){
		LineString line = LineString.fromLngLats(path);
		//Turn LineString to feature
		Feature feat = Feature.fromGeometry((Geometry) line);
		return feat;
	}
	
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
	
	
	public static void createGeoJSONMapReadings(List<Point> path, Sensor[] listOfSensors) {
		var lineFeature = createLinePath(path);
		var features = markSensorsOnMap(listOfSensors);
		features.add(lineFeature);
//		NoFlyZone offLimitZones = JsonParser.parseNoFlyZones(App.getPortNumber()); 
//		features.addAll(offLimitZones.noFlyZonesCollection.features());
		//Turn the list of Features of the line path with the individual sensor points having appropriate properties to Json String
		var geoJson = (FeatureCollection.fromFeatures(features)).toJson(); 
		
		
    	try {
    		var year =App.getYearString();
    		var month =App.getMonthString();
    		var day =App.getDayString();
    		var filename = "readings-" + day + "-" + month + "-" + year+ ".geojson";
    		var isNewFileCreated = true;
			App.createOrAppendToFile(geoJson,filename, isNewFileCreated);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1); //Unsuccessful termination of program
		}
	}
	
	
	
}
