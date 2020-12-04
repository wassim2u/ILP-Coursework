# [Sensor](../src/main/java/uk/ac/ed/inf/aqmaps/Sensor.java#L9)

**Type:** `public` `class`

Class used when parsing JSON sensor information from webserver. Contains additional fields to mark whether the sensor needs replacement or not. 












## [locateSensorCoordinates](../src/main/java/uk/ac/ed/inf/aqmaps/Sensor.java#L22)

**Type:** `public` `Point`

Given that we know its what3words Location, we can convert it to (lng,lat) coordinates 
by passing in the w3wlocation to the Json Parser as the sensor communicates with webserver to get the point coordinates. 





**Returned Value:** Object of type com.mapbox.GeoJSON.Point representing the coordinates of the sensor.  








