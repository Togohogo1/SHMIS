package com.company.utilities;

import java.util.ArrayList;

import com.company.classes.Appointment;

public class SearchSort {
    // TODO add somewhere numbers are easy to increment
    public static int binarySearch(ArrayList<Appointment> arr, long id) {
        int l = 0, r = arr.size() - 1;

        while (l <= r) {
            int mid = (l+r)/2;

            if (arr.get(mid).getId() > id)
                r = mid - 1;
            else if (arr.get(mid).getId() < id)
                l = mid + 1;
            else
                return mid;
        }

        return -1;
    }

    public static <T, R> void mergeSort(ArrayList<T> arr, AttributeFetcher<T, R> fetcher) {
        // Keep dividing the array in half until the size of the array >= 2
        if (arr.size() >= 2) {
            ArrayList<T> left = new ArrayList<>(arr.subList(0, arr.size()/2));
            ArrayList<T> right = new ArrayList<>(arr.subList(arr.size()/2, arr.size()));

            mergeSort(left, fetcher);
            mergeSort(right, fetcher);
            merge(arr, left, right, fetcher);
        }
    }

    private static <T, R> void merge(ArrayList<T> result, ArrayList<T> left, ArrayList<T> right, AttributeFetcher<T, R> fetcher) {
        int i = 0, j = 0;

        for (int k = 0; k < result.size(); k++) {
            if (i < left.size() && j < right.size()) {
                Comparable<R> obj1 = fetcher.fetch(left.get(i));
                Comparable<R> obj2 = fetcher.fetch(right.get(j));

                if (obj1.compareTo((R)obj2) <= 0) {
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
