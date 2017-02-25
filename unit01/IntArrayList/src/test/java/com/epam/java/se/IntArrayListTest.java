package com.epam.java.se;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class IntArrayListTest {

    @Test
    public void testBinarySearchRecursiveForExistingElementReturnIndexInArray() throws Exception {
        final int[] intArraySorted = {Integer.MIN_VALUE, -10, 0, 1, 2, 3, 8, 15, Integer.MAX_VALUE};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        assertEquals(5, arrayList.binarySearchRecursive(3));
        assertEquals(8, arrayList.binarySearchRecursive(Integer.MAX_VALUE));
    }

    @Test
    public void testBinarySearchRecursiveForNotExistingElementReturnNegativeIndexToInsertMinusOne() {

        final int[] intArraySorted = {Integer.MIN_VALUE, -10, 0, 1, 2, 3, 8, 15, Integer.MAX_VALUE};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        assertEquals(-2, arrayList.binarySearchRecursive(-20));
        assertEquals(-7, arrayList.binarySearchRecursive(5));
    }

    @Test
    public void testBinarySearchCyclicalForExistingElementReturnIndexInArray() throws Exception {

        final int[] intArraySorted = {Integer.MIN_VALUE, -10, 0, 1, 2, 3, 8, 15, Integer.MAX_VALUE};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        assertEquals(5, arrayList.binarySearchCyclical(3));
        assertEquals(8, arrayList.binarySearchCyclical(Integer.MAX_VALUE));
    }

    @Test
    public void testBinarySearchCyclicalForNotExistingElementReturnNegativeIndexToInsertMinusOne() {

        final int[] intArraySorted = {Integer.MIN_VALUE, -10, 0, 1, 2, 3, 8, 15, Integer.MAX_VALUE};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        assertEquals(-2, arrayList.binarySearchCyclical(-20));
        assertEquals(-7, arrayList.binarySearchCyclical(5));
    }

    @Test
    public void testDescendingMergeSortForSortedArray() throws Exception {

        final int[] intArraySorted = {Integer.MIN_VALUE, -10, 0, 1, 2, 3, 8, 15, Integer.MAX_VALUE};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        arrayList.descendingMergeSort();

        for (int i = 0; i < intArraySorted.length; i++) {
            assertEquals("i = " + i, intArraySorted[i], arrayList.get(i));
        }
    }


    @Test
    public void testDescendingMergeSortForReverseSortedArray() throws Exception {

        final int[] intArraySorted = {Integer.MAX_VALUE, 15, 8, 3, 2, 1, 0, -10, Integer.MIN_VALUE};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        Arrays.sort(intArraySorted);
        arrayList.descendingMergeSort();

        for (int i = 0; i < intArraySorted.length; i++) {
            assertEquals("i = " + i, intArraySorted[i], arrayList.get(i));
        }
    }

    @Test
    public void testDescendingMergeSortForEqualElementsArray() throws Exception {

        final int[] intArraySorted = {15, 15, 15, 15, 15, 15, 15, 15, 15};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        Arrays.sort(intArraySorted);
        arrayList.descendingMergeSort();

        for (int i = 0; i < intArraySorted.length; i++) {
            assertEquals("i = " + i, intArraySorted[i], arrayList.get(i));
        }
    }

    @Test
    public void testDescendingMergeSortForPartiallySortedArray() throws Exception {

        final int[] intArraySorted = {-8, -351, 104, -800, -799, Integer.MAX_VALUE, 15, 15, 15};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        Arrays.sort(intArraySorted);
        arrayList.descendingMergeSort();

        for (int i = 0; i < intArraySorted.length; i++) {
            assertEquals("i = " + i, intArraySorted[i], arrayList.get(i));
        }
    }

    @Test
    public void testDescendingMergeSortForPartiallySortedArrayWithDuplicateElements() throws Exception {

        final int[] intArraySorted = {-8, -351, 104, -800, -799, Integer.MAX_VALUE, 15, 15, 15};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        Arrays.sort(intArraySorted);
        arrayList.descendingMergeSort();

        for (int i = 0; i < intArraySorted.length; i++) {
            assertEquals("i = " + i, intArraySorted[i], arrayList.get(i));
        }
    }

    @Test
    public void testRisingMergeSortForSortedArray() throws Exception {

        final int[] intArraySorted = {Integer.MIN_VALUE, -10, 0, 1, 2, 3, 8, 15, Integer.MAX_VALUE};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        arrayList.risingMergeSort();

        for (int i = 0; i < intArraySorted.length; i++) {
            assertEquals("i = " + i, intArraySorted[i], arrayList.get(i));
        }
    }


    @Test
    public void testRisingMergeSortForReverseSortedArray() throws Exception {

        final int[] intArraySorted = {Integer.MAX_VALUE, 15, 8, 3, 2, 1, 0, -10, Integer.MIN_VALUE};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        Arrays.sort(intArraySorted);
        arrayList.risingMergeSort();

        for (int i = 0; i < intArraySorted.length; i++) {
            assertEquals("i = " + i, intArraySorted[i], arrayList.get(i));
        }
    }

    @Test
    public void testRisingMergeSortForEqualElementsArray() throws Exception {

        final int[] intArraySorted = {15, 15, 15, 15, 15, 15, 15, 15, 15};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        Arrays.sort(intArraySorted);
        arrayList.risingMergeSort();

        for (int i = 0; i < intArraySorted.length; i++) {
            assertEquals("i = " + i, intArraySorted[i], arrayList.get(i));
        }
    }

    @Test
    public void testRisingMergeSortForPartiallySortedArray() throws Exception {

        final int[] intArraySorted = {-8, -351, 104, -800, -799, Integer.MAX_VALUE, 15, 15, 15};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        Arrays.sort(intArraySorted);
        arrayList.risingMergeSort();

        for (int i = 0; i < intArraySorted.length; i++) {
            assertEquals("i = " + i, intArraySorted[i], arrayList.get(i));
        }
    }

    @Test
    public void testRisingMergeSortForPartiallySortedArrayWithDuplicateElements() throws Exception {

        final int[] intArraySorted = {-8, -351, 104, -800, -799, Integer.MAX_VALUE, 15, 15, 15};
        final IntArrayList arrayList = new IntArrayList(intArraySorted);

        Arrays.sort(intArraySorted);
        arrayList.risingMergeSort();

        for (int i = 0; i < intArraySorted.length; i++) {
            assertEquals("i = " + i, intArraySorted[i], arrayList.get(i));
        }
    }

}