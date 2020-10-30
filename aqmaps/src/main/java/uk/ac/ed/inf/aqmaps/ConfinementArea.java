package uk.ac.ed.inf.aqmaps;

public class ConfinementArea {

	/*
	 * The numbers describe the range of values that the latitudes and longitudes could take
	 * The drones must stay strictly within these limits. 
	 * A drone at one of the limit locations or outside of the ranges will be considered as malfunctioning.
	 */
	final double NORTHERN_BOUNDARY = 55.946233; //Currently latitude for Forrest Hill and KFC
    final double SOUTHERN_BOUNDARY = 55.942617; //Currently latitude for Top of the Meadows and Buccleuch St. Bus Stop
    final double WESTERN_BOUNDARY = -3.192473;  //Currently Longitude for Forrest Hill and Top of the Meadows
    final double EASTERN_BOUNDARY = -3.184319;  //Currently Longitude for KFC and Buccleuch St. Bus Stop
    final double RANGE_LONGITUDE = Math.abs(EASTERN_BOUNDARY - WESTERN_BOUNDARY); //width of the area enclosed by the boundaries
    final double RANGE_LATITUDE =  Math.abs(NORTHERN_BOUNDARY - SOUTHERN_BOUNDARY);	//height of the area enclosed by the boundaries
	
	
	
	
	
}
