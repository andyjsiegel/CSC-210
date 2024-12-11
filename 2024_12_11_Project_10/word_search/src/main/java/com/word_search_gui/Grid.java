package com.word_search_gui;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Grid {
    public char[][] grid;
    public int rows;
    public int cols;
    private Random random;
    private boolean noobMode = false;
    public HashMap<String, Boolean> wordsFound = new HashMap<String, Boolean>();

    public Grid(int rows, int cols) {
        this.rows = rows+1;
        this.cols = cols;
        this.grid = new char[rows+1][cols];
        this.random = new Random();
        fillEmptyGrid();
    }
    public Grid(int rows, int cols, boolean noobMode) {
        this.rows = rows;
        this.cols = cols+1;
        this.grid = new char[rows][cols+1];
        this.random = new Random();
        this.noobMode = noobMode;
        fillEmptyGrid();
    }

    public char[][] getGrid() {
        return grid;
    }

    private void fillEmptyGrid() {
        // Fill the grid with spaces
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(i == 0) {
                    grid[i][j] = (char) ('a' + j);
                } else {
                    grid[i][j] = ' '; 
                }
            }
        }
    }

    public void fillGridWithWords(List<String> words) {
        // for every word, place it in the grid and add it to the wordsFound map
        for (String word : words) {
            wordsFound.put(word, false);
            placeWord(word);
        }
        fillRandomLetters(noobMode);
    }

    private void placeWord(String word) {
        // place a word in the grid
        boolean placed = false;
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
        // helper function to check if a word can be placed in a given direction
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
            if (row + word.length() > rows || col + word.length() > cols) return false; 
            for (int i = 0; i < word.length(); i++) {
                if (grid[row + i][col + i] != ' ') return false; // Check for overlap
            }
        }
        return true;
    }

    private void fillRandomLetters(boolean noobMode) {
        // replace spaces with random letters
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == ' ') {
                    if(noobMode) {
                        /* if noob mode is on, grid filled with lowercase letters
                        while words are uppercase making it trivial to find words */
                        grid[i][j] = (char) ('a' + random.nextInt(26)); 
                    } else {
                        grid[i][j] = (char) ('A' + random.nextInt(26)); 
                    }
                }
            }
        }
    }

    public boolean findWord(String word, int x, int y, char direction) {
        // function to check if a word is present in the grid using the given direction
        switch(direction) {
            case 'h':
                return findHorizontal(word, x, y);
            case 'v':
                return findVertical(word, x, y);
            case 'd':
                return findDiagonal(word, x, y);
            default:
                System.out.println("Invalid direction");
                return false;
        }
    }

    private boolean findHorizontal(String word, int x, int y) {
        if (y + word.length() > cols) return false; // Check boundary
        for (int i = 0; i < word.length(); i++) {
            if (grid[x][y + i] != word.charAt(i)) {
                return false; // character mismatch
            }
        }
        wordsFound.put(word, true);
        return true; // Word found
    }

    private boolean findVertical(String word, int x, int y) {
        if (x + word.length() > rows) return false; // Check boundary
        for (int i = 0; i < word.length(); i++) {
            if (grid[x + i][y] != word.charAt(i)) {
                return false; // Mismatch
            }
        }
        wordsFound.put(word, true);
        return true; // Word found
    }

    private boolean findDiagonal(String word, int x, int y) {
        if (x + word.length() > rows || y + word.length() > cols) return false; // Check boundary
        for (int i = 0; i < word.length(); i++) {
            if (grid[x + i][y + i] != word.charAt(i)) {
                return false; // Mismatch
            }
        }
        wordsFound.put(word, true);
        return true; // Word found
    }

    public void removeWord(String word, int x, int y, char direction) {
        // function to remove a word from the grid using the given direction
        // by replacing each character with a '*'
        switch(direction) {
            case 'h':
                removeHorizontal(word, x, y);
                break;
            case 'v':
                removeVertical(word, x, y);
                break;
            case 'd':
                removeDiagonal(word, x, y);
                break;
            default:
                System.out.println("Invalid direction");
                break;
        }
    }
    
    private void removeHorizontal(String word, int x, int y) {
        for (int i = 0; i < word.length(); i++) {
            grid[x][y + i] = '*'; 
        }
    }
    
    private void removeVertical(String word, int x, int y) {
        for (int i = 0; i < word.length(); i++) {
            grid[x + i][y] = '*'; 
        }
    }
    
    private void removeDiagonal(String word, int x, int y) {
        for (int i = 0; i < word.length(); i++) {
            grid[x + i][y + i] = '*'; 
        }
    }
    
    public boolean isGameOver() {
        Collection<Boolean> booleans = wordsFound.values(); // Get values from the map
        HashSet<Boolean> bools = new HashSet<>(booleans); // Create a set from the values
        
        //if the set is entirely true, then the game is over
        if (bools.size() == 1 && bools.contains(true)) {
            return true;
        }
        
        return false; // Return false if conditions are not met
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
