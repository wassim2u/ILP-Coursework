package uk.ac.ed.inf.aqmaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mapbox.geojson.Point;
import java.awt.geom.Line2D;

//Path Finder problem while avoiding obstacles. Bounded relaxation on A star with 1.5 epsilon
public class DronePathing{
	private final double MOVE_LENGTH = 0.0003; //Length of a move in degrees
	private final int NUM_OF_DIRECTIONS = 36;
	private Point[] possibleMoveSets; 
	private Drone drone;
	private List<Point> path;
	private List<Integer> directions;
	private final double epsilon = 1.5; //Weighted A* with epsilon cost - Multiplied to the heuristic
	
	
	
	
	public DronePathing(Drone drone) {
		this.drone = drone; 
		initiatePossibleMoveSets();
		path = new ArrayList<Point>();
		directions = new ArrayList<Integer>();

	}
	
	private void initaliseNewPathing() {
		path.clear();
		directions.clear();
	}
	
	
	
	
	//Equivalent to h(n) - Estimated cost from node n to the end point
	private double heuristicFunction(Point pointA, Point endPoint) {
		return Distance.euclideanDistanceBetweenTwoPoints(pointA,endPoint) * epsilon;
	}
		
	//TODO: Try catch null error if it ever happens findPath

	public void generatePath(Point startCoords, Point targetCoords) {
		initaliseNewPathing();
		var open = new ArrayList<PathNode>();
		var closed = new ArrayList<PathNode>();
		var foundPathSuccessfully = false;
		
		PathNode startNode = new PathNode(startCoords);
		open.add(startNode);
		while(open.isEmpty() == false) {
			var minDist = Double.MAX_VALUE;
			var currNode = open.get(0);
			var bestIndex = 0;
			var index = 0;
			for (PathNode node: open) {
				//f(n) = g(n) + h(n)
				var totalFScore = node.getCostG() + heuristicFunction(node.getPointCoordinates(), targetCoords); 
				
				if (totalFScore < minDist) {
					minDist = totalFScore;
					currNode = node;
					bestIndex = index;
				}	
				index++;
			}
			open.remove(bestIndex);
			closed.add(0,currNode);
			if ((withinTargetRange(currNode.getPointCoordinates(),targetCoords) && !firstMove(currNode)) || checkNumberOfMoves(currNode)) {
				//break; we found our solution Or we cant move anymore
					backTrackAndStoreResults(currNode);
					foundPathSuccessfully= true;
					break;
					
			}
			List<PathNode> nextPossibleNeighbours= findNextPossibleNodes(currNode.getPointCoordinates(), currNode);
			for (PathNode neighbourNode: nextPossibleNeighbours) {
				if (!searchNeighborInList(open,neighbourNode) && !(searchNeighborInList(closed,neighbourNode))){
					open.add(0,neighbourNode);
				}
				
			}
			
			
		}
		//TODO: Handle null pls thnx
		if (!foundPathSuccessfully) {
			System.out.println("NULL WHY IS WHYYYY");
		}
	} 
	
	
	private boolean searchNeighborInList(List<PathNode> arr, PathNode node) {
		for (PathNode n: arr) {
			if (n.getPointCoordinates().longitude() == node.getPointCoordinates().longitude()
	         && n.getPointCoordinates().latitude() ==node.getPointCoordinates().latitude()) {
				return true;
			}
		}
			
		return false;
        
    }
	
	
	//Rotate for a total of 36 including 0 degrees initial state.
	//Represents the possible directions and the change in displacements that our drone can move to.
	//The values will be used to add to the coordinates of the points.
	private void initiatePossibleMoveSets() {
		var x = MOVE_LENGTH;
		var y = 0.0;
		this.possibleMoveSets = new Point[NUM_OF_DIRECTIONS];
		for (int angle=0; angle < 360; angle = angle+10) {
			var newX = x*Math.cos(Math.toRadians(angle)) - y*Math.sin(Math.toRadians(angle));
			var newY = x*Math.sin(Math.toRadians(angle)) + y*Math.cos(Math.toRadians(angle));
			possibleMoveSets[(angle/10)] = Point.fromLngLat(newY, newX);
		}
	}
	
	private List<PathNode> findNextPossibleNodes(Point currentPoint, PathNode currNode) {
		var newLng = 0.0; var newLat= 0.0;
		int index = 0;
		int angle = 0;
		var neighbours = new ArrayList<PathNode>();
		for (Point displacement: possibleMoveSets) {
			newLng = currentPoint.longitude() + displacement.longitude();
			newLat = currentPoint.latitude() + displacement.latitude();
			var newPoint = Point.fromLngLat(newLng, newLat);
			var pathLine = new Line2D.Double(currentPoint.latitude(),currentPoint.longitude(), newLat, newLng);
			if (drone.getOffLimitZones().checkCoordinatesWithinArea(newPoint,pathLine)) {
				var neighbourCost = currNode.getCostG() + MOVE_LENGTH;
				var neighbourNode = new PathNode(newPoint,currNode,neighbourCost);
				neighbourNode.setDirectionAngle(angle);
				neighbours.add(neighbourNode);
			}
			index++;
			angle+=10;
		}
		return neighbours;
	}
	
	private void backTrackAndStoreResults(PathNode currNode) {
		drone.addPredictedMoveCount(currNode.getMoves()); //Configure the number of moves we have made so far and add it to the drone.
		path.add(0,currNode.getPointCoordinates());
		directions.add(0,currNode.getDirectionAngle());
		

		while (currNode.getParent() !=null) {
			currNode = currNode.getParent();
			path.add(0,currNode.getPointCoordinates());
			directions.add(0,currNode.getDirectionAngle());
			
		}
	}
	
	
	
	
	private boolean checkNumberOfMoves(PathNode n) {
		return (n.getMoves() + drone.getCurrentNumberOfMoves()) == drone.getMaximumNumberOfAllowedMoves();
	}
	
	private boolean withinTargetRange(Point curr, Point target) {
		//distance between target and current node is less than 0.0002. If yes, return true;
		return Distance.euclideanDistanceBetweenTwoPoints(curr,target) < 0.0002;
	}
	
	
	
	//Edge case: If number of moves zero, we cant take 0 path. Must move before we take reading.
	private boolean firstMove(PathNode node) {
		return node.getMoves() ==0;
	}
	

	public List<Point> getPathOfCurrentMovement(){
		return path;
	}
	
	public List<Integer> getDirectionAnglesOfCurrentPathing(){
		return directions;
	}
	

	

	

}
