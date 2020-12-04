package uk.ac.ed.inf.aqmaps;

import com.mapbox.geojson.Point;

/**
 * Node corresponding to the properties of a point in our path search space for A* Search algorithm in DronePathing.  
 */
public class PathNode {
	private PathNode parent;
	private double costG;
	private Point coordinates;
	private int currentMoves;
	private int directionAngle;
	

	public PathNode(Point coord) {
		this.coordinates = coord;
		this.parent = null; //No parent meaning it is the root node.
		this.costG = 0.0;
		currentMoves = 0;
		directionAngle=0; //Point towards East
	}
	

	public PathNode(Point coord, PathNode parent, double gCost) {
		this.coordinates = coord;
		this.parent = parent;
		this.costG = gCost;
		currentMoves = parent.getMoves() + 1;
		directionAngle=0;//Point towards East as a start - To be changed later
		
	}
	
	public double getCostG() {
		return costG;
	}
	
	
	
	
	public Point getPointCoordinates() {
		return coordinates;
	}
	
	public int getMoves() {
		return currentMoves;
	}
	
	public PathNode getParent() {
		return parent;
	}
	public int getDirectionAngle() {
		return directionAngle;
	}
	
	public void setDirectionAngle(int angle) {
		directionAngle = angle;
	}
	
	



	
	
}
