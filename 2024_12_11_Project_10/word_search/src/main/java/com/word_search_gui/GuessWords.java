package com.word_search_gui;

import java.util.Scanner;

public class GuessWords {
    
    private Grid grid;
    private boolean gameOver = false;

    public GuessWords(Grid grid) {
        this.grid = grid;
    }

    public void play() {
        Scanner guesser = new Scanner(System.in);
        while(!gameOver) {
            printGridWithCoordinates(grid);
            System.out.print("Enter word found: " );
            String word = guesser.nextLine();
            System.out.print("Enter x: ");
            String x = guesser.nextLine();
            int coordx = Integer.parseInt(x);
            System.out.print("Enter y: ");
            char y = guesser.nextLine().toLowerCase().charAt(0);
            int coordy = (int) y - 'a';
            System.out.print("[H]orizontal [V]ertical [D]iagonal? ");
            char direction = guesser.nextLine().toLowerCase().charAt(0);
            
            if(grid.findWord(word.toUpperCase(), coordx, coordy, direction)) {
                System.out.println("\n" + word + " removed");
                grid.removeWord(word.toUpperCase(), coordx, coordy, direction);
                gameOver = grid.isGameOver();
            } else {
                System.out.println(word + " not found");
            }
        }
        guesser.close();
        System.out.println("\nAll words found!");
    }

    private static void printGridWithCoordinates(Grid grid) {
        String[] lines = grid.toString().split("\n");
        int cols = lines[0].split(" ").length;
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for(int i = 0; i < cols; i++) {
            sb.append((char) ('a' + i));
            sb.append(" ");
        }
        System.out.println(sb.toString());
        for(int i = 0; i < lines.length; i++) {
            System.out.printf("%02d %s%n", i, lines[i]);
        }
    }
}
