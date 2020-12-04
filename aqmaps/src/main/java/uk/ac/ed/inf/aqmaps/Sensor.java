package uk.ac.ed.inf.aqmaps;

import com.mapbox.geojson.Point;

import uk.ac.ed.inf.aqmaps.What3WordsDetails.Coordinates;


/**
 * Class used when parsing JSON sensor information from webserver. Contains additional fields to mark whether the sensor needs replacement or not. 
 */
public class Sensor {
	
	private String location;
	private Float battery;
	private String reading;
	private boolean needsReplacement=false;
	private boolean visited=false;

	
	
	/**
	 * Given that we know its what3words Location, we can convert it to (lng,lat) coordinates 
	 * by passing in the w3wlocation to the Json Parser as the sensor communicates with webserver to get the point coordinates.
	 * @return Object of type com.mapbox.GeoJSON.Point representing the coordinates of the sensor.
	 */
	public Point locateSensorCoordinates() {
		What3WordsDetails w3waddress = JsonParser.parseWhat3WordsDetails(App.getPortNumber(), location);
		Coordinates coordinates = w3waddress.getCoordinates();
		return Point.fromLngLat(coordinates.lng, coordinates.lat);
	}
	
	
	public String getw3wLocation() {
		return location;
	}
	public float getBattery() {
		return battery;
	}
	public String getReading() {
		return reading;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	
	public boolean getReplacementStatus() {
		return needsReplacement;
	}
	
	public void setReplacementStatus(boolean condition) {
		needsReplacement = condition;
	}
	public void setVisitedStatus(boolean visitedStatus) {
		visited = visitedStatus;
	}
}
