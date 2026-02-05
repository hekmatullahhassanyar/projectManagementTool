package com.studentmanagement.filters;

public interface TaskFilter<T> {
    boolean filter(T item);
}
