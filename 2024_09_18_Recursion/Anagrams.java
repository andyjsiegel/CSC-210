// package com.gradescope.anagrams;
/* 
This Java program takes in command line arguments to determine anagrams from a word.
The first argument is the words list (txt) that determines the valid words.
The second argument is the word to find anagrams from
The third argument is the max number of anagrams.
*/
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Anagrams {

    public static HashSet<String> getWordList(String fileName) throws FileNotFoundException {
        File wordsFile = new File(fileName);
        Scanner fileReader = new Scanner(wordsFile);
        HashSet<String> wordsList = new HashSet<String>();
        while(fileReader.hasNextLine()) {
            String word = fileReader.nextLine();
            wordsList.add(word);
        }
        fileReader.close();
        return wordsList;
    }
    
    public static ArrayList<Character> getChars(String word) {
        ArrayList<Character> charList = new ArrayList<Character>();
        for (char c : word.toCharArray()) {
            charList.add(c);
        }
        return charList;

    }

    public static void getCombinations(ArrayList<Character> chars, String s, HashSet<String> validWords, HashSet<String> solutions) {
        if (s.length() > 0 && validWords.contains(s)) {
            solutions.add(s);  // Add to solutions if it's a valid word
        }
        
        for (int i = 0; i < chars.size(); i++) {
            char currentChar = chars.get(i);
            // Recursive call
            ArrayList<Character> remainingChars = new ArrayList<>(chars);
            remainingChars.remove(i);  // Remove the current char to avoid repeats
            getCombinations(remainingChars, s + currentChar, validWords, solutions);
        }
    }
    
    public static void getAnagrams(int wordLength, ArrayList<String> orderedSolution, String inputWord, ArrayList<String> result, int maxAnagrams, int currentCharSum, ArrayList<ArrayList<String>> allResults) {
        // Base case: If the current character sum equals the word length, add a copy of the result to allResults
        if (currentCharSum == wordLength) {
            String anagramsConcatenated = String.join("", new ArrayList<>(result));
            char[] array1 = anagramsConcatenated.toCharArray();
            char[] array2 = inputWord.toCharArray();
            Arrays.sort(array1);
            Arrays.sort(array2);
            boolean arraysAreEqual = Arrays.equals(array1, array2);
            if(arraysAreEqual) {
                if(maxAnagrams == -1 || result.size() == maxAnagrams) {
                    allResults.add(new ArrayList<>(result)); // Add a copy of the result
                }
            }
            
            return;
        }
        if(currentCharSum > wordLength) {
            return;
        }
        
        // Create a temporary list to iterate over
        ArrayList<String> tempList = new ArrayList<>(orderedSolution);
        
        for (String word : tempList) {
            // Remove the word from orderedSolution
            orderedSolution.remove(word);
            
            // Update the current character sum and add the word to the result
            int tempCharSum = currentCharSum + word.length();
            result.add(word);
            
            // Recursive call
            getAnagrams(wordLength, orderedSolution, inputWord, result, maxAnagrams, tempCharSum, allResults);
            
            // Backtrack: remove the word from result and add it back to orderedSolution
            result.remove(result.size() - 1); // Remove the last added word
            orderedSolution.add(word); // Add the word back to the orderedSolution
        }
    }
    
    
    public static void main(String[] args) throws FileNotFoundException {
            
            String wordList = args[0];
            String word = args[1];
            int maxAnas = Integer.valueOf(args[2]);
            // String wordList = "2024_09_18_Recursion/words1.txt";
            // String word = "barbarabush";
            // int maxAnas = 0;
            if (maxAnas == 0) maxAnas = -1;  // set to -1 for no limit
    
            System.out.println("Phrase to scramble: " + word);
            
            HashSet<String> validWords = getWordList(wordList);
            HashSet<String> solutions = new HashSet<String>();
            ArrayList<Character> allChars = getChars(word);
            
            getCombinations(allChars, "", validWords, solutions);
            ArrayList<String> orderedSolution = new ArrayList<String>(solutions);
            Collections.sort(orderedSolution);
            
            System.out.println("\nAll words found in " + word + ":");
            System.out.println(orderedSolution);
            
            ArrayList<String> result = new ArrayList<String>();
            System.out.println("\nAnagrams for " + word + ":");
            ArrayList<ArrayList<String>> allResults = new ArrayList<ArrayList<String>>();
            getAnagrams(word.length(), orderedSolution, word, result, maxAnas, 0, allResults);
            Collections.sort(allResults, new Comparator<ArrayList<String>>() {
                @Override
                public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                    // Compare the inner lists lexicographically
                    return o1.toString().compareTo(o2.toString());
                }
            });
            for (int i = 0; i < allResults.size(); i++) System.out.println(allResults.get(i));
    
    
        }
}
