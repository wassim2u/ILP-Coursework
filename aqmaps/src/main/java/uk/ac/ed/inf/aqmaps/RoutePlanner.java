package uk.ac.ed.inf.aqmaps;

import java.util.List;
import java.util.Map;

import com.mapbox.geojson.Point;

/*
 * TODO: Optimise this algorithm to be faster (Dont make a new array every iteration of reverse and calculate cost of the affected tours)
 *  choose different TSP algorithm 
 *  
 *  TODO: Handle invalid input starting points? 
 */

//TSP Problem - naive without obstacle
public class RoutePlanner {
	//Stores the distances from one sensor to another
	public double[][] distances; 
	public final int TOUR_LENGTH;
	public int[] tourIndex; 
	
	//Construct a graph representing the distances from one sensor to another
	public RoutePlanner(Point start, List<Point> sensorPoints) {
		//Initialise tour to be the order of sensors passed originally. We will modify this to give the best tour
		//Include an arbitrary null sensor at Index 0 to represent the starting point
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
	

	public double calculateTourCost(int[] tour) {
		var cost = 0.0; 
		for (int i=0; i<TOUR_LENGTH-1;i++) {
			var current = tour[i];
			var to = tour[i+1];
			cost += distances[current][to];
		}
		cost += distances[tour[TOUR_LENGTH-1]][tour[0]];
		return cost;
	}
	
	public void twoOpt(){
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
	
	public int[] generateRoute() {
		twoOpt();
		return tourIndex;
	}
	/*
	*Consider taking the reverse route between a sensor at index m in our tour and another sensor at index n. If the cost of 
	*reversing the route is lower than the cost originally, then change the new tour with the better cost.
	*We are only comparing the changes by examining the new distances between the sensors we are attempting the reverse. 
	*The costs of the route with unchanged elements will still yield the same result 
	*(i.e A - B - C - D - F - G). Reversing B and F, the new route will be : A - F - D -C - B - G. Examine if the new route total cost is lower
	*
	* Note : mInd should always be less than nInd.
	*/
	public int[] attemptReverseRoute(int mInd, int nInd) {
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

