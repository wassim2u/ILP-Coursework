package uk.ac.ed.inf.aqmaps;

//TODO: Set getters and change access modifiers to private

public class What3WordsDetails {
	
	String country;
	W3WSquare square;
	String nearestPlace;
	private Coordinates coordinates; 
	String words; 
	String language;
	String map;
	
	
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
