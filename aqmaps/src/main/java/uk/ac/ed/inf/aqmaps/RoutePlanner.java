package uk.ac.ed.inf.aqmaps;

import java.util.List;

import com.mapbox.geojson.Point;



/**
 * The class tackles the Traveling Salesmen Problem using TwoOpt for finding the estimated, best journey given a list of sensor points and a starting point, which we need to visit once. 
 * Note that the current implementation does not consider the obstacles when scheduling the best path. Pathfinding from a pointA to the next nearest pointB in the journey tackles the issue 
 * of avoiding offlimit areas.
 */
public class RoutePlanner {
	//Stores the distances from one sensor to another
	private double[][] distances; 
	private final int TOUR_LENGTH;
	/**
	 *  Represents the pathing scheduled from RoutePlanner object, with the value in tourIndex representing a mapping to the sensor number in the listOfSensors array.
	 *  I.e) The indicies represent the flight number in order (with 0 being the departure at the starting point). The value 5 at an index i signifies that the drone gets in range to sensor numbered 5 in our int[] listOfSensors array (See Drone class) at flight number i.
	 */
	private int[] tourIndex; 
	
	
	/**
	 * Constructs a distance matrix representing the distances from one sensor to another when the constructor is called. 
	 *@param start  The starting point of the drone, which the drone needs to come back to at the end of the route
	 *@param sensorPoints The list of sensors to visit.
	 */
	public RoutePlanner(Point start, List<Point> sensorPoints) {
		//Initialise tour to be the order of sensors passed originally. We will modify this to give the best tour
		TOUR_LENGTH = sensorPoints.size() + 1;
		tourIndex = new int[TOUR_LENGTH];
		for (int i=0;i<TOUR_LENGTH;i++) {
			tourIndex[i] = i;
		}
		
		//Initialise a 2D array to store the distances as an adjacency matrix.
		distances = new double[TOUR_LENGTH][TOUR_LENGTH];
		
		for (int i=0; i< TOUR_LENGTH; i++){
			Point currCoordinates;
			if (i ==0) {
				currCoordinates = start;
			}
			else {
				currCoordinates = sensorPoints.get(i-1);
			}
			for (int j=0; j<i; j++) {
				//Calculate the distance between the two points if we are not at the same point.
				if (i != j){
					Point toCoordinates;
					if (j == 0) {
						toCoordinates = start;
					}
					else {
						toCoordinates= sensorPoints.get(j-1); 
					}
					//Using the coordinates parsed from the webserver using locateSensorCoordinates, calculate the distance between the two sensors.
					var dist = Distance.euclideanDistanceBetweenTwoPoints(currCoordinates,toCoordinates);
					distances[i][j] = dist;
					distances[j][i] = dist;
				}
				
			}
		}
	}
	
	/**
	 * Runs the TwoOpt Heuristic of obtaining the best flight journey. 
	 * @return the journey chosen by the algorithm as list of integers with values corresponding to the sensor number labeled in Drone. Note that the value 0 corresponds to the starting point and not a sensor.
	 */
	public int[] generateRoute() {
		twoOpt();
		System.out.println("Created a travel plan for the drone to visit sensors");
		return tourIndex;
	}
	
	/**
	 * Using the distance matrix, calculate the distance to traverse a route configuration
	 * @param tour The flight journeys (The idea similar to the explanation for tourIndex attribute).
	 * @return the total distance to traverse this route configuration.
	 */
	private double calculateTourCost(int[] tour) {
		var cost = 0.0; 
		for (int i=0; i<TOUR_LENGTH-1;i++) {
			var current = tour[i];
			var to = tour[i+1];
			cost += distances[current][to];
		}
		cost += distances[tour[TOUR_LENGTH-1]][tour[0]];
		return cost;
	}
	
	/**
	 * The algorithm for attempting to find the best route configuration for the drone. 
	 * The main idea is that it tries to attempt reversing portions of routes and sees if there are any improvements. This is done iteratively until reversing doesnt give a better result.
	 * 
	 */
	private void twoOpt(){
		var improved = true;
		while (improved==true){
			improved = false;
			var originalDistance = calculateTourCost(tourIndex);
			for (int m =1; m<TOUR_LENGTH-1; m++) {
				for (int n=m+1; n<TOUR_LENGTH; n++) {
					var newRoute = attemptReverseRoute(m,n);
					var newDistance = calculateTourCost(newRoute);
					if (newDistance < originalDistance){
						tourIndex = newRoute.clone();
						improved = true;
					}
				}
			}
		}
	}
	
	
	/**
	* Consider taking the reverse route between a sensor at index m in our tour and another sensor at index n. If the cost of 
	* reversing the route is lower than the cost originally, then change the new tour with the better cost.
	* We are only comparing the changes by examining the new distances between the sensors we are attempting the reverse. 
	* The costs of the route with unchanged elements will still yield the same result 
	* (i.e A , B , C , D , F , G). Reversing B and F, the new route will be : A , F , D ,C , B , G. Examine if the new route total cost is lower
	* @param mInd the flight number to consider swapping the routes for
	* @param nInd the second flight number to consider swapping the routes for
	* @return the new flight journeys
	*/
	private int[] attemptReverseRoute(int mInd, int nInd) {
		var newRoute = new int[TOUR_LENGTH];
		int index = 0;
		for (int i=0; i<mInd; i++) {
			newRoute[i] = tourIndex[i];
			index++;
		}
		for (int i=nInd; i>=mInd; i--) {
			newRoute[index] = tourIndex[i];
			index++;
		}
		for (int i=nInd+1; i<TOUR_LENGTH; i++) {
			newRoute[index] = tourIndex[i];
			index++;
		}
		
		return newRoute;
	}
	

	
	

	
	
	public int[] gettourIndex() {
		return tourIndex;
	}
	
	
	
	

	
	
	
}

