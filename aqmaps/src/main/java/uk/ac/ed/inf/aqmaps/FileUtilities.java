package uk.ac.ed.inf.aqmaps;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class that handles functionalities related to file handling, whether creating, writing, or reading a file. 
 */
public final class FileUtilities {
	
	/**
	 * Function that creates a new file or appends to an existing file and writes to it the String value passed in the argument. 
	 * Note that setting isNewFile to be true deletes any existing file with the same name.
	 * @param StringtoAdd The piece of String text to be added to the file
	 * @param filename The name of the file to be written to (can be existing or new)
	 * @param isNewFile boolean value on whether the file exists or not. A false value will create a new file. If the file with the same name already exists, it will overwrite it.
	 * @param extensionType The extension type of the file to be xreated. Eg. txt, geojson. Do not add a dot to the parameter value, this is done automatically.
	 * @throws IOException if something goes wrong during the process of writing to a file.
	 */
	public static void createOrAppendToFile(String StringtoAdd, String filename, String extensionType, boolean isNewFile) throws IOException {
		var fileWithExtension = filename + "." + extensionType;
		FileWriter myWriter;
		if(isNewFile) {
			myWriter = new FileWriter(fileWithExtension);
		}
		else {
			var appendToExistingFile = true;
			myWriter = new FileWriter(fileWithExtension, appendToExistingFile);
		}
    	myWriter.write(StringtoAdd);
    	myWriter.close();
    	if (isNewFile) {
        	System.out.println("Successfully created " + fileWithExtension + " file.");
    	}
    	
	}
	
}
