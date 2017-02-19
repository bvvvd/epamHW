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
    public void remove() throws Exception {

    }

    @Test
    public void union() throws Exception {

    }

    @Test
    public void intersection() throws Exception {

    }

    @Test
    public void difference() throws Exception {

    }

    @Test
    public void isSubsetOf() throws Exception {

    }

}