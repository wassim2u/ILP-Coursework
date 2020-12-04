# [Drone](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L11)

**Type:** `public` `class`

The class represents the attributes of a drone. 
Its main responsibility lies in initiating all events involved in the drone life process, from initiating the scheduling of pathing that the drone needs to take, 
reading the sensor information when it is within range, and logging its flight path journey if needed. 
Note that this drone class does not handle path generation algorithm, but it creates new objects of types RoutePlanner and DronePathing which returns this information back to the drone. 












## [startingPoint](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L17)

**Type:** `private` `Point`

The start point of a drone. The robot must end its journey as close as possible back to these coordinates. 











## [predictedMoveCount](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L19)

**Type:** `private` `int`

The list of sensors to visit./ 
private Sensor[] listOfSensors; 
/Keeps track of the number of moves it will make. Updated after DronePathing generates a path from one point to another. 











## [numberOfMovesTaken](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L23)

**Type:** `private` `int`

Keeps track of the number of moves it made. Used for logging flight path and to emulate drone movement 











## [MAXIMUM](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L25)

**Type:** `private` `final` `int`

The maximim poves that a drone can take. 











## [restrictedAreas](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L27)

**Type:** `private` `NoFlyZone`

Holds information on the restricted areas that it cannot go to, including the knowlegde of the confinement area. 











## [int](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L30)

**Type:** `private`

Initialised when a Drone object is made. Represents the pathing scheduled from RoutePlanner object, with the value representing a mapping to the sensor number in the listOfSensors array. 
I.e) The index represents the flight number in order (with 0 being the departure at the starting point). The value 5 at an index i signifies that the drone gets in range to sensor numbered 5 in our int[] listOfSensors array at flight number i. 












## [firstSensorInTour](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L34)

**Type:** `private` `int`

The pathing scheduled from RoutePlanner object has the first sensor to visit at Flight number 1 in the droneTourSpots array, with 0 being the starting position of the drone when the program is started. 











## [createNewFile](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L36)

**Type:** `private` `boolean`

The pathing successfully taken by the drone as it emulates movement from one point to another./ 
private List<Point> droneCompletePath; 
/Represents the direction of departure (0 degrees being East moving counterclockwise in multiples of 10) when the drone successfully emulates the pathing from one point to another./ 
private List<Integer> droneCompleteDirections; 
/For every life cycle of a program, used for the creation of a new file representing the flight path recorded. 











## [receiverRange](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L42)

**Type:** `private` `double`

The range of the drone's receiver in degrees 











## [returnCompletePath](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L66)

**Type:** `public` `List<Point>`

Finds the complete pathing at the end of the drone lifecycle with the schedule of flights returned by RoutePlanner. 




|Parameter Name|Description|
|-----|-----|
|recordFlight|Boolean value to signal the drone whether there is a need to record the flight paths taken or not. |


**Returned Value:** a list of points representing the full pathing taken by the drone at the end of its lifecycle  








## [registerNewPathingInfo](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L88)

**Type:** `private` `void`

Updates the internal values of the drone as it emulates movement from pointA to the next destination pointB. 




|Parameter Name|Description|
|-----|-----|
|droneController|The object that handles the pathfinding problem from one point to another while avoiding the no fly zones. To be used to generate a path from pointA to pointB.|
|pointA|The departure from the first point.|
|pointB|The next point destination.|
|tourNumber|The current flight number in our complete pathing.|
|recordFlight|Set to True if logging values are required.  |








## [getSensorInTour](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L130)

**Type:** `public` `Sensor`

Returns object of type Sensor at the specified flight number in the journey from our list of sensors. The function allows us to map the requried flight number to the corresponding sensor. 




|Parameter Name|Description|
|-----|-----|
|currentTourIndex|The current flight number|


**Returned Value:** The specified sensor needed.   








## [getSensorCoordinatesInTour](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L140)

**Type:** `public` `Point`

Finds the point coordinates of the sensor given a specified flight number that corresponds to the sensor at a particular spot on the journey in our flight path. 




|Parameter Name|Description|
|-----|-----|
|currentTourIndex|The current flight number|


**Returned Value:** The Point coordinates of the specified sensor needed  








## [getAllSensorPoints](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L149)

**Type:** `public` `List<Point>`

Takes the information we know about our list of sensors that need to be visited (parsed from the webserver) and returns a list of their point coordinates on the map 





**Returned Value:** The list of point coordinates for all the sensors.  








## [readSensor](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L162)

**Type:** `public` `Float`

Emulates the reading of a sensor by communicating with the sensor object and mark it as visited. If the battery is less than 10, report as malfunctioning. 




|Parameter Name|Description|
|-----|-----|
|sensor|The Sensor the drone is in range with and can take readings from if possible.|


**Returned Value:** Returns the reading returned by the sensor. Return null if the battery is low (less than 10).  








## [recordFlightPath](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L183)

**Type:** `public` `void`

Records the flight journey path in a text file 




|Parameter Name|Description|
|-----|-----|
|moveCount|Number of moves|
|pointOnPath|The point on a path|
|nextPointOnPath|the next close point in the path|
|directionAngle|the angle in which the drone moved from pointOnPath|
|sensorLocation|the what3words location of the sensor  |








## [addPredictedMoveCount](../src/main/java/uk/ac/ed/inf/aqmaps/Drone.java#L218)

**Type:** `public` `void`

Update the predictedMoveCount, signifying the number of moves the drone will make after it schedules a pathing. 




|Parameter Name|Description|
|-----|-----|
|moves|The number of moves made calculated at the end of the path generation segment of DronePathing.   |








