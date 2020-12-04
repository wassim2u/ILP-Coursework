package uk.ac.ed.inf.aqmaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mapbox.geojson.Point;
import java.awt.geom.Line2D;

//Path Finder problem while avoiding obstacles. Bounded relaxation on A star with 1.5 epsilon
/**
 * Path Finder problem while avoiding obstacles. The class is responsible for generating a pathing from one point to another. 
 * It uses Weighted A* search to navigate to the target destination. Some methods are private in order to abstract inner functionalities of this class, 
 * which the Drone class does not need to access to.  
 */
public class DronePathing{
	private final double MOVE_LENGTH = 0.0003; //Length of a move in degrees
	private final int NUM_OF_DIRECTIONS = 36;
	private Point[] possibleMoveSets; 
	private Drone drone;
	private List<Point> path;
	private List<Integer> directions;
	//Weighted A* with epsilon cost - Multiplied to the heuristic
	private final double epsilon = 1.5; 

	
	
	
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
	
	
	
	
	/**
	 * Returns the estimated cost of the heuristic function, which is the euclidean distance from one point to the final target point at the end of the journey.
	 * The distance is multiplied by an epsilon cost to speed up the search algorithm and prioritise the traversal of points more closer to the goal.  
	 * @param pointA A point on space
	 * @param endPoint The final end target point
	 * @return the euclidean distance between the two points
	 */
	private double heuristicFunction(Point pointA, Point endPoint) {
		return Distance.euclideanDistanceBetweenTwoPoints(pointA,endPoint) * epsilon;
	}
		

	/**
	 * Weighted A* Search Implementation. Finds the shortest path from the current position of the drone to the next position of the next target point. 
	 * The algorithm also makes sure that a move is made before taking a reading, even if the drone is besides to the target coordinates.
	 * @param startCoords The start point of the journey
	 * @param targetCoords The end goal that the algorithm tries to find a path to reach for.
	 */
	public void generatePathAStar(Point startCoords, Point targetCoords) {
		initaliseNewPathing();
		//open consists of nodes that have been visited but not expanded yet. 
		//close consists of nodes that have been visited and expanded.
		var open = new ArrayList<PathNode>();
		var closed = new ArrayList<PathNode>();
		var foundPathSuccessfully = false;
		//create a new node
		PathNode startNode = new PathNode(startCoords);
		open.add(startNode);
		while(open.isEmpty() == false) {
			var minDist = Double.MAX_VALUE;
			var currNode = open.get(0);
			var bestIndex = 0;
			var index = 0;
			//Find the node with the best Fscore - 
			// which is the combination of gScore (cost taken by node so far to reach current point from start) + heuristicScore (estimated distance from current to end target)
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
				//break; we found our solution; or We cannot move anymore
					backTrackAndStoreResults(currNode);
					foundPathSuccessfully= true;
					break;
					
			}
			List<PathNode> nextPossibleNeighbours= findNextPossibleNodes(currNode);
			for (PathNode neighbourNode: nextPossibleNeighbours) {
				if (!searchNeighborInList(open,neighbourNode) && !(searchNeighborInList(closed,neighbourNode))){
					open.add(0,neighbourNode);
				}
				
			}
			
			
		}
		if (!foundPathSuccessfully) {
			System.out.println("No path found! Or Something went Wrong");
		}
	} 
	
	/**
	 * Find if the current node is in our list by checking the coordinates. Used in order to avoid adding duplicates to our open or closed list.
	 * @param arr List of nodes saved in open or closed list to evaluate the variable node with to see if node already exists in one of those lists.
	 * @param node  The current node 
	 * @return True if the node coordinates match up with one of the coordinates in our arr (which is either the open or closed list).
	 */
	private boolean searchNeighborInList(List<PathNode> arr, PathNode node) {
		for (PathNode nInList: arr) {
			if (nInList.getPointCoordinates().longitude() == node.getPointCoordinates().longitude()
	         && nInList.getPointCoordinates().latitude() ==node.getPointCoordinates().latitude()) {
				return true;
			}
		}
			
		return false;
        
    }
	
	
	/**
	 * Represents the possible directions and the change in displacements that our drone can move to. 
	 * Rotate for a total of 36 including 0 degrees initial state.
	 * The values will be used to add to the coordinates of the points.
	 */
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
	/**
	 * Given a node, find the next possible nodes, which will be the neighbours. The method also checks by not generating new nodes that are outside the confinement area or inside a no fly zone.
	 * @param currNode The current node that will be used to find next nodes
	 * @return the list of neighbours 
	 */
	private List<PathNode> findNextPossibleNodes(PathNode currNode) {
		var newLng = 0.0; var newLat= 0.0;
		int index = 0;
		int angle = 0;
		var neighbours = new ArrayList<PathNode>();
		var currentNodeLat = currNode.getPointCoordinates().latitude();
		var currentNodeLng =  currNode.getPointCoordinates().longitude();
		for (Point displacement: possibleMoveSets) {
			newLng = currentNodeLng + displacement.longitude();
			newLat = currentNodeLat + displacement.latitude();
			var newPoint = Point.fromLngLat(newLng, newLat);
			var pathLine = new Line2D.Double(currentNodeLat,currentNodeLng, newLat, newLng);
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
	
	/**
	 * At the end of the A star iteration when close to the target point, back track the journey taken till the starting point and store the direction angles and paths.
	 * @param currNode The end result of the node next to the target 
	 */
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
	
	
	
	/**
	 * Check to see if the number of moves of our drone has reached the limit of moves allowed. Will be used to halt the algorithm and return the current pathing. 
	 * @param n the PathNode to be evaluated on.
	 * @return true if the number of moves have reached the limit.
	 */
	private boolean checkNumberOfMoves(PathNode n) {
		return (n.getMoves() + drone.getCurrentNumberOfMoves()) == drone.getMaximumNumberOfAllowedMoves();
	}
	
	/**
	 * Given two points, determine whether the drone will be close to the target at a point in space curr.
	 * @param curr The projected position of the drone in space.
	 * @param target The target destination
	 * @return true if it is within the possible range allowed by the drone
	 */
	private boolean withinTargetRange(Point curr, Point target) {
		//distance between target and current node is less than 0.0002. If yes, return true;
		return Distance.euclideanDistanceBetweenTwoPoints(curr,target) < drone.getReadingRange();
	}
	
	
	
	/**
	 * Edge case: If number of moves zero, we cant take 0 path. Must move before we take reading.
	 * @param node The node to evaluate on, which contains information on the number of moves taken to reach that node in space.
	 * @return true if the node is at the start
	 */
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
