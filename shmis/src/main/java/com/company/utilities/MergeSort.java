package com.company.utilities;

import java.util.ArrayList;

import com.company.classes.Patient;

public class MergeSort {
    public static void mergeSort(ArrayList<Patient> arr, String sortBy) {
        // Keep dividing the array in half until the size of the array >= 2
        if (arr.size() >= 2) {
            ArrayList<Patient> left = new ArrayList<Patient>(arr.subList(0, arr.size()/2));
            ArrayList<Patient> right = new ArrayList<Patient>(arr.subList(arr.size()/2, arr.size()));

            mergeSort(left, sortBy);
            mergeSort(right, sortBy);
            merge(arr, left, right, sortBy);
        }
    }

    public static void merge(ArrayList<Patient> result, ArrayList<Patient> left, ArrayList<Patient> right, String mergeBy) {
        int i = 0, j = 0;

        for (int k = 0; k < result.size(); k++) {
            if (i < left.size() && j < right.size()) {
                boolean cmp;
                Patient p1 = left.get(i), p2 = right.get(i);

                if (mergeBy.equals("firstName"))
                    cmp = p1.lessEqualsFirstName(p2);
                else if (mergeBy.equals("lastName"))
                    cmp = p1.lessEqualsLastName(p2);
                else
                    cmp = p1.lessEqualEmail(p2);

                if (cmp) {
                    result.set(k, left.get(i));
                    i++;
                } else {
                    result.set(k, right.get(j));
                    j++;
                }
            }

            // Boundary check
            else if (i == left.size() && j < right.size()) {
                result.set(k, right.get(j));
                j++;
            } else if (j == right.size() && i < left.size()) {
                result.set(k, left.get(i));
                i++;
            }
        }
    }
}
