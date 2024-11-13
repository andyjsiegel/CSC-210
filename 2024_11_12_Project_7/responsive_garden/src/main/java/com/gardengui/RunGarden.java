package com.gardengui;

import java.util.Arrays;

public class RunGarden {

    public static Garden createGardenFromRowsCols(int rows, int columns) {
        System.out.println("garden creation method running");
        Garden garden = new Garden(rows, columns);
        return garden;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide the number of rows and columns for the garden.");
            return;
        }

        int rows = Integer.parseInt(args[0]);
        int columns = Integer.parseInt(args[1]);
    }
}
