package com.company.utilities;

public interface AttributeFetcher<T, R> {
    public Comparable<R> fetch(T objT);
}
