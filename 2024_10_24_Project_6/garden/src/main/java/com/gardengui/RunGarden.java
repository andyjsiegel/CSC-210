package com.gardengui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RunGarden {

    public static Garden createGardenFromFile(String filePath) throws FileNotFoundException {
        // System.out.println("garden being created...");
        Scanner fileReader = new Scanner(new File(filePath));
        int rows = Integer.valueOf(fileReader.nextLine().split(" ")[1]);
        int columns = Integer.valueOf(fileReader.nextLine().split(" ")[1]);
        // double delay = Double.valueOf(fileReader.nextLine().split(" ")[1]);
        // System.out.println("rows: " + rows + " -- cols: "+ columns + " -- delay: " + delay);
        if (columns > 16) {
            System.out.println("Too many plot columns.");
            fileReader.close();
            return null; // Handle this case as needed
        } else {
            // System.out.println("garden is viable size");
            Garden garden = new Garden(rows, columns);
            fileReader.close();
            return garden;
            // Set the garden in the controller before parsing commands
            // controller.setGarden(garden); // Set the garden here

            // while (fileReader.hasNextLine()) {
            //     String line = fileReader.nextLine();
            //     // controller.parseGardenCommand(line); // Update garden with command and refresh UI
            //     try {
            //         Thread.sleep((long) delay*1000);
            //     } catch (InterruptedException e) {

            //     }
            // }
            // fileReader.close();
            // return garden;
        }
    }

    public static void main(String[] args) {
        // This is just for running the application in a console
        // In JavaFX, you will not use this main method
    }
}
