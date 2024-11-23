// package com.gradescope.wordsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordSearch {

    public static void main(String[] args) {
        // exit program if incorrect number of arguments
        if (args.length != 1) {
            System.out.println("Usage: java WordSearch <input_file>");
            return;
        }

        String inputFileName = args[0];
        try {
            Grid grid = setUpGame(inputFileName);
            String outputFileName = "output_" + inputFileName;
            writeOutputFile(outputFileName, grid);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }

    private static Grid setUpGame(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] dimensions = reader.readLine().split(" ");
            int rows = Integer.parseInt(dimensions[1]);
            int cols = Integer.parseInt(dimensions[0]);
            Grid grid = new Grid(rows, cols);

            String word;
            List<String> words = new ArrayList<>();
            while ((word = reader.readLine()) != null) {
                // use word = in the if statement to make code cleaner
                words.add(word.toUpperCase()); // Convert words to uppercase
            }

            grid.fillGridWithWords(words);
            return grid;
        }
    }

    private static void writeOutputFile(String filename, Grid grid) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(grid.toString());
        }
    }
}
