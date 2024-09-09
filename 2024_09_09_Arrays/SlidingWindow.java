import java.util.Arrays;

public class SlidingWindow {
    public static int sum(int[] numbers) {
        int theSum = 0; // Initialize theSum to 0
        for (int i = 0; i < numbers.length; i++) { // Iterate from 0 to n-1
            theSum += numbers[i]; // Add each element to theSum
        }
        return theSum;
    }

    public static int maxConsecSum(int[] numbers, int k) {
        int maxSum = sum(Arrays.copyOfRange(numbers, 0, k));
        for(int index = k; index<numbers.length; index++) {
            int windowSum = sum(Arrays.copyOfRange(numbers, index-k, index));
            if( windowSum > maxSum) {
                maxSum = windowSum;
            }
        }
        return maxSum;
    } 
    
    public static int[] factorial(int n) {
        int result = 1;
        int[] allResults = new int[n];
        
        for (int i = 1; i <= n; i++) {
            result *= i;
            allResults[i-1] = result;
            
        }
        
        return allResults;
        
    } 
    public static void main(String[] args) {
        // int[] myNumbers = {10,2,-3,4,3};
        // System.out.println(maxConsecSum(myNumbers, 1)); // 10
        // System.out.println(maxConsecSum(myNumbers, 2)); // 12
        // System.out.println(maxConsecSum(myNumbers, 3)); // 9
        // System.out.println(maxConsecSum(myNumbers, 4)); // 13
        // System.out.println(maxConsecSum(myNumbers, 5)); // 16
        
        // int[] otherNumbers = {8, 1, 3, 8, 2};
        // System.out.println(maxConsecSum(otherNumbers, 2));  // 11
        // System.out.println(maxConsecSum(otherNumbers, 3));  // 13 * No its 12
        
        // int[] moreNumbers = {8, 1, 3, 7, 3, 2, 4, 10, -5, 4};
        // System.out.println(maxConsecSum(moreNumbers, 2)); // 14
        // System.out.println(maxConsecSum(moreNumbers, 5)); // 26
        int[] myResult = factorial(10);
        System.out.println(Arrays.toString(myResult));
        
    }
}
