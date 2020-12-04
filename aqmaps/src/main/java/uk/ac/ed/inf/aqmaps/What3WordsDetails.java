package uk.ac.ed.inf.aqmaps;

/**
 * Template class for the what3words details which will be retrieved from the webserver and converted from JSON format to this type.
 * The attributes are the What3Words information corresponding to a What3Words address.
 */
public class What3WordsDetails {
	
	private String country;
	private W3WSquare square;
	private String nearestPlace;
	private Coordinates coordinates; 
	private String words; 
	private String language;
	private String map;
	
	
	public static class W3WSquare{
		SouthWestPoint southwest;
		NorthEastPoint northeast;
		
		public static class SouthWestPoint{
			double lng;
			double lat;
		}
		private class NorthEastPoint{
			double lng;
			double lat;
		}
	}
	
	public static class Coordinates{
		double lng;
		double lat;
	}
	
	//Getters and Setters
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	

}
