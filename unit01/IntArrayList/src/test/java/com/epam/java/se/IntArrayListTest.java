package com.epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntArrayListTest {

    @Test
    public void testBinarySearchRecursiveExistingElement() throws Exception {
        final int[] intArraySorted = {Integer.MIN_VALUE, -10, 0, 1, 2, 3, 8, 15, Integer.MAX_VALUE};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        assertEquals(5, arrayList.binarySearchRecursive(3));
        assertEquals(8, arrayList.binarySearchRecursive(Integer.MAX_VALUE));
    }

    @Test
    public void testBinarySearchRecursiveNotExistingElementReturnNegativeIndexToInsertMinusOne() {

        final int[] intArraySorted = {Integer.MIN_VALUE, -10, 0, 1, 2, 3, 8, 15, Integer.MAX_VALUE};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        assertEquals(-2, arrayList.binarySearchRecursive(-20));
        assertEquals(-7, arrayList.binarySearchRecursive(5));
    }

    @Test
    public void testBinarySearchCyclicalExistingElement() throws Exception {

        final int[] intArraySorted = {Integer.MIN_VALUE, -10, 0, 1, 2, 3, 8, 15, Integer.MAX_VALUE};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        assertEquals(5, arrayList.binarySearchCyclical(3));
        assertEquals(8, arrayList.binarySearchCyclical(Integer.MAX_VALUE));
    }

    @Test
    public void testBinarySearchCyclicalNotExistingElementReturnNegativeIndexToInsertMinusOne() {

        final int[] intArraySorted = {Integer.MIN_VALUE, -10, 0, 1, 2, 3, 8, 15, Integer.MAX_VALUE};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        assertEquals(-2, arrayList.binarySearchCyclical(-20));
        assertEquals(-7, arrayList.binarySearchCyclical(5));
    }

}