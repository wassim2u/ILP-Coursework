package uk.ac.ed.inf.aqmaps;

import com.mapbox.geojson.FeatureCollection;

public class NoFlyZone {
	public FeatureCollection noFlyZonesCollection;
	
	public NoFlyZone(String geoJsonSource) {
		noFlyZonesCollection = FeatureCollection.fromJson(geoJsonSource);
	}
	
	
	
}
