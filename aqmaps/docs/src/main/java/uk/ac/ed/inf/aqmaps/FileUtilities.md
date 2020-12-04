# [FileUtilities](../src/main/java/uk/ac/ed/inf/aqmaps/FileUtilities.java#L7)

**Type:** `public` `final` `class`

Utility class that handles functionalities related to file handling, whether creating, writing, or reading a file. 












## [createOrAppendToFile](../src/main/java/uk/ac/ed/inf/aqmaps/FileUtilities.java#L12)

**Type:** `public` `static` `void`

Function that creates a new file or appends to an existing file and writes to it the String value passed in the argument. 
Note that setting isNewFile to be true deletes any existing file with the same name. 




|Parameter Name|Description|
|-----|-----|
|StringtoAdd|The piece of String text to be added to the file|
|filename|The name of the file to be written to (can be existing or new)|
|isNewFile|boolean value on whether the file exists or not. A false value will create a new file. If the file with the same name already exists, it will overwrite it.|
|extensionType|The extension type of the file to be xreated. Eg. txt, geojson. Do not add a dot to the parameter value, this is done automatically.|




|Exception|Description|
|-----|-----|
|IOException|if something goes wrong during the process of writing to a file.  |





