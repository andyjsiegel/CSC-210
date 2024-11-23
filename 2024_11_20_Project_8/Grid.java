// package com.gradescope.wordsearch;

import java.util.List;
import java.util.Random;

public class Grid {
    private char[][] grid;
    private int rows;
    private int cols;
    private Random random;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        this.random = new Random();
        fillEmptyGrid();
    }

    private void fillEmptyGrid() {
        // Fill the grid with spaces
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = ' '; 
            }
        }
    }

    public void fillGridWithWords(List<String> words) {
        for (String word : words) {
            placeWord(word);
        }
        fillRandomLetters();
    }

    private void placeWord(String word) {
        boolean placed = false;
        boolean placedDiagonally = false;
        boolean placedVertically = false;
        boolean placedHorizontally = false;
        int attempts = 0;
        while (!placed && attempts < 100) { // Limit attempts to avoid infinite loop
            int direction = random.nextInt(3); // 0: horizontal, 1: vertical, 2: diagonal
            int row = random.nextInt(rows); // pick a random row
            int col = random.nextInt(cols); // pick a random column

            if (canPlaceWord(word, row, col, direction)) {
                for (int i = 0; i < word.length(); i++) {
                    if (direction == 0) {
                        grid[row][col + i] = word.charAt(i); // Horizontal
                    } else if (direction == 1) {
                        grid[row + i][col] = word.charAt(i); // Vertical
                    } else if (direction == 2) {
                        grid[row + i][col + i] = word.charAt(i); // Diagonal
                    }
                }
                placed = true;
            }
            attempts++;
        }
    }

    private boolean canPlaceWord(String word, int row, int col, int direction) {
        if (direction == 0) { // Horizontal
            if (col + word.length() > cols) return false; // if the column index + word length is greater than the number of columns, return false
            for (int i = 0; i < word.length(); i++) {
                if (grid[row][col + i] != ' ') return false; // if any of the letters in the word overlap with an existing letter, return false
            }
        } else if (direction == 1) { // Vertical
            if (row + word.length() > rows) return false; // if the row index + word length is greater than the number of rows, return false
            for (int i = 0; i < word.length(); i++) {
                if (grid[row + i][col] != ' ') return false; // Check for overlap
            }
        } else if (direction == 2) { // Diagonal
            /* if the row index + word length is greater than the 
            number of rows or the column index + word length is 
            greater than the number of columns, return false – both
            conditions must be true for a word to be placed diagonally */
            if (row + word.length() > rows || col + word.length() > cols) return false; 
            for (int i = 0; i < word.length(); i++) {
                if (grid[row + i][col + i] != ' ') return false; // Check for overlap
            }
        }
        return true;
    }

    private void fillRandomLetters() {
        // replace spaces with random letters
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == ' ') {
                    // use ascii code to generate random letter
                    grid[i][j] = (char) ('A' + random.nextInt(26)); 
                }
            }
        }
    }

    @Override
    public String toString() {
        // use stringbuilder instead of concatenation for better memory management
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            for (char c : row) {
                sb.append(c).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
