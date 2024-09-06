// This program takes in a file using the Scanner of grades in categories with
// weights and calculates the final grade from that. A file name is requested.

// package com.gradescope.gradenator; 
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



public class Gradenator {

    public static double getAverage(String[] numbers) {
        double sum = 0;
        for (String num : numbers) {
            sum += Double.valueOf(num);
        }
        return (sum / numbers.length);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("File name?");
        String fileName = in.nextLine();
        in.close();
        File gradeBook = new File(fileName);
        Scanner gradeScanner = new Scanner(gradeBook);
        double weightsSum = 0;
        double finalGrade = 0;
        while(gradeScanner.hasNextLine()) {
            String line = gradeScanner.nextLine();
            String[] components = line.trim().split(";");
            // components[0] = grades of assignments
            // components[1] = category label
            // components[2] = weight
            String[] numbers = components[0].split(" ");
            String categoryLabel = components[1].trim();
            String weight = components[2].replace("%", "").trim();
            Double weightDecimal = Double.valueOf(weight) / 100;
            String percentage = (weightDecimal*100) + "%";
            weightsSum += weightDecimal;
            double average = getAverage(numbers);
            finalGrade += average * weightDecimal;
            System.out.format("%s; %s; avg=%.1f%n", categoryLabel, percentage, average);
        }
        System.out.format("TOTAL = %.1f%% out of %.1f%%", finalGrade, weightsSum * 100);
        gradeScanner.close();
        
    }
}
