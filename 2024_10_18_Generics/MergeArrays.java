// package com.gradescope.mymerge;

import java.util.ArrayList;

public class MergeArrays {
    
    public static <T> ArrayList<T> merge(T[] list1, T[] list2) {
        ArrayList<T> mergedArray = new ArrayList<T>();
        T[] shorterList; T[] longerList;
        if (list1.length < list2.length) {
            shorterList = list1;
            longerList = list2;
        } else {
            shorterList = list2;
            longerList = list1;
        }

        for(int i = 0; i<longerList.length; i++) {
            if (i < shorterList.length) { mergedArray.add(shorterList[i]); }
            mergedArray.add(longerList[i]);
        }

        return mergedArray;
    }
}
