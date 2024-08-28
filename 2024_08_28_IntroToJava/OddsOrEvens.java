package com.gradescope.oddeven;

public class OddsOrEvens {
    public static boolean isEven(int number) { 
        return (number % 2 == 0);
     }
    
    public static boolean isOdd(int number) { 
        return !(number % 2 == 0);
     }
}
 