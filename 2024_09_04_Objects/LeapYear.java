// package com.gradescope.leapyear;
import java.util.Scanner;

public class LeapYear {

    public static String isLeap(int year) {
        if ((year % 4 == 0) && (year % 100 != 0)) {
            return "Leap Year";
        }
        else if ((year % 400 == 0)) {
            return "Leap Year";
        }
        else 
            return "Regular Year";

    }
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a year: ");
        int year = in.nextInt();
        in.close();
        System.out.println(isLeap(year));
    }
}
