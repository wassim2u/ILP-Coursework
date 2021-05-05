# Planner System for Autonomous Drone Collecting Sensor Readings in a Map



The repository contains two folders, _aqmaps_ and _heatmap_ for the two separate marked courseworks for the **Informatics Large Practical** Course. The bulk of the semester was spent on aqmaps for implementing a drone route planner algorithm. Achieved **89%** overall in the course.

 - _heatmap_: 23/25 - Visualising a “heat map” over the drone confinement area through Geo-JSON maps.
  
 - _aqmap_: 66/75 - The task is to program an autonomous drone which will collect readings from air quality sensors distributed around an urban geographical area as part of a (fictitious) research project to analyse urban air quality. The drone must reach all required sensors in a map in a given area provided some constraints including the number of maximal movements allowed and while avoiding no-fly-zones, areas which the drone is not allowed to cross.

The _ilp-report.pdf_ was written at the end to document the project. It contains description of the Software Architecture of the program, brief overview on the class documentation used generated by Javadoc comments written through code, and the drone control algorithm

## Running Webserver
To run the local web server, use the command

_java -jar WebServerLite.jar_

This will start the web server on the default port (80) and it will serve the content in the maps, words, and
buildings folders. You can check the web server is running by visiting the address http://localhost:80 in your preferred web browser.

- _maps_ folder: The folder contains files containing lists of sensors to be visited, one for each day in the years 2020 and 2021.

- _words_ folder: The folder contains JSON files which give the What3Words information corresponding to a What3Words address. The details for a What3Words address "first.second.third" will be stored in the file words/first/second/third/details.json.

- _buildings_ folder: Within the confinement area are four regions where the drone is not allowed to fly. These four regions are known as the No Fly Zones for the drone. The details of the No Fly Zones are given in the file buildings/no-fly-zones.geojson. This file is in Geo-JSON format, which was used as a way to encode geographic data structures.
