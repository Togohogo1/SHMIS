package com.company.utilities;

import java.util.ArrayList;

public class MergeSort {

}


class MergeSortInt {
    public static void merge(ArrayList<Integer> result, ArrayList<Integer> left, ArrayList<Integer> right) {
        int i = 0, j = 0;

        for (int a = 0; a < result.size(); a++) {
            if (i < left.size() && j < right.size()) {
                if (left.get(i) <= right.get(j)) {
                    result.set(a, left.get(i));
                    i++;
                } else if (left.get(i) > right.get(j)) {
                    result.set(a, right.get(j));
                    j++;
                }
            }

            // Boundary check
            else if (i == left.size() && j < right.size()) {
                result.set(a, right.get(j));
                j++;
            } else if (j == right.size() && i < left.size()) {
                result.set(a, left.get(i));
                i++;
            }
        }
    }

    public static void mergeSort(ArrayList<Integer> arr) {
        // Keep dividing the array in half until the size of the array >= 2
        if (arr.size() >= 2) {
            ArrayList<Integer> left = new ArrayList<Integer>(arr.subList(0, arr.size()/2));
            ArrayList<Integer> right = new ArrayList<Integer>(arr.subList(arr.size()/2, arr.size()));
            // int[] left = Arrays.copyOfRange(arr, 0, arr.length/2);
            // int[] right = Arrays.copyOfRange(arr, arr.length/2, arr.length);

            mergeSort(left);
            mergeSort(right);
            merge(arr, left, right);
        }
    }
}