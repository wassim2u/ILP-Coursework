package uk.ac.ed.inf.aqmaps;

import java.util.ArrayList;
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
	public final int TOUR_LENGTH_EXCLUDING_RETURN;
	public List<Integer> tourIndex; 
	
	//Construct a graph representing the distances from one sensor to another
	public RoutePlanner(Point start, Sensor[] listOfSensors) {
		//Initialise tour to be the order of sensors passed originally. We will modify this to give the best tour
		//Include an arbitrary null sensor at Index 0 to represent the starting point
		//TODO: Meaningful comments. Have sensorNumber instead of i for example.
		TOUR_LENGTH_EXCLUDING_RETURN = listOfSensors.length + 1;
		tourIndex = new ArrayList<Integer>(TOUR_LENGTH_EXCLUDING_RETURN);
		for (int i=0;i<TOUR_LENGTH_EXCLUDING_RETURN;i++) {
			tourIndex.add(i);
		}
		
		//Initialise a 2D array to store the distances as an adjacency matrix.
		distances = new double[TOUR_LENGTH_EXCLUDING_RETURN][TOUR_LENGTH_EXCLUDING_RETURN];
		
		for (int i=0; i< TOUR_LENGTH_EXCLUDING_RETURN; i++){
			Point currCoordinates;
			if (i ==0) {
				currCoordinates = start;
			}
			else {
				currCoordinates = listOfSensors[i-1].locateSensorCoordinates();
			}
			for (int j=0; j<i; j++) {
				//Calculate the distance between the two points if we are not at the same point.
				if (i != j){
					Point toCoordinates;
					if (j == 0) {
						toCoordinates = start;
					}
					else {
						toCoordinates= listOfSensors[j-1].locateSensorCoordinates(); 
					}
					//Using the coordinates parsed from the webserver using locateSensorCoordinates, calculate the distance between the two sensors.
					var dist = Distance.euclideanDistanceBetweenTwoPoints(currCoordinates,toCoordinates);
					distances[i][j] = dist;
					distances[j][i] = dist;
				}
				
			}
		}
	}
	
	public double calculateTourCost(List<Integer> tour) {
		var cost = 0.0; 
		for (int i=0; i<TOUR_LENGTH_EXCLUDING_RETURN-1;i++) {
			var current = tour.get(i);
			var to = tour.get(i+1);
			cost += distances[current][to];
		}
		cost += distances[tour.get(TOUR_LENGTH_EXCLUDING_RETURN-1)][tour.get(0)];
		return cost;
	}
	
	public void twoOpt(){
		var improved = true;
		while (improved==true){
			improved = false;
			var originalDistance = calculateTourCost(tourIndex);
			for (int m =1; m<TOUR_LENGTH_EXCLUDING_RETURN-1; m++) {
				for (int n= m+1; n<TOUR_LENGTH_EXCLUDING_RETURN; n++) {
					var newRoute = attemptReverseRoute(m,n);
					var newDistance = calculateTourCost(newRoute);
					if (newDistance < originalDistance){
						tourIndex = newRoute;
						improved = true;
					}
				}
			}
		}
	}
	
	public List<Integer> generateRoute() {
		twoOpt();
		//Create a new list and remove starting point which is not a sensor.
		List <Integer> sensorTourSpots  = new ArrayList<Integer>();
		for (int i = 1; i < tourIndex.size(); i++) {
			   int val = tourIndex.get(i);//get the value
			   sensorTourSpots.add(val-1);//subtract and set the value at the same index
		}
		return sensorTourSpots;
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
	public List<Integer> attemptReverseRoute(int mInd, int nInd) {
		var newRoute = new ArrayList<Integer>(TOUR_LENGTH_EXCLUDING_RETURN);
		int index = 0;
		for (int i=0; i<mInd; i++) {
			newRoute.add(tourIndex.get(i));
			index++;
		}
		for (int i=nInd; i>=mInd; i--) {
			newRoute.add(tourIndex.get(i));
			index++;
		}
		for (int i=nInd+1; i<TOUR_LENGTH_EXCLUDING_RETURN; i++) {
			newRoute.add(tourIndex.get(i));
			index++;
		}
		
		return newRoute;
	}
	

	
	

	
	
	public List<Integer> gettourIndex() {
		return tourIndex;
	}
	
	
	
	

	
	
	
}

