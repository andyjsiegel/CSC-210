public class OddsOrEvens {
    public static boolean isEven(int number) { 
        return (number % 2 == 0);
     }
    
    public static boolean isOdd(int number) { 
        return !(number % 2 == 0);
     }
    public static void main(String[] args) {
        int number = 4;
        String result = (number % 2 == 0) ? "Even" : "Odd";
        System.out.println(result);
    }
}
 