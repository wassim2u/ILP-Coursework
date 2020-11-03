package uk.ac.ed.inf.aqmaps;

public class What3WordsDetails {
	
	private String country;
	private W3WSquare Square;
	private String nearestPlace;
	private Coordinates buildingCoordinates;
	private String threeWords; 
	private String language;
	private String mapURL;
	
	
	private class W3WSquare{
		private SouthWestPoint SWPoint;
		private NorthEastPoint NEPoint;
		
		private class SouthWestPoint{
			private double longitude;
			private double latitude;
		}
		private class NorthEastPoint{
			private double longitude;
			private double latitude;
		}
	}
	
	private class Coordinates{
		private double longitude;
		private double latitude;
	}
	
	

}
