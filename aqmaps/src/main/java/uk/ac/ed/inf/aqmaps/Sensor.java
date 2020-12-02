package uk.ac.ed.inf.aqmaps;

import com.mapbox.geojson.Point;

import uk.ac.ed.inf.aqmaps.What3WordsDetails.Coordinates;


public class Sensor {
	
	private String location;
	private float battery;
	private float reading;
	private boolean needsReplacement=false;
	
	
	
	//Given that we know its what3words Location, we can convert it to (lng,lat) coordinates
	//by passing in the w3wlocation to the Json Parser to get the information from the webserver.
	public Point locateSensorCoordinates() {
		What3WordsDetails w3waddress = JsonParser.parseWhat3WordsDetails(App.getPortNumber(), location);
		Coordinates coordinates = w3waddress.getCoordinates();
		return Point.fromLngLat(coordinates.lng, coordinates.lat);
	}
	
	//Getters to retrieve the variable values
	
	
	public String getw3wLocation() {
		return location;
	}
	public float getBattery() {
		return battery;
	}
	public float getReading() {
		return reading;
	}
	
	

	public boolean getReplacementStatus() {
		return needsReplacement;
	}
	
	public void setReplacementStatus(boolean condition) {
		needsReplacement = condition;
	}
}
