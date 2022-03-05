package com.company.utilities;

/**
 * Functional interface to allow functions to be passed as parameters in
 * <code>SearchSort.java</code>.
 */
public interface AttributeFetcher<T, R> {
    /**
     * Returns a <code>Comparable</code> interface to be used in
     * <code>SearchSort.java</code> for comparisons.
     *
     * @param objT The return value from the function passed as a parameter
     * @return A <code>Comparable</code> interface
     */
    public Comparable<R> fetch(T objT);
}
