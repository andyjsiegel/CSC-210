// package com.gradescope.wordsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: Andy Siegel
 * Class: CSC 210
 * Semester: Fall 2024
 */ 

// input file is a text file with the following format:

/* rows columns
   word1
   word2
   ... */

public class WordSearch {

    public static void main(String[] args) {
        // exit program if incorrect number of arguments
        if (args.length != 1) {
            System.out.println("Usage: java WordSearch.java <input_file>");
            return;
        }

        String inputFileName = args[0];
        try {
            Grid grid = setUpGame(inputFileName);
            GuessWords guessingGame = new GuessWords(grid);
            guessingGame.play();
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        }
    }

    private static Grid setUpGame(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String[] dimensions = reader.readLine().split(" ");
            int rows = Integer.parseInt(dimensions[1]);
            int cols = Integer.parseInt(dimensions[0]);
            // Grid grid = new Grid(rows, cols);
            Grid grid = new Grid(rows, cols, true);

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

    
}
