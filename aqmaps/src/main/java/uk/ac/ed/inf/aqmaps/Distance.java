package uk.ac.ed.inf.aqmaps;

import com.mapbox.geojson.Point;

/**
 * Utility class which contains a function used by other classes to calculate the distance between two points.
 */
public final class Distance {

	private Distance() {
		
	}
	/**
	 * Given two points, it returns the euclidean distance between them. 
	 * @param a The first point with longitude and latitude coordiantes of type double
	 * @param b The second point with longitude and latitude coordinates of type double
	 * @return The Euclidean distance between the two points.
	 */
	public static double euclideanDistanceBetweenTwoPoints(Point a, Point b) {
		var ydist = a.longitude()  - b.longitude();
		var xdist = a.latitude() - b.latitude();
		return Math.sqrt(ydist*ydist + xdist*xdist);
	}
	
}
