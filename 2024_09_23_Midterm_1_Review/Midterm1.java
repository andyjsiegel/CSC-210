import java.util.ArrayList;

public class Midterm1 {
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(99); nums.add(98); nums.add(97);
        for (int i : nums) System.out.println("i="+i);
        for (int j=0; j<nums.size(); j++) {
        System.out.println("j="+j);
        System.out.println("nums[j] = "+nums.get(j));
}
    }
}
