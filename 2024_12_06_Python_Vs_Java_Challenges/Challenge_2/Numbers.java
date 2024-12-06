// package com.gradescope.numbers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Numbers {
    private ArrayList<Integer> numbers = new ArrayList<Integer>();

    public Numbers() {

    }

    public void addNumber(int number) {
        numbers.add(number);
        numbers.sort(Integer::compareTo);
    }

    public void remove(int number) {
        numbers.sort(Integer::compareTo);
        numbers.remove(number);
    }

    public int getMax() {
        numbers.sort(Integer::compareTo);
        return numbers.get(numbers.size() - 1);
    }

    public int getMin() {
        numbers.sort(Integer::compareTo);
        return numbers.get(0);
    }

    public void removeDuplicates() {
        Set<Integer> numberSet = new HashSet<>(numbers); // Use a HashSet to remove duplicates
        numbers = new ArrayList<>(numberSet); // Convert back to a List
        numbers.sort(Integer::compareTo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int number: numbers) {
            sb.append(number + " ");
        }
        return sb.toString().strip();
    }

    public static void main(String[] args) {
        Numbers myNumbers = new Numbers();
        myNumbers.addNumber(10);
        myNumbers.addNumber(1);
        myNumbers.addNumber(5);
        myNumbers.addNumber(10);
        myNumbers.addNumber(25);
        myNumbers.addNumber(10);
        myNumbers.addNumber(-25);
        System.out.println(myNumbers); // -25 1 5 10 10 10 25
        myNumbers.removeDuplicates();
        System.out.println(myNumbers); // -25 1 5 10 25
    }
}