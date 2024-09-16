// package com.gradescope.airportinfo;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class AirportInfo {

    public static HashMap<String, String> getDestinations(String fileName) throws FileNotFoundException {
        File flightsCSV = new File(fileName);
        Scanner fileReader = new Scanner(flightsCSV);
        HashMap<String, String> destinations = new HashMap<String, String>();
        fileReader.nextLine(); // skip headers

        while(fileReader.hasNextLine()) {
            String dataLine = fileReader.nextLine();
            String[] values = dataLine.split(",");
            String sourceAirport = values[2];
            String destinationAirport = values[4];
            String currentDestinations = destinations.get(sourceAirport);
            if (currentDestinations == null) {
                currentDestinations = "";
            }
            destinations.put(sourceAirport, currentDestinations += " " + destinationAirport); 
        }
        fileReader.close();
        return destinations;
    }
    
    public static HashMap<String, Integer> getAirportCount(String fileName) throws FileNotFoundException {
        File flightsCSV = new File(fileName);
        Scanner fileReader = new Scanner(flightsCSV);
        HashMap<String, Integer> airportCount = new HashMap<String, Integer>();
        fileReader.nextLine(); // skip headers
        while(fileReader.hasNextLine()) {
            String dataLine = fileReader.nextLine();
            String[] values = dataLine.split(",");
            String sourceAirport = values[2];
            String destinationAirport = values[4];

            Integer numFlightsDeparting = airportCount.get(sourceAirport);
            Integer numFlightsArriving = airportCount.get(destinationAirport);

            if(numFlightsArriving == null) numFlightsArriving = 0;
            if (numFlightsDeparting == null) numFlightsDeparting = 0;

            airportCount.put(sourceAirport, numFlightsDeparting + 1);
            airportCount.put(destinationAirport, numFlightsArriving + 1);
        }
        fileReader.close();
        return airportCount;
    }

    public static String getDepartures(HashMap <String, String> destinations) {
        ArrayList<String> sortedKeys = new ArrayList<String>(destinations.keySet());
        Collections.sort(sortedKeys);
        String departuresString = "";
        for(String key: sortedKeys) {
            departuresString += key + " flies to " + destinations.get(key).trim() + "\n";
        }
        return departuresString;
    }
    
    public static String getMax(HashMap<String, Integer> airportCount) {
        int max = Integer.MIN_VALUE;
        String maxString = "";
        for (String key : airportCount.keySet()) {
            if(airportCount.get(key) > max) {
                max = airportCount.get(key);
                maxString = "MAX FLIGHTS " + max + " : " + key;
            } 
            else if(airportCount.get(key) == max) {
                maxString += "\nMAX FLIGHTS " + max + " : " + key;
            }
            // if value < max, do nothing
        }
        List<String> linesList = Arrays.asList(maxString.split("\n"));
        Collections.sort(linesList);
        String sortedLines = String.join("\n", linesList);
        return sortedLines;

    }
    
    public static String getLimits(int limit, HashMap<String, Integer> airportCount) {
        String limitedString = "";
        for (String key : airportCount.keySet()) {
            if(airportCount.get(key) > limit) {
                limitedString += key + " - " + airportCount.get(key) + "\n";
            }
        }
        List<String> linesList = Arrays.asList(limitedString.split("\n"));
        Collections.sort(linesList);
        String sortedLines = String.join("\n", linesList);
        return sortedLines;
    }
    public static void main(String[] args) throws FileNotFoundException {
        /* LOCAL TESTING CODE 
        HashMap<String, String> destinations = getDestinations("2024_09_11_HashMaps/routes.csv");
        HashMap<String, Integer> airportCount = getAirportCount("2024_09_11_HashMaps/routes.csv");
        System.out.println(getDepartures(destinations));
        System.out.println(getMax(airportCount));
        System.out.println(getLimits(Integer.valueOf(700), airportCount));
        */

        HashMap<String, String> destinations = getDestinations(args[0]);
        HashMap<String, Integer> airportCount = getAirportCount(args[0]);
        
        if (args[1].equals("MAX")) {
            System.out.println(getMax(airportCount));
        }
        
        if (args[1].equals("DEPARTURES")) {
            System.out.println(getDepartures(destinations));
        }
        
        if (args[1].equals("LIMIT")) {
            System.out.println(getLimits(Integer.valueOf(args[2]), airportCount));
        }
        
    }
}
