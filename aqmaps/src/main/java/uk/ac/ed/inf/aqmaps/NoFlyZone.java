package uk.ac.ed.inf.aqmaps;

import com.mapbox.geojson.FeatureCollection;

import java.awt.geom.Line2D;
import java.util.List;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;


/**
 * 
 * Represents the confinement area and the offLimit areas on the map. The offLimit areas can be retrieved from the webserver
 * Confinement area is the rectangular region where the drone can move around, and the offlimit areas or zones are buildings and places the drone is now allowed to fly over.
 */
public class NoFlyZone {
	public static FeatureCollection noFlyZonesCollection;

	/*
	 * The numbers describe the range of values that the latitudes and longitudes could take
	 * The drones must stay strictly within these limits. 
	 * A drone at one of the limit locations or outside of the ranges will be considered as malfunctioning.
	 */
	final static double NORTHERN_BOUNDARY = 55.946233; //Currently latitude for Forrest Hill and KFC
    final static double SOUTHERN_BOUNDARY = 55.942617; //Currently latitude for Top of the Meadows and Buccleuch St. Bus Stop
    final static double WESTERN_BOUNDARY = -3.192473;  //Currently Longitude for Forrest Hill and Top of the Meadows
    final static double EASTERN_BOUNDARY = -3.184319;  //Currently Longitude for KFC and Buccleuch St. Bus Stop
    final static double RANGE_LONGITUDE = Math.abs(EASTERN_BOUNDARY - WESTERN_BOUNDARY); //width of the area enclosed by the boundaries
    final static double RANGE_LATITUDE =  Math.abs(NORTHERN_BOUNDARY - SOUTHERN_BOUNDARY);	//height of the area enclosed by the boundaries
	
    
	
	public NoFlyZone(String geoJsonSource) {
		noFlyZonesCollection = FeatureCollection.fromJson(geoJsonSource);
	}
	
	
	/**
	 * Checks if the coordinate point is within the correct regions, outside of restricted zones and inside the confinement area (within boundary)
	 * @param lineEndPoint the coordinate point to evaluate on
	 * @param linePath Represents a small line segment of the drone from Point A to Point B. 
	 * @return true if the coordinates are in the correct region
	 */
	public boolean checkCoordinatesWithinArea(Point lineEndPoint, Line2D.Double linePath){
		return checkWithinBoundary(lineEndPoint) && checkOutsideOffLimitAreas(linePath);  
	}
	
	/**
	 * Check whether a point is in the confinement area.
	 * @param p The point to evaluate on
	 * @return true if the condition is satisified
	 */
	public static boolean checkWithinBoundary(Point p) {
		var latCheck = p.latitude() < NORTHERN_BOUNDARY && p.latitude() > SOUTHERN_BOUNDARY;
		var longCheck = p.longitude() < EASTERN_BOUNDARY && p.longitude() > WESTERN_BOUNDARY;
		return latCheck && longCheck;

	}
	/**
	 * Given a line path, the function checks whether the line crosses one of the polygons by evaluating it against small polygon line segments.
	 * @param linePath Line created by movement from Point A to Point B
	 * @return True if the line path is not intersecting any of the polygons which correspond to the NoFlyZones
	 */
	public boolean checkOutsideOffLimitAreas(Line2D.Double linePath) {
		var features = noFlyZonesCollection.features();
		for (Feature f: features) {
			if (f.geometry() instanceof Polygon) {
				Polygon polygonOutline =(Polygon) f.geometry();
				 //Index 0 will give us List<Points> that we need.
				List<Point> polygonPoints = polygonOutline.coordinates().get(0); 
				for (int i=1; i<polygonPoints.size(); i++) {
					var pointA = polygonPoints.get(i-1);
					var pointB = polygonPoints.get(i);
					var lineBetweenTwoPolygonPoints = new Line2D.Double(pointA.latitude(),pointA.longitude(),
														pointB.latitude(),pointB.longitude());
					if (linePath.intersectsLine(lineBetweenTwoPolygonPoints)) {
						return false;
					}
				}
			}
			
		}
		//If no intersection if found at the end of the execution of this function, return true, m
		return true;
	}

	
	
}

	

