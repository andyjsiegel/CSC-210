
// package com.gradescope.garden;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class RunGarden {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File(args[0]));
        int rows = Integer.valueOf(fileReader.nextLine().split(" ")[1]);
        int columns = Integer.valueOf(fileReader.nextLine().split(" ")[1]);
        if (columns > 16) {
            System.out.println("Too many plot columns.");
        } else {
            Garden garden = new Garden(rows, columns);
            fileReader.nextLine(); // skip empty line
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String words[] = line.split(" ");
                words[0] = words[0].toUpperCase();
                String result = String.join(" ", words);
                if (result.contains("PRINT")) {
                    System.out.println("> " + result);
                } else if (result.contains("PLANT")) {
                    // Do nothing
                } else {
                    System.out.println("> " + result + "\n");
                }

                garden.parseCommand(line);
            }
            fileReader.close();
        }
    }
}
