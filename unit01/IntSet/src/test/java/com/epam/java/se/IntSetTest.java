package com.epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chris on 19.02.2017.
 */
public class IntSetTest {

    @Test
    public void testAddNegativeNumber() throws Exception {
        final IntSet set = new IntSet();
        set.add(-2);
        assertTrue(set.contains(-2));
        set.add(-200);
        assertTrue(set.contains(-200));
        set.add(Integer.MIN_VALUE);
        assertTrue(set.contains(Integer.MIN_VALUE));
    }

    @Test
    public void testAddNonNegativeNumber() throws Exception {
        final IntSet set = new IntSet();
        set.add(0);
        assertTrue(set.contains(0));
        set.add(100);
        assertTrue(set.contains(100));
        set.add(Integer.MAX_VALUE);
        assertTrue(set.contains(Integer.MAX_VALUE));
    }

    @Test
    public void testNotContainsNotAddedNumber() throws Exception {
        final IntSet set = new IntSet();
        assertFalse(set.contains(0));
        assertFalse(set.contains(-100));
        assertFalse(set.contains(100));
    }

    @Test
    public void testContainsAddedNonNegativeNumber() throws Exception {
        final IntSet set = new IntSet();
        set.add(0);
        set.add(100);
        assertTrue(set.contains(0));
        assertTrue(set.contains(100));
    }


    @Test
    public void testContainsAddedNegativeNumber() throws Exception {
        final IntSet set = new IntSet();
        set.add(-1);
        set.add(-100);
        assertTrue(set.contains(-1));
        assertTrue(set.contains(-100));
    }

    @Test
    public void testRemoveNegativeNumber() throws Exception {
        final IntSet set = new IntSet();
        set.add(-10);
        set.remove(-10);
        assertFalse(set.contains(-10));
        set.add(-100);
        set.remove(-100);
        assertFalse(set.contains(-100));
    }

    @Test
    public void testRemoveNonNegativeNumber() throws Exception {
        final IntSet set = new IntSet();
        set.add(0);
        set.remove(0);
        assertFalse(set.contains(0));
        set.add(100);
        set.remove(100);
        assertFalse(set.contains(100));
    }

    @Test
    public void testUnionCorrectArgument() throws Exception {
        final IntSet set = new IntSet();
        set.add(-10);
        set.add(10);
        final IntSet anotherSet = new IntSet();
        anotherSet.add(-100);
        anotherSet.add(100);
        final IntSet unitedSet = set.union(anotherSet);
        for (int i = -100; i <= 100; i++) {
            if (i == -100 || i == -10 || i == 10 || i == 100) {
                assertTrue(unitedSet.contains(i));
            } else {
                assertFalse(unitedSet.contains(i));
            }
        }
    }

    @Test
    public void testUnionEmptySetArgument() throws Exception {
        final IntSet set = new IntSet();
        set.add(5);
        final IntSet anotherSet = new IntSet();
        final IntSet united = set.union(anotherSet);
        for (int i = -64; i < 64; i++) {
            if (i == 5) {
                assertTrue(united.contains(i));
            } else {
                assertFalse(united.contains(i));
            }
        }
    }

    @Test
    public void testUnionEmptyCurrentSet() throws Exception {
        final IntSet set = new IntSet();
        final IntSet anotherSet = new IntSet();
        anotherSet.add(5);
        final IntSet united = set.union(anotherSet);
        for (int i = -64; i < 64; i++) {
            if (i == 5) {
                assertTrue(united.contains(i));
            } else {
                assertFalse(united.contains(i));
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnionNullArgumentThrowsIllegalArgumentException() throws Exception {
        final IntSet set = new IntSet();

        final IntSet united = set.union(null);
    }

    @Test
    public void testIntersectionCorrectArgument() throws Exception {
        final IntSet set = new IntSet();
        set.add(-10);
        set.add(10);
        final IntSet anotherSet = new IntSet();
        anotherSet.add(-100);
        anotherSet.add(100);
        final IntSet intersection = set.intersection(anotherSet);
        for (int i = -100; i <= 100; i++) {
            assertFalse(intersection.contains(i));
        }
    }

    @Test
    public void testIntersectionEmptySetArgument() throws Exception {
        final IntSet set = new IntSet();
        set.add(5);
        final IntSet anotherSet = new IntSet();
        final IntSet intersection = set.intersection(anotherSet);
        for (int i = -64; i < 64; i++) {
            assertFalse(intersection.contains(i));
        }
    }

    @Test
    public void testIntersectionEmptyCurrentSet() throws Exception {
        final IntSet set = new IntSet();
        final IntSet anotherSet = new IntSet();
        anotherSet.add(5);
        final IntSet intersection = set.intersection(anotherSet);
        for (int i = -64; i < 64; i++) {
            assertFalse(intersection.contains(i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntersectionNullArgumentThrowsIllegalArgumentException() throws Exception {
        final IntSet set = new IntSet();

        final IntSet intersection = set.intersection(null);
    }

    @Test
    public void testDifferenceCorrectArgument() throws Exception {
        final IntSet set = new IntSet();
        set.add(-5);
        set.add(5);
        final IntSet anotherSet = new IntSet();
        anotherSet.add(5);
        final IntSet difference1 = set.difference(anotherSet);
        for (int i = -64; i < 64; i++) {
            if (i == -5) {
                assertTrue(difference1.contains(i));
            } else {
                assertFalse(difference1.contains(i));
            }
        }
    }

    @Test
    public void testDifferenceEmptyArgument() throws Exception {
        final IntSet set = new IntSet();
        set.add(-5);
        set.add(5);
        final IntSet anotherSet = new IntSet();
        final IntSet difference1 = set.difference(anotherSet);
        for (int i = -64; i < 64; i++) {
            if (i == -5 || i == 5) {
                assertTrue(difference1.contains(i));
            } else {
                assertFalse(difference1.contains(i));
            }
        }
    }

    @Test
    public void testDifferenceEmptyCurrentSet() throws Exception {
        final IntSet set = new IntSet();
        final IntSet anotherSet = new IntSet();
        anotherSet.add(5);
        final IntSet difference1 = set.difference(anotherSet);
        for (int i = -64; i < 64; i++) {
            assertFalse(difference1.contains(i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDifferenceNullArgumentThrowsIllegalArgumentException() throws Exception {
        final IntSet set = new IntSet();

        final IntSet intersection = set.intersection(null);
    }

    @Test
    public void testIsSubsetOfCorrectArguments() throws Exception {
        final IntSet set = new IntSet();
        set.add(-5);
        set.add(-100);
        set.add(100);
        final IntSet anotherSet = new IntSet();
        anotherSet.add(-5);
        anotherSet.add(-100);
        anotherSet.add(100);
        anotherSet.add(200);
        assertTrue(set.isSubsetOf(anotherSet));
        assertFalse(anotherSet.isSubsetOf(set));
    }

    @Test
    public void testIsSubsetOfEmptyArgument() throws Exception {
        final IntSet set = new IntSet();
        set.add(-5);
        set.add(5);
        final IntSet anotherSet = new IntSet();
        assertFalse(set.isSubsetOf(anotherSet));
    }

    @Test
    public void testIsSubsetOfEmptyCurrentSet() throws Exception {
        final IntSet set = new IntSet();
        final IntSet anotherSet = new IntSet();
        anotherSet.add(5);
        assertTrue(set.isSubsetOf(anotherSet));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsSubsetOfNullArgumentThrowsIllegalArgumentException() throws Exception {
        final IntSet set = new IntSet();

        final boolean isSubset = set.isSubsetOf(null);
    }

}