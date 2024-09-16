// package com.gradescope.lab03;
import java.util.ArrayList;

public class Lab3Dice {

    public static void roll(int digits, String number, ArrayList<String> result) {
        if (digits == 0) {
            result.add(number);
        } else {
            for (int n = 1; n < 7; n++) {
                roll(digits - 1, number + n, result);
            }
            
        }
    }
    
    public static void main(String[] args) {
        ArrayList<String> allSolutions = new ArrayList<String>();
        String solution = "";
        roll(2, solution, allSolutions);
        System.out.println(allSolutions);
    }
}
