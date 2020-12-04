package uk.ac.ed.inf.aqmaps;

import java.io.FileWriter;
import java.io.IOException;

public final class FileUtilities {
	
	
	public static void createOrAppendToFile(String StringtoAdd, String filename, boolean isNewFile) throws IOException {
		FileWriter myWriter;
		if(isNewFile) {
			myWriter = new FileWriter(filename);
		}
		else {
			var appendToExistingFile = true;
			myWriter = new FileWriter(filename, appendToExistingFile);
		}
    	myWriter.write(StringtoAdd);
    	myWriter.close();
    	if (isNewFile) {
        	System.out.println("Successfully created " + filename + " file.");
    	}
    	
	}
	
}
