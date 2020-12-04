# [DronePathing](../src/main/java/uk/ac/ed/inf/aqmaps/DronePathing.java#L12)

**Type:** `public` `class`

Path Finder problem while avoiding obstacles. The class is responsible for generating a pathing from one point to another. 
It uses Weighted Asearch to navigate to the target destination. Some methods are private in order to abstract inner functionalities of this class, 
which the Drone class does not need to access to. 












## [heuristicFunction](../src/main/java/uk/ac/ed/inf/aqmaps/DronePathing.java#L46)

**Type:** `private` `double`

Returns the estimated cost of the heuristic function, which is the euclidean distance from one point to the final target point at the end of the journey. 
The distance is multiplied by an epsilon cost to speed up the search algorithm and prioritise the traversal of points more closer to the goal. 




|Parameter Name|Description|
|-----|-----|
|pointA|A point on space|
|endPoint|The final end target point|


**Returned Value:** the euclidean distance between the two points  








## [generatePathAStar](../src/main/java/uk/ac/ed/inf/aqmaps/DronePathing.java#L59)

**Type:** `public` `void`

Weighted ASearch Implementation. Finds the shortest path from the current position of the drone to the next position of the next target point. 
The algorithm also makes sure that a move is made before taking a reading, even if the drone is besides to the target coordinates. 




|Parameter Name|Description|
|-----|-----|
|startCoords|The start point of the journey|
|targetCoords|The end goal that the algorithm tries to find a path to reach for.  |








## [searchNeighborInList](../src/main/java/uk/ac/ed/inf/aqmaps/DronePathing.java#L116)

**Type:** `private` `boolean`

Find if the current node is in our list by checking the coordinates. Used in order to avoid adding duplicates to our open or closed list. 




|Parameter Name|Description|
|-----|-----|
|arr|List of nodes saved in open or closed list to evaluate the variable node with to see if node already exists in one of those lists.|
|node|The current node |


**Returned Value:** True if the node coordinates match up with one of the coordinates in our arr (which is either the open or closed list).  








## [initiatePossibleMoveSets](../src/main/java/uk/ac/ed/inf/aqmaps/DronePathing.java#L135)

**Type:** `private` `void`

Represents the possible directions and the change in displacements that our drone can move to. 
Rotate for a total of 36 including 0 degrees initial state. 
The values will be used to add to the coordinates of the points. 












## [findNextPossibleNodes](../src/main/java/uk/ac/ed/inf/aqmaps/DronePathing.java#L150)

**Type:** `private` `List<PathNode>`

Given a node, find the next possible nodes, which will be the neighbours. The method also checks by not generating new nodes that are outside the confinement area or inside a no fly zone. 




|Parameter Name|Description|
|-----|-----|
|currNode|The current node that will be used to find next nodes|


**Returned Value:** the list of neighbours   








## [backTrackAndStoreResults](../src/main/java/uk/ac/ed/inf/aqmaps/DronePathing.java#L179)

**Type:** `private` `void`

At the end of the A star iteration when close to the target point, back track the journey taken till the starting point and store the direction angles and paths. 




|Parameter Name|Description|
|-----|-----|
|currNode|The end result of the node next to the target   |








## [checkNumberOfMoves](../src/main/java/uk/ac/ed/inf/aqmaps/DronePathing.java#L199)

**Type:** `private` `boolean`

Check to see if the number of moves of our drone has reached the limit of moves allowed. Will be used to halt the algorithm and return the current pathing. 




|Parameter Name|Description|
|-----|-----|
|n|the PathNode to be evaluated on.|


**Returned Value:** true if the number of moves have reached the limit.  








## [withinTargetRange](../src/main/java/uk/ac/ed/inf/aqmaps/DronePathing.java#L208)

**Type:** `private` `boolean`

Given two points, determine whether the drone will be close to the target at a point in space curr. 




|Parameter Name|Description|
|-----|-----|
|curr|The projected position of the drone in space.|
|target|The target destination|


**Returned Value:** true if it is within the possible range allowed by the drone  








## [firstMove](../src/main/java/uk/ac/ed/inf/aqmaps/DronePathing.java#L221)

**Type:** `private` `boolean`

Edge case: If number of moves zero, we cant take 0 path. Must move before we take reading. 




|Parameter Name|Description|
|-----|-----|
|node|The node to evaluate on, which contains information on the number of moves taken to reach that node in space.|


**Returned Value:** true if the node is at the start  








