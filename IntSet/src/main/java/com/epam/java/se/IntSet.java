package com.epam.java.se;

import java.util.Arrays;

/**
 * Created by chris on 19.02.2017.
 */
public class IntSet {

    private long[] negativeData;

    private long[] nonNegativeData;

    private IntSet(long[] negativeData, long[] nonNegativeData) {
        this.negativeData = Arrays.copyOf(negativeData, negativeData.length);
        this.nonNegativeData = Arrays.copyOf(nonNegativeData,nonNegativeData.length);
    }

    public IntSet() {
        negativeData = new long[1];
        nonNegativeData = new long[1];
    }

    public void add(int value) {
        if (value < 0) {
            int absoluteValue = Math.abs(value);
            int frameOfValue = (absoluteValue - 1) / 64;
            ensureCapacityOfNegativeData(frameOfValue + 1);
            final long mask = 1L << (absoluteValue - 1);
            negativeData[frameOfValue] |= mask;
        }else {

        }
    }

    private void ensureCapacityOfNegativeData(int requiredCapacityOfNegativeData) {
        if (requiredCapacityOfNegativeData <= getCapacityOfNegativeData()) {
            return;
        }
        final int newCapacityOfNegativeData = Math.
                max(requiredCapacityOfNegativeData, (getCapacityOfNegativeData() * 3) / 2 + 1);
        negativeData = Arrays.copyOf(negativeData, newCapacityOfNegativeData);
    }

    private int getCapacityOfNegativeData() {
        return negativeData.length;
    }

    public boolean contains(int value) {
        if (value < 0) {
            int absoluteValue = Math.abs(value);
            int frameOfValue = (absoluteValue - 1) / 64;
            if (frameOfValue > getCapacityOfNegativeData()) {
                return false;
            }
            final long mask = 1L << (absoluteValue - 1);
            final long result = negativeData[frameOfValue] & mask;
            return result != 0;
        }else {
            return false;
        }
    }

    public void remove(int value) {
        // TODO: 19.02.2017 implement
        throw new UnsupportedOperationException();
    }

    public IntSet union(IntSet anotherSet) {
        // TODO: 19.02.2017 implement
        throw new UnsupportedOperationException();
    }

    public IntSet intersection(IntSet anotherSet) {
        // TODO: 19.02.2017 implement
        throw new UnsupportedOperationException();
    }

    public IntSet difference(IntSet anotherSet) {
        // TODO: 19.02.2017 implement
        throw new UnsupportedOperationException();
    }

    public boolean isSubsetOf(IntSet anotherSet) {
        // TODO: 19.02.2017 implement
        throw new UnsupportedOperationException();
    }
}
