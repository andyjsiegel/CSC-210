// package com.gradescope.hms;
import java.util.Scanner;

public class HoursMinutesSeconds {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter number of seconds: ");
        int total_seconds = in.nextInt();
        int hours = total_seconds / 3600;
        int minutes = (total_seconds % 3600) / 60;
        int seconds = total_seconds % 60;
        System.out.println(hours + ":" + minutes + ":" + seconds);
        in.close();
    }
}
