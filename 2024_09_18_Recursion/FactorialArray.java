import java.util.ArrayList;

public class FactorialArray {
    public static ArrayList<Integer> factorial(int n) {
        ArrayList<Integer> factorialValues = new ArrayList<Integer>();
        int result = 1;
        for(int i = 1; i < n+1; i++) {
            result *= i;
            factorialValues.add(result);
        }
        return factorialValues;
    }
    public static void main(String[] args) {
        System.out.println(factorial(10));
    }
}
