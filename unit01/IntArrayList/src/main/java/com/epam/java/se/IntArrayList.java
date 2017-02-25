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

    /**
     * Expects collection to be sorted.
     *
     * @param value value to find in collection
     * @return index of the value or -indexToInsert - 1
     */

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
        } else if (data[middle] > value) {
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
                rightBoundExclusive = middle;
            } else {
                leftBoundInclusive = middle + 1;
            }
        }

        return -leftBoundInclusive - 1;
    }


    public void descendingMergeSort() {
        descendingMergeSortExecution(data, 0, getSize(), new int[getSize()]);
    }

    private void descendingMergeSortExecution(int[] data, int leftBoundInclusive,
                                              int rightBoundExclusive, int[] free) {
        final int arrayFrame = rightBoundExclusive - leftBoundInclusive;
        if (arrayFrame <= 1) {
            return;
        }

        final int middle = leftBoundInclusive + arrayFrame / 2;

        descendingMergeSortExecution(data, leftBoundInclusive, middle, free);
        descendingMergeSortExecution(data, middle, rightBoundExclusive, free);

        merger(data, leftBoundInclusive, middle, rightBoundExclusive, free);
    }

    private static void merger(int[] data, int leftBoundInclusive, int middle,
                               int rightBoundExclusive, int[] free) {
        System.arraycopy(data, leftBoundInclusive, free,
                leftBoundInclusive, rightBoundExclusive - leftBoundInclusive);

        int i = leftBoundInclusive;
        int j = middle;
        for (int k = leftBoundInclusive; k < rightBoundExclusive; k++) {
            if (i >= middle) data[k] = free[j++];
            else if (j >= rightBoundExclusive) data[k] = free[i++];
            else if (free[i] < free[j]) data[k] = free[i++];
            else data[k] = free[j++];
        }
    }

    public void risingMergeSort() {
        risingMergeSortExecution(data, 0, getSize());
    }

    private void risingMergeSortExecution(int[] data, int leftBoundInclusive, int rightBoundExclusive) {
        final int arrayLenght = rightBoundExclusive - leftBoundInclusive;
        if (arrayLenght <= 1) {
            return;
        }

        final int[] free = new int[getSize()];

        for (int currentFrameSize = 1; currentFrameSize < arrayLenght; currentFrameSize *= 2) {
            for (int i = 0; i < arrayLenght; i += currentFrameSize * 2) {
                risingMerge(data, i, currentFrameSize + i, currentFrameSize * 2 + i, free);
            }
        }
    }

    private void risingMerge(int[] data, int leftStartInclusive,int rightStartInclusive, int end, int[] free) {
        int i = leftStartInclusive;
        int j = rightStartInclusive;

        if (j >= data.length){
            return;
        }

        int frameToSortLength = Math.min(end - leftStartInclusive, data.length - leftStartInclusive);

        for (int k = 0; k < frameToSortLength; k++){
            if (i >= rightStartInclusive) free[k] = data[j++];
            else if (j >= end || j >= data.length) free[k] = data[i++];
            else if (data[i] < data[j]) free[k] = data[i++];
            else free[k] = data[j++];
        }

        System.arraycopy(free, 0, data, leftStartInclusive, frameToSortLength);
    }
}