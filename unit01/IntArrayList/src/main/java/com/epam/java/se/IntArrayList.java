package com.epam.java.se;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IntArrayList {

    private int[] data;

    private int size;

    public IntArrayList(int[] data) {
        this.data = Arrays.copyOf(data, data.length);
        size = data.length;
    }

    public IntArrayList() {
        data = new int[10];
        size = 0;
    }

    public void add(int value) {
        ensureCapacity(size + 1);
        data[size] = value;
        size += 1;
    }

    public int get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }

        return data[i];
    }

    public int getSize() {
        return size;
    }

    public int maxValueInefficient() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return maxValueRec(data, 0, size);
    }

    private int maxValueRec(int[] data, int startInclusive, int endExlusive) {
        final int length = endExlusive - startInclusive;

        if (length == 1) {
            return data[startInclusive];
        } else if (length == 0) {
            return Integer.MIN_VALUE;
        }

        final int mid = startInclusive + length / 2;
        return Math.max(
                maxValueRec(data, startInclusive, mid),
                maxValueRec(data, mid, endExlusive)
        );
    }

    public void sort() {
        mergeSort(data, 0, getSize(), new int[getSize()]);
    }


    /**
     * Expects collection to be sorted.
     *
     * @param value value to find in collection
     * @return index of the value or -indexToInsert - 1
     */
    public int binarySearch(int value) {
        throw new UnsupportedOperationException();
    }

    private static void mergeSort(int[] data, int startInclusive, int endExclusive, int[] free) {
        final int length = endExclusive - startInclusive;
        if (length <= 1) {
            return;
        }

        final int mid = startInclusive + length / 2;

        mergeSort(data, startInclusive, mid, free);
        mergeSort(data, mid, endExclusive, free);

        merger(data, startInclusive, mid, endExclusive, free);
    }

    private static void merger(int[] data, int startInclusive, int mid, int endExclusive, int[] free) {
        System.arraycopy(data, startInclusive, free, startInclusive, endExclusive - startInclusive);

        int i = startInclusive;
        int j = mid;
        for (int k = startInclusive; k < endExclusive; k++) {
            if (i >= mid) data[k] = free[j++];
            else if (j >= endExclusive) data[k] = free[i++];
            else if (free[i] < free[j]) data[k] = free[i++];
            else data[k] = free[j++];
        }
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= getCapacity()) {
            return;
        }
        final int newCapacity = Math.max(requiredCapacity, (getCapacity() * 3) / 2 + 1);
        data = Arrays.copyOf(data, newCapacity);
    }

    private int getCapacity() {
        return data.length;
    }


    public int binarySearchRecursive(int value) {
        return binarySearchRecursiveExecution(value, 0, getSize(), data);
    }

    private int binarySearchRecursiveExecution(int value, int leftBoundInclusive,
                                               int rightBoundExclusive, int[] data) {
        if (leftBoundInclusive == rightBoundExclusive) {
            return -leftBoundInclusive - 1;
        }

        final int arrayFrame = rightBoundExclusive - leftBoundInclusive;
        final int middle = leftBoundInclusive + arrayFrame / 2;

        if (data[middle] == value) {
            return middle;
        }else if (data[middle] > value) {
            return binarySearchRecursiveExecution(value, leftBoundInclusive, middle, data);
        } else {
            return binarySearchRecursiveExecution(value, middle + 1, rightBoundExclusive, data);
        }
    }

    public int binarySearchCyclical(int value) {
        return binarySearchCyclicalExecution(value, 0, getSize(), data);
    }

    private int binarySearchCyclicalExecution(int value, int leftBoundInclusive, int rightBoundExclusive, int[] data) {
        while (leftBoundInclusive != rightBoundExclusive) {

            final int arrayFrame = rightBoundExclusive - leftBoundInclusive;
            final int middle = leftBoundInclusive + arrayFrame / 2;

            if (data[middle] == value) {
                return middle;
            } else if (data[middle] > value) {
                rightBoundExclusive =middle;
            } else {
                leftBoundInclusive = middle + 1;
            }
        }

        return -leftBoundInclusive - 1;
    }
}