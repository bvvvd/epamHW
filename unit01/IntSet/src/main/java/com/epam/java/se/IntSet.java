package com.epam.java.se;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * Class is designed to store Set of integer Numbers
 *
 * @author Valeriy Burmistrov
 */
public class IntSet {


    /**
     * Set of negative integer numbers
     */
    private long[] negativeData;

    /**
     * Set of non-negative integer numbers
     */
    private long[] nonNegativeData;
    private final int numberOfBits = 64;

    /**
     * Creates a new default Set
     */
    public IntSet() {
        negativeData = new long[1];
        nonNegativeData = new long[1];
    }

    private IntSet(long[] negativeData, long[] nonNegativeData) {
        this.negativeData = Arrays.copyOf(negativeData, negativeData.length);
        this.nonNegativeData = Arrays.copyOf(nonNegativeData, nonNegativeData.length);
    }

    /**
     * adds a new integer number to Set
     *
     * @param value - the number needed to add
     */
    public void add(int value) {
        if (value < 0) {
            int absoluteValue = getAbsoluteValue(value);
            int frameOfValue = getFrameOfValue(absoluteValue);
            ensureCapacityOfNegativeData(frameOfValue + 1);
            final long mask = 1L << (absoluteValue - 1);
            negativeData[frameOfValue] |= mask;
        } else {
            int frameOfValue = value / numberOfBits;
            ensureCapacityOfNonNegativeData(frameOfValue + 1);
            final long mask = 1L << value;
            nonNegativeData[frameOfValue] |= mask;
        }
    }

    private int getAbsoluteValue(int value) {
        return Math.abs(value);
    }

    private int getFrameOfValue(int value) {
        return (value - 1) / numberOfBits;
    }

    private void ensureCapacityOfNonNegativeData(int requiredCapacityOfNonNegativeData) {
        if (requiredCapacityOfNonNegativeData <= getCapacityOfNonNegativeData()) {
            return;
        }
        final int newCapacityOfNonNegativeData = Math.
                max(requiredCapacityOfNonNegativeData, (getCapacityOfNonNegativeData() * 3) / 2 + 1);
        nonNegativeData = Arrays.copyOf(nonNegativeData, newCapacityOfNonNegativeData);
    }

    private int getCapacityOfNonNegativeData() {
        return nonNegativeData.length;
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

    /**
     * checks the Set contains number
     *
     * @param value - the number needed to be checked
     * @return true in case set contains the number, false in case set does not contains the number,
     * or number is negative
     */
    public boolean contains(int value) {
        if (value < 0) {
            int absoluteValue = getAbsoluteValue(value);
            int frameOfValue = getFrameOfValue(absoluteValue);
            if (frameOfValue >= getCapacityOfNegativeData()) {
                return false;
            }
            final long mask = 1L << (absoluteValue - 1);
            final long result = negativeData[frameOfValue] & mask;
            return result != 0;
        } else {
            int frameOfValue = value / numberOfBits;
            if (frameOfValue >= nonNegativeData.length) {
                return false;
            }
            final long mask = 1L << value;
            final long result = nonNegativeData[frameOfValue] & mask;
            return result != 0;
        }
    }

    /**
     * removes the number from the Set
     *
     * @param value - the number needed to be removed
     */
    public void remove(int value) {
        if (value < 0) {
            int absoluteValue = getAbsoluteValue(value);
            int frameOfValue = getFrameOfValue(absoluteValue);
            if (frameOfValue >= getCapacityOfNegativeData()) {
                return;
            }
            final long mask = 1L << (absoluteValue - 1);
            negativeData[frameOfValue] &= ~mask;
        } else {
            int frameOfValue = value / numberOfBits;
            if (frameOfValue >= getCapacityOfNonNegativeData()) {
                return;
            }
            final long mask = 1L << value;
            nonNegativeData[frameOfValue] &= ~mask;
        }
    }

    /**
     * merges values of two IntSets
     *
     * @param anotherSet - Set needed to be united with current Set
     * @return new IntSet, which contains values from both of source Sets
     */
    public IntSet union(@Nonnull IntSet anotherSet) {

        final int resultingSetCopyOfNonNegativeDataSize =
                Math.min(this.getCapacityOfNonNegativeData(), anotherSet.getCapacityOfNonNegativeData());

        long[] resultingSetNonNegativeData;
        if (resultingSetCopyOfNonNegativeDataSize == this.getCapacityOfNonNegativeData()) {
            resultingSetNonNegativeData = Arrays.
                    copyOf(anotherSet.nonNegativeData, anotherSet.getCapacityOfNonNegativeData());
            for (int i = 0; i < resultingSetCopyOfNonNegativeDataSize; i++) {
                resultingSetNonNegativeData[i] |= this.nonNegativeData[i];
            }
        } else {
            resultingSetNonNegativeData = Arrays.
                    copyOf(this.nonNegativeData, anotherSet.getCapacityOfNonNegativeData());
            for (int i = 0; i < resultingSetCopyOfNonNegativeDataSize; i++) {
                resultingSetNonNegativeData[i] |= anotherSet.nonNegativeData[i];
            }
        }

        final int resultingSetCopyOfNegativeDataSize =
                Math.min(this.getCapacityOfNegativeData(), anotherSet.getCapacityOfNegativeData());

        long[] resultingSetNegativeData;
        if (resultingSetCopyOfNegativeDataSize == this.getCapacityOfNegativeData()) {
            resultingSetNegativeData = Arrays.
                    copyOf(anotherSet.negativeData, anotherSet.getCapacityOfNegativeData());
            for (int i = 0; i < resultingSetCopyOfNegativeDataSize; i++) {
                resultingSetNegativeData[i] |= this.negativeData[i];
            }
        } else {
            resultingSetNegativeData = Arrays.
                    copyOf(this.negativeData, anotherSet.getCapacityOfNegativeData());
            for (int i = 0; i < resultingSetCopyOfNegativeDataSize; i++) {
                resultingSetNegativeData[i] |= anotherSet.negativeData[i];
            }
        }

        return new IntSet(resultingSetNegativeData, resultingSetNonNegativeData);

    }

    /**
     * creates new IntSet, which contains values, which belong to both of source Sets
     *
     * @param anotherSet - Set needed to be intersected with current Set
     * @return new IntSet, which contains values, which contains in both of source Sets
     */
    public IntSet intersection(@Nonnull IntSet anotherSet) {
        final int resultingSetNonNegativeDataSize =
                Math.min(this.getCapacityOfNonNegativeData(), anotherSet.getCapacityOfNonNegativeData());

        long[] resultingSetNonNegativeData = new long[resultingSetNonNegativeDataSize];
        for (int i = 0; i < resultingSetNonNegativeDataSize; i++) {
            resultingSetNonNegativeData[i] = this.nonNegativeData[i] & anotherSet.nonNegativeData[i];
        }

        final int resultingSetNegativeDataSize =
                Math.min(this.getCapacityOfNegativeData(), anotherSet.getCapacityOfNegativeData());

        long[] resultingSetNegativeData = new long[resultingSetNegativeDataSize];
        for (int i = 0; i < resultingSetNegativeDataSize; i++) {
            resultingSetNegativeData[i] = this.negativeData[i] & anotherSet.nonNegativeData[i];
        }

        return new IntSet(resultingSetNegativeData, resultingSetNonNegativeData);
    }

    /**
     * creates new IntSet, which contains values that belong to current IntSet
     * and does not belong to the other IntSet
     *
     * @param anotherSet - the Set needed to be differed with current Set
     * @return the new IntSet, which contains values from the current IntSet
     * and does not contains values from the other IntSet
     */
    public IntSet difference(@Nonnull IntSet anotherSet) {
        final long[] resultingSetNonNegativeData = new long[getCapacityOfNonNegativeData()];
        final int frameOfDifferenceNonNegativeData =
                Math.min(this.getCapacityOfNonNegativeData(), anotherSet.getCapacityOfNonNegativeData());
        for (int i = 0; i < frameOfDifferenceNonNegativeData; i++) {
            resultingSetNonNegativeData[i] = this.nonNegativeData[i] & ~anotherSet.nonNegativeData[i];
        }

        final long[] resultingSetNegativeData = new long[getCapacityOfNegativeData()];
        final int frameOfDifferenceNegativeData =
                Math.min(this.getCapacityOfNegativeData(), anotherSet.getCapacityOfNegativeData());
        for (int i = 0; i < frameOfDifferenceNegativeData; i++) {
            resultingSetNegativeData[i] = this.negativeData[i] & ~anotherSet.negativeData[i];
        }

        return new IntSet(resultingSetNegativeData, resultingSetNonNegativeData);
    }

    /**
     * check that the current Set is a subset of anotherSet
     *
     * @param anotherSet - set the entry in which needed to check
     * @return the result of check
     */
    public boolean isSubsetOf(@Nonnull IntSet anotherSet) {
        if (this.getCapacityOfNonNegativeData() > anotherSet.getCapacityOfNonNegativeData() ||
                this.getCapacityOfNegativeData() > anotherSet.getCapacityOfNegativeData()) {
            return false;
        }
        for (int i = 0; i < this.getCapacityOfNegativeData(); i++) {
            if (this.negativeData[i] != (this.negativeData[i] & anotherSet.negativeData[i])) {
                return false;
            }
        }

        for (int i = 0; i < this.getCapacityOfNonNegativeData(); i++) {
            if (this.nonNegativeData[i] != (this.nonNegativeData[i] & anotherSet.nonNegativeData[i])) {
                return false;
            }
        }

        return true;
    }
}