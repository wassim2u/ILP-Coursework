# [NoFlyZone](../src/main/java/uk/ac/ed/inf/aqmaps/NoFlyZone.java#L14)

**Type:** `public` `class`


Represents the confinement area and the offLimit areas on the map. The offLimit areas can be retrieved from the webserver 
Confimenemt area is the rectangular region where the drone can move around, and the offlimit areas or zones are buildings and places the drone is now allowed to fly over. 












## [checkCoordinatesWithinArea](../src/main/java/uk/ac/ed/inf/aqmaps/NoFlyZone.java#L41)

**Type:** `public` `boolean`

Checks if the coordinate point is within the correct regions, outside of restricted zones and inside the confinement area (within boundary) 




|Parameter Name|Description|
|-----|-----|
|lineEndPoint|the coordinate point to evaluate on|
|linePath|Represents a small line segment of the drone from Point A to Point B. |


**Returned Value:** true if the coordinates are in the correct region  








## [checkWithinBoundary](../src/main/java/uk/ac/ed/inf/aqmaps/NoFlyZone.java#L51)

**Type:** `public` `static` `boolean`

Check whether a point is in the confinement area. 




|Parameter Name|Description|
|-----|-----|
|p|The point to evaluate on|


**Returned Value:** true if the condition is satisified  








## [checkOutsideOffLimitAreas](../src/main/java/uk/ac/ed/inf/aqmaps/NoFlyZone.java#L62)

**Type:** `public` `boolean`

Given a line path, the function checks whether the line crosses one of the polygons by evaluating it against small polygon line segments. 




|Parameter Name|Description|
|-----|-----|
|linePath|Line created by movement from Point A to Point B|


**Returned Value:** True if the line path is not intersecting any of the polygons which correspond to the NoFlyZones  








