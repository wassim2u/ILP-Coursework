# [JsonParser](../src/main/java/uk/ac/ed/inf/aqmaps/JsonParser.java#L20)

**Type:** `public` `final` `class`

Utility class that handles parsing data from the webserver from Json into appropriate Object types 












## [serverName](../src/main/java/uk/ac/ed/inf/aqmaps/JsonParser.java#L24)

**Type:** `private` `static` `String`

One HttpClient is created, shared between all HttpRequests./ 
private static final HttpClient client = HttpClient.newHttpClient(); 
/Current Server name is set at the beginning. Currently called "localhost". 











## [NOTFOUNDSTATUS](../src/main/java/uk/ac/ed/inf/aqmaps/JsonParser.java#L28)

**Type:** `private` `static` `final` `int`

The HTTP Response code that indicates that the server could not find the page requested. 











## [checkResponseNotFound](../src/main/java/uk/ac/ed/inf/aqmaps/JsonParser.java#L32)

**Type:** `private` `static` `boolean`

Helper method that checks the HTTP Status code returned by the HTTP response. 




|Parameter Name|Description|
|-----|-----|
|status|The response code.|


**Returned Value:** true if status code is 404.   








## [getBodyContent](../src/main/java/uk/ac/ed/inf/aqmaps/JsonParser.java#L41)

**Type:** `private` `static` `String`

Helper function that returns the body content of a JSON file formatted as a JSON String. 




|Parameter Name|Description|
|-----|-----|
|portNumber|The port number, should be equivalent to the port number that the server is in in order to have a successful connection.|
|path|The file path on the webserver to retrieve the required body content.|


**Returned Value:** Returns the Body content found from the JSON File.  








## [parseWhat3WordsDetails](../src/main/java/uk/ac/ed/inf/aqmaps/JsonParser.java#L94)

**Type:** `public` `static` `What3WordsDetails`

Parses JSON content related to the location of a sensor and converts it to a new object of type What3WordDetails 




|Parameter Name|Description|
|-----|-----|
|portNumber|The port number which the server is in.|
|what3WordsLocation|The String location of the sensor, with each word separated by a dot.|


**Returned Value:** What3WordsDetails Object with attributes conveying information about the location of a sensor.  








## [changeLocationFormatToFilePath](../src/main/java/uk/ac/ed/inf/aqmaps/JsonParser.java#L110)

**Type:** `private` `static` `String`

Removes the dots in the String to a forward slash to represent the format of a file path for our webserver 




|Parameter Name|Description|
|-----|-----|
|w3w|The what3Words location in a dot format. |


**Returned Value:** A New String with the format of a file path (with forward slashes).  








## [Sensor](../src/main/java/uk/ac/ed/inf/aqmaps/JsonParser.java#L119)

**Type:** `public` `static`

Parses JSON content with air quality readings of different sensors and their location and converts them to Sensor objects. 




|Parameter Name|Description|
|-----|-----|
|portNumber|The port number which the server is in.|


**Returned Value:** An array of Sensors  








## [parseNoFlyZones](../src/main/java/uk/ac/ed/inf/aqmaps/JsonParser.java#L136)

**Type:** `public` `static` `NoFlyZone`

Parses JSON content related to Off Limit Zones, zones inside the confinement areas that the drone should not fly over . 




|Parameter Name|Description|
|-----|-----|
|portNumber|The port number which the server is in.|


**Returned Value:** A new object of type NoFlyZone  








