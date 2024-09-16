// package com.gradescope.lab03;
import java.util.ArrayList;

public class Lab3Binary {

    public static void binary(int digits, String number, ArrayList<String> array) {
        if(digits == 0) {
            array.add(number);
        } else {
            binary(digits - 1, number + "0", array);
            binary(digits - 1, number + "1", array);
        }
    }
    public static void main(String[] args) {
        ArrayList<String> allSolutions = new ArrayList<String>();
        binary(5, "", allSolutions);
        System.out.println(allSolutions);
    }
}
