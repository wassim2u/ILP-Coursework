package uk.ac.ed.inf.aqmaps;

import com.mapbox.geojson.Point;

public class Distance {

	private Distance() {
		
	}
	
	public static double euclideanDistanceBetweenTwoPoints(Point a, Point b) {
		var ydist = a.longitude()  - b.longitude();
		var xdist = a.latitude() - b.latitude();
		return Math.sqrt(ydist*ydist + xdist*xdist);
	}
	
}
