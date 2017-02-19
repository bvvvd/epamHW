package com.epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chris on 19.02.2017.
 */
public class IntSetTest {

    @Test
    public void addNegativeNumber() throws Exception {
        final IntSet set = new IntSet();
        set.add(-2);
        assertTrue(set.contains(-2));
        set.add(-200);
        assertTrue(set.contains(-200));
    }

    @Test
    public void contains() throws Exception {

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