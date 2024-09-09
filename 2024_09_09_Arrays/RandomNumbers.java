import java.util.ArrayList;
import java.util.Collections; 
import java.util.Random;

public class RandomNumbers {
    
    public static ArrayList<Integer> getNumbers(int limit) {
        Random rand = new Random();
        ArrayList<Integer> randInts = new ArrayList<Integer>();
        
        for (int i = 0; i < limit; i++)  randInts.add(rand.nextInt(limit));
        
        Collections.sort(randInts);
        return randInts;
        
    }

    public static void main(String[] args) {
        
        if (args.length > 0) {
            int n = Integer.valueOf(args[0]); // or Integer.parseInt(args[0]);
            ArrayList<Integer> myResult = getNumbers(n);
            System.out.println(myResult);
        }
        
    }
}