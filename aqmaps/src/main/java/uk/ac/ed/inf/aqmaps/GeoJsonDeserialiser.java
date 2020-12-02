package uk.ac.ed.inf.aqmaps;


import java.util.List;
import java.util.Map;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;

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
			"Red", 			"#ff0000"
	);
	
	//Specified fill_opacitiy property value to add to all the features
	private static final String FILL_OPACITY = "0.75"; 
	
	//Helper function to add suitable attributes
	private static void addAppropriateProperties(Feature feat, Sensor sensor) {
		String colourVal = mapValueToColour(sensor.getReading());
		feat.addStringProperty("fill-opacity", FILL_OPACITY);
		feat.addStringProperty("rgb-string", colourVal);
		feat.addStringProperty("fill", colourVal);
	}
	
	//Helper function to assign Hex value in order to add a colour attribute
		private static String mapValueToColour(float val) {
			
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

	
	public static void createLinePath(List<Point> path){
		var linePathing = LineString.fromLngLats(path);
	}
	
	public static void markSensorsOnMap(Sensor[] listOfSensors) {
		for (Sensor sensor: listOfSensors) {
			Point sensorPoint = sensor.locateSensorCoordinates();
			
		}
	}
	
}
