// package com.gradescope.filesum;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileSum {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = in.nextLine();
        in.close();
        File myFile = new File(fileName);
        Scanner myReader = new Scanner(myFile);
        int sum = 0;
        while(myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] numbers = line.split(" ");
            for (String num : numbers) {
                sum += Integer.valueOf(num);
            }
        }
        System.out.println(sum);
        myReader.close();
        
    }
}
