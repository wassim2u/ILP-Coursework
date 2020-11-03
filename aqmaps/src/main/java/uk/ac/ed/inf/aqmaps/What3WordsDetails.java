package uk.ac.ed.inf.aqmaps;

public class What3WordsDetails {
	
	String country;
	W3WSquare Square;
	String nearestPlace;
	Coordinates buildingCoordinates;
	String threeWords; 
	String language;
	String mapURL;
	
	
	public static class W3WSquare{
		SouthWestPoint SWPoint;
		NorthEastPoint NEPoint;
		
		public static class SouthWestPoint{
			double longitude;
			double latitude;
		}
		private class NorthEastPoint{
			double longitude;
			double latitude;
		}
	}
	
	public static class Coordinates{
		double longitude;
		double latitude;
	}
	
	

}
