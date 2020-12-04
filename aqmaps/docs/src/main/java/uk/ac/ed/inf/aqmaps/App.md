# [App](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L12)

**Type:** `public` `class`

Represents the application interface, and is the starting point of the program. 
The class is mainly used to check the argument values passed and store them for the duration of the program. 












## [numberOfExpectedArguments](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L19)

**Type:** `private` `static` `int`

The number of arguments expected when starting the application from command line. Needs to be changed if there becomes different requirements 
or if there is a need for flexibility. 












## [date](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L23)

**Type:** `private` `static` `LocalDate`

The date given in the arguments. Used later for accessing the list of sensors that need to be visited on a given date. 











## [startPoint](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L25)

**Type:** `private` `static` `Point`

The starting point of the drone given in the arguments. 











## [randomSeed](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L27)

**Type:** `private` `static` `int`

The random seed number given in the arguments. Current implementation does not make use of it as the program returns the same results. 











## [portNumber](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L29)

**Type:** `private` `static` `int`

The port number given in the arguments. Needs to match with the webserver's port number in order to connect and parse information from it successfully. 











## [checkNumOfArguments](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L43)

**Type:** `private` `static` `void`

This function checks the number of arguments and would exit the program if the number is not equal to the value defined at the 
variable numberOfExpectedArguments. 




|Parameter Name|Description|
|-----|-----|
|args|The arguments passed in the terminal|




|Exception|Description|
|-----|-----|
|IllegalArgumentException|if the number of arguments do not match up with the required number set in numberOfExpectedArguments.  |





## [readDateFromArguments](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L62)

**Type:** `private` `static` `LocalDate`

Reads the date values from the arguments in the format DD MM YY. 




|Parameter Name|Description|
|-----|-----|
|dayString|The day passed in the argument|
|monthString|The month passed in the argument|
|yearString|The year passed in the argument|


**Returned Value:** Returns LocalDate containing the three parameters passed.  




|Exception|Description|
|-----|-----|
|NumberFormatException|if the program fails to parse from String to Integer if the string values given are not numbers (eg. Will accept 06, but not June). |





## [createStartingPoint](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L85)

**Type:** `private` `static` `Point`

Reads the longitude and latitude values passed and creates a new Point to encapsulate both values. 




|Parameter Name|Description|
|-----|-----|
|latString|The latitude passed in the arguments|
|longString|The longitude passed in the arguments|


**Returned Value:** Returns Point object containing the two parameters passed of longitude and latitude.  




|Exception|Description|
|-----|-----|
|IllegalArgumentException|if the values given were invalid (eg. Outside the confinement area or inside one of the no fly zones). |





## [initialiseSeed](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L111)

**Type:** `private` `static` `int`

Reads the random seed value passed in the arguments to stores it in the program. 




|Parameter Name|Description|
|-----|-----|
|seedString|the random seed value|


**Returned Value:** value of type int representing the random seed.  




|Exception|Description|
|-----|-----|
|NumberFormatException|if the value given were invalid (Not an integer number).|





## [readPortNumber](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L128)

**Type:** `private` `static` `int`

Reads the port number value passed in the arguments to parse it into integer and store it in the program. 




|Parameter Name|Description|
|-----|-----|
|portString|the port number passed in the arguments. |


**Returned Value:** value of type int representing the port number.  




|Exception|Description|
|-----|-----|
|NumberFormatException|if the value given were invalid (Not an integer number).|





## [exitArgumentError](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L148)

**Type:** `private` `static` `void`

Ran when the arguments passed are not in the correct format. Notifies the user of the format to input the arguments. 












## [addLeadingZeroToString](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L181)

**Type:** `private` `static` `String`

Add one leading zero if the number is less than 10. 
Used for parsing air quality data. This is to match the String date formats in the webserver to access the json file successfully in the maps folder. 




|Parameter Name|Description|
|-----|-----|
|numString|A number in String type.|


**Returned Value:** return the new String with a leading zero if it is a single digit. Otherwise, it returns the same String value passed in argument.  








## [isSingleDigit](../src/main/java/uk/ac/ed/inf/aqmaps/App.java#L195)

**Type:** `private` `static` `boolean`

Check if it is a single digit. Used for parsing air quality data by matching up the format in the webserver to access the json file needed using dates successfully. 




|Parameter Name|Description|
|-----|-----|
|number|A number of type int.|


**Returned Value:** True if it is a single digit. Otherwise, return false.  








