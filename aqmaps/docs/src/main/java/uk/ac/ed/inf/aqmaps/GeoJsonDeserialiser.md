# [GeoJsonDeserialiser](../src/main/java/uk/ac/ed/inf/aqmaps/GeoJsonDeserialiser.java#L16)

**Type:** `public` `final` `class`

Utility class used to generate GeoJson strings which contain methods to make Features of sensor locations and the final flight path of the drone. 












## [Map<String](../src/main/java/uk/ac/ed/inf/aqmaps/GeoJsonDeserialiser.java#L21)

**Type:** `private` `static` `final`

Mapping of colour strings to their hexadecimal String representation. 












## [addAppropriateProperties](../src/main/java/uk/ac/ed/inf/aqmaps/GeoJsonDeserialiser.java#L38)

**Type:** `private` `static` `void`

Helper function to add appropriate attributes given a feature and a sensor 




|Parameter Name|Description|
|-----|-----|
|feat|Feature to add properties to|
|sensor|The sensor used to help add properties to the feature created from sensor  |








## [mapReadingToColour](../src/main/java/uk/ac/ed/inf/aqmaps/GeoJsonDeserialiser.java#L53)

**Type:** `private` `static` `String`

Helper function to make mappings of readings to the appropriate colour properties. The function also evaluates the state of the sensor when reading is not appropriate (Not visited or Low battery). 




|Parameter Name|Description|
|-----|-----|
|sensor|The sensor to get readings from |


**Returned Value:** The colour hexadecimal String value depending on the state of the sensor  








## [mapReadingToSymbol](../src/main/java/uk/ac/ed/inf/aqmaps/GeoJsonDeserialiser.java#L92)

**Type:** `private` `static` `String`

Helper function to Map reading to the appropriate marker symbols for each feature for GEOJSON. 




|Parameter Name|Description|
|-----|-----|
|sensor|The sensor to get readings from.|


**Returned Value:** Returns appropriate String marker depending on status of sensor battery and the reading returned.  








## [createLinePath](../src/main/java/uk/ac/ed/inf/aqmaps/GeoJsonDeserialiser.java#L116)

**Type:** `private` `static` `Feature`

Create a Feature from a list of points that are turned into a LineString 




|Parameter Name|Description|
|-----|-----|
|path|The list of points|


**Returned Value:** Feature representing the line (list of points)  








## [markSensorsOnMap](../src/main/java/uk/ac/ed/inf/aqmaps/GeoJsonDeserialiser.java#L128)

**Type:** `public` `static` `List<Feature>` ``

Create new features using the coordinates of the sensor location, and add the appropriate attributes. 




|Parameter Name|Description|
|-----|-----|
|listOfSensors|The list of sensors to mark on the map|


**Returned Value:** the list of features generated.  








## [](../src/main/java/uk/ac/ed/inf/aqmaps/GeoJsonDeserialiser.java#L147)

From a list of points and a list of sensors, generate a GEOJSON map 
with lines representing the path the drone has taken and the list of sensors to mark their locations with appropriate attributes on the map 




|Parameter Name|Description|
|-----|-----|
|path|The complete pathing taken by the drone |
|listOfSensors|the list of sensors the drone needs to visit on its path.  |








