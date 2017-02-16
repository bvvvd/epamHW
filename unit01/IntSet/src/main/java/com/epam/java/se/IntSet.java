package com.epam.java.se;

import java.util.Arrays;

/**
 * Class is designed to store Set of positive integer Numbers
 *
 * @author Valeriy Burmistrov
 */
public class IntSet {

    /**
     * Set of numbers
     */
    private long[] positiveData;

    /**
     * Create a new IntSet with a specified data
     *
     * @param positiveData - array of storing positive numbers
     */
    private IntSet(long[] positiveData) {

        this.positiveData = Arrays.copyOf(positiveData, positiveData.length);
    }

    /**
     * Create a new default Set
     */
    public IntSet() {

        positiveData = new long[1];
    }

    /**
     * adds a new integer number to Set
     *
     * @param value - the number needed to add
     */
    public void add(int value) {
        ensureCapasity(value / 64 + 1);
        if (value >= 0) {
            int index = value / 64;
            positiveData[index] |= 1L << value;
        }
    }

    private void ensureCapasity(int requiredCapasity) {
        if (requiredCapasity <= getCapasity()) {
            return;
        }
        final int newCapasity = Math.max(requiredCapasity, (getCapasity() * 3) / 2 + 1);
        positiveData = Arrays.copyOf(positiveData, newCapasity);
    }

    private int getCapasity() {
        return positiveData.length;
    }

    /**
     * removes the number from the Set
     *
     * @param value - the number needed to be removed
     */
    public void remove(int value) {
        int index = value / 64;
        if (index > positiveData.length) {
            return;
        }
        final long mask = 1L << value;
        positiveData[index] &= ~mask;
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
            return false;
        }
        final long mask = 1L << value;
        int index = value / 64;
        if (index > positiveData.length) {
            return false;
        }
        final long result = positiveData[index] & mask;
        return result != 0;
    }


    /**
     * merges values of two IntSets
     *
     * @param anotherSet - Set needed to be united with current Set
     * @return new IntSet, which contains values from both of source Sets
     */
    public IntSet union(IntSet anotherSet) {
        int arraySize = Math.max(this.positiveData.length, anotherSet.positiveData.length);
        int copySize = Math.min(this.positiveData.length, anotherSet.positiveData.length);
        final long[] resultingSetPositiveData = new long[arraySize];
        if (arraySize == this.positiveData.length) {
            System.arraycopy(this.positiveData, 0, resultingSetPositiveData, 0, arraySize);
        } else {
            System.arraycopy(anotherSet.positiveData, 0, resultingSetPositiveData, 0, arraySize);
        }
        for (int i = 0; i < copySize; i++) {
            resultingSetPositiveData[i] = this.positiveData[i] | anotherSet.positiveData[i];
        }

        return new IntSet(resultingSetPositiveData);
    }


    /**
     * creates new IntSet, which contains values, which belong to both of source Sets
     *
     * @param anotherSet - Set needed to be intersected with current Set
     * @return new IntSet, which contains values, which contains in both of source Sets
     */
    public IntSet intersection(IntSet anotherSet) {
        int arraySize = Math.min(this.positiveData.length, anotherSet.positiveData.length);
        final long[] resultingSetPositiveData = new long[arraySize];
        for (int i = 0; i < arraySize; i++) {
            resultingSetPositiveData[i] = this.positiveData[i] & anotherSet.positiveData[i];
        }

        return new IntSet(resultingSetPositiveData);
    }


    /**
     * creates new IntSet, which contains values that belong to current IntSet
     * and does not belong to the other IntSet
     *
     * @param anotherSet - the Set needed to be differed with current Set
     * @return the new IntSet, which contains values from the current IntSet
     * and does not contains values from the other IntSet
     */
    public IntSet difference(IntSet anotherSet) {
        final long[] resultingSetPositiveData = new long[this.positiveData.length];
        final int differenceSize = Math.min(this.positiveData.length, anotherSet.positiveData.length);
        System.arraycopy(this.positiveData, 0, resultingSetPositiveData, 0, this.positiveData.length);

        for (int i = 0; i < differenceSize; i++) {
            resultingSetPositiveData[i] &= ~anotherSet.positiveData[i];
        }
        return new IntSet(resultingSetPositiveData);
    }


    /**
     * check that the current Set is a subset of anotherSet
     *
     * @param anotherSet - set the entry in which needed to check
     * @return the result of check
     */
    public boolean isSubsetOf(IntSet anotherSet) {
        if (this.positiveData.length == 0) {
            return true;
        }
        if (this.positiveData.length > anotherSet.positiveData.length) {
            return false;
        }
        for (int i = 0; i < this.positiveData.length; i++) {
            if ((anotherSet.positiveData[i] & this.positiveData[i]) != this.positiveData[i]) {
                return false;
            }
        }
        return true;
    }

}