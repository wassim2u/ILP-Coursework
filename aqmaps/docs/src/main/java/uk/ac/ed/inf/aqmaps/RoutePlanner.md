# [RoutePlanner](../src/main/java/uk/ac/ed/inf/aqmaps/RoutePlanner.java#L10)

**Type:** `public` `class`

The class tackles the Traveling Salesmen Problem using TwoOpt for finding the estimated, best journey given a list of sensor points and a starting point, which we need to visit once. 
Note that the current implementation does not consider the obstacles when scheduling the best path. Pathfinding from a pointA to the next nearest pointB in the journey tackles the issue 
of avoiding offlimit areas. 












## [int](../src/main/java/uk/ac/ed/inf/aqmaps/RoutePlanner.java#L19)

**Type:** `private`

Represents the pathing scheduled from RoutePlanner object, with the value in tourIndex representing a mapping to the sensor number in the listOfSensors array. 
I.e) The indicies represent the flight number in order (with 0 being the departure at the starting point). The value 5 at an index i signifies that the drone gets in range to sensor numbered 5 in our int[] listOfSensors array (See Drone class) at flight number i. 












## [RoutePlanner](../src/main/java/uk/ac/ed/inf/aqmaps/RoutePlanner.java#L26)

**Type:** `public`

Constructs a distance matrix representing the distances from one sensor to another when the constructor is called. 




|Parameter Name|Description|
|-----|-----|
|start|The starting point of the drone, which the drone needs to come back to at the end of the route|
|sensorPoints|The list of sensors to visit.  |








## [int](../src/main/java/uk/ac/ed/inf/aqmaps/RoutePlanner.java#L70)

**Type:** `public`

Runs the TwoOpt Heuristic of obtaining the best flight journey. 





**Returned Value:** the journey chosen by the algorithm as list of integers with values corresponding to the sensor number labeled in Drone. Note that the value 0 corresponds to the starting point and not a sensor.  








## [calculateTourCost](../src/main/java/uk/ac/ed/inf/aqmaps/RoutePlanner.java#L79)

**Type:** `private` `double`

Using the distance matrix, calculate the distance to traverse a route configuration 




|Parameter Name|Description|
|-----|-----|
|tour|The flight journeys (The idea similar to the explanation for tourIndex attribute).|


**Returned Value:** the total distance to traverse this route configuration.  








## [twoOpt](../src/main/java/uk/ac/ed/inf/aqmaps/RoutePlanner.java#L95)

**Type:** `private` `void`

The algorithm for attempting to find the best route configuration for the drone. 
The main idea is that it tries to attempt reversing portions of routes and sees if there are any improvements. This is done iteratively until reversing doesnt give a better result. 













## [int](../src/main/java/uk/ac/ed/inf/aqmaps/RoutePlanner.java#L119)

**Type:** `private`

Consider taking the reverse route between a sensor at index m in our tour and another sensor at index n. If the cost of 
reversing the route is lower than the cost originally, then change the new tour with the better cost. 
We are only comparing the changes by examining the new distances between the sensors we are attempting the reverse. 
The costs of the route with unchanged elements will still yield the same result 
(i.e A , B , C , D , F , G). Reversing B and F, the new route will be : A , F , D ,C , B , G. Examine if the new route total cost is lower 




|Parameter Name|Description|
|-----|-----|
|mInd|the flight number to consider swapping the routes for|
|nInd|the second flight number to consider swapping the routes for|


**Returned Value:** the new flight journeys 








