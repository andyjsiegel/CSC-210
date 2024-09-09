// SHORT ASSIGNMENT 02
// package com.gradescope.medalinfo;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class MedalInfo {

    public static HashMap<String, Integer> getMedalCount(String fileName) throws FileNotFoundException {
        HashMap<String, Integer> medalCount = new HashMap<String, Integer>();
        File medalsCSV = new File(fileName);
        Scanner medalScanner = new Scanner(medalsCSV);
        medalScanner.nextLine(); // skip headers
        while(medalScanner.hasNextLine()) {
            String dataLine = medalScanner.nextLine();
            String[] values = dataLine.split(",");
            String countryName = values[6];
            // use Integer vs int for nullability
            Integer countryCount = medalCount.get(countryName);

            if(countryCount == null) countryCount = 0;

            medalCount.put(countryName, countryCount + 1);

        }
        medalScanner.close();
        
        return medalCount;
    }
    
    public static String getMax(HashMap<String, Integer> medalCount) {
        int maxCount = 0;
        String countryName = "";
        for (String key : medalCount.keySet()) {
            int numMedals = medalCount.get(key);
            if (numMedals > maxCount) {
                maxCount = numMedals;
                countryName = key;
            }        
        }
        return countryName + " had the most medals with a total of " + maxCount + " medals.";
    }
    public static String getMin(HashMap<String, Integer> medalCount) {
        int minCount = Integer.MAX_VALUE;
        String countryName = "";
        for (String key : medalCount.keySet()) {
            int numMedals = medalCount.get(key);
            if (numMedals < minCount) {
                minCount = numMedals;
                countryName = key;
            }        
        }
        String plural = " medal";
        plural += (minCount == 1) ? "." : "s.";
        return countryName + " had the fewest medals with a total of " + minCount + plural;
    }

    public static String getCountry(HashMap<String, Integer> medalCount, String countryName) {
        Integer theCount = medalCount.get(countryName);
        String plural = " medal";
        plural += (theCount == 1) ? "." : "s.";
        return countryName + " had a total of " + theCount + plural;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // local-testing code:
        // HashMap<String, Integer> countryCount = getMedalCount("2024_09_09_Arrays/medalists.csv");
        // System.out.println(getMax(countryCount));
        // System.out.println(getMin(countryCount));
        // System.out.println(getCountry(countryCount, "Brazil"));

        HashMap<String, Integer> countryCount = getMedalCount("medallists.csv");
        
        if (args[0].equals("MAX")) {
            System.out.println(getMax(countryCount));
        }
        
        if (args[0].equals("MIN")) {
            System.out.println(getMin(countryCount));
        }
        
        if (args[0].equals("COUNTRY")) {
            System.out.println(getCountry(countryCount, args[1]));
        }
        

    }
}
