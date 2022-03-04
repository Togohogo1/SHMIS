package com.company.utilities;

import java.util.ArrayList;

/**
 * Utility class containing methods for searching and sorting.
 */
public class SearchSort {
    /**
     * Binary searches an <code>ArrayList</code> to get the index of <code>val</code>.
     *
     * @param <T>     Generic type parameter
     * @param <R>     Generic return type
     * @param arr     The <code>ArrayList</code> to be searched
     * @param fetcher Functional interface denoting the attribute to search by
     * @param val     The value to be searched for
     * @return The index of <code>val</code>
     */
    public static <T, R> int binarySearch(ArrayList<T> arr, AttributeFetcher<T, R> fetcher, R val) {
        int l = 0, r = arr.size() - 1;

        while (l <= r) {
            int mid = (l+r) / 2;
            Comparable<R> obj = fetcher.fetch(arr.get(mid));

            if (obj.compareTo(val) > 0)
                r = mid - 1;
            else if (obj.compareTo(val) < 0)
                l = mid + 1;
            else
                return mid;
        }

        return -1;  // Not found
    }

    /**
     * Sorts an <code>ArrayList</code> using merge sort based on an attribute specified by
     * <code>fetcher</code>.
     *
     * @param <T>     Generic type parameter
     * @param <R>     Generic return type
     * @param arr     The <code>ArrayList</code> to be sorted
     * @param fetcher Functional interface denoting the attribute to sort by
     */
    public static <T, R> void mergeSort(ArrayList<T> arr, AttributeFetcher<T, R> fetcher) {
        // Keep dividing the array in half until the size of the array >= 2
        if (arr.size() >= 2) {
            ArrayList<T> left = new ArrayList<>(arr.subList(0, arr.size()/2));
            ArrayList<T> right = new ArrayList<>(arr.subList(arr.size()/2, arr.size()));

            // Recursing left and right
            mergeSort(left, fetcher);
            mergeSort(right, fetcher);
            merge(arr, left, right, fetcher);
        }
    }

    /**
     * Helper method for the <code>mergeSort()</code> method to merge two sorted
     * arrays.
     *
     * @param <T>     Generic type parameter
     * @param <R>     Generic return type
     * @param result  Resultant <code>ArrayList</code>
     * @param left    Left <code>ArrayList</code>
     * @param right   Right <code>ArrayList</code>
     * @param fetcher Functional interface denoting the attribute to sort by
     */
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
