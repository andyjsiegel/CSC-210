import java.util.ArrayList;
import java.util.Collections;
public class NumsToBools {

    public static boolean isOdd(int n) {
        return !(n % 2 == 0);
    }

    public static ArrayList<Boolean> numsToBools(ArrayList<Integer> nums) {
        ArrayList<Boolean> bools = new ArrayList<Boolean>();
        for(int n: nums) {
            bools.add(isOdd(n));
        }
        return bools;
    }
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Collections.addAll(numbers, 0, 1, 1, 2, 3, 3);
        System.out.println(numsToBools(numbers));
    }
}
