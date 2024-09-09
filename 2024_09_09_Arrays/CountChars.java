import java.util.HashMap;

public class CountChars {
    
    public static HashMap<Character, Integer> countChars(String word) {
        HashMap<Character, Integer> charCount = new HashMap<Character, Integer>();
        
        for (int i = 0; i < word.length(); i++) {
            // get value for the character at index i
            Integer currentCount = charCount.get(word.charAt(i)); 
            
            // if there was no key yet in the HashMap, make the value zero
            if(currentCount == null) currentCount = 0;
            
            // put in the HashMap for the character at index i (our key)
            // the current value plus one
            charCount.put(word.charAt(i), currentCount + 1);
        }
        
        return charCount;
        
        
    }

    public static void main(String[] args) {
        System.out.println(countChars("pneumonoultramicroscopicsilicovolcanoconiosis"));

    }

}