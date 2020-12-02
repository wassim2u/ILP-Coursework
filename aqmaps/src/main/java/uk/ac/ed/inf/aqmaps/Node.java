package uk.ac.ed.inf.aqmaps;

import java.util.ArrayList;
import java.util.List;

import com.mapbox.geojson.Point;

public class Node {
	private Node parent;
	private double costG;
	private Point coordinates;
	private int currentMoves;
	private int directionAngle;
	
	public Node(Point coord) {
		this.coordinates = coord;
		this.parent = null; //No parent meaning it is the root node.
		this.costG = 0.0;
		currentMoves = 0;
		directionAngle=0; //Point towards East
	}
	

	public Node(Point coord, Node parent, double cost) {
		this.coordinates = coord;
		this.parent = parent;
		this.costG = cost;
		currentMoves = parent.getMoves() + 1;
		directionAngle=0;//Point towards East as a starting point
		
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
	
	public Node getParent() {
		return parent;
	}
	public int getDirectionAngle() {
		return directionAngle;
	}
	
	public void setDirectionAngle(int angle) {
		directionAngle = angle;
	}
	
	



	
	
}
