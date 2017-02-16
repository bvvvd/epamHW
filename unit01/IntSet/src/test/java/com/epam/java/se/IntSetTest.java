package com.epam.java.se;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntSetTest {
    @Test
    public void add() throws Exception {
        final IntSet set = new IntSet();

        assertFalse(set.contains(0));
        set.add(0);
        assertTrue(set.contains(0));
        set.add(1);
        assertTrue(set.contains(1));
        set.add(10);
        assertTrue(set.contains(1));
        set.add(-1);
        assertFalse(set.contains(-1));
        set.add(64);
        assertTrue(set.contains(64));

        set.add(513);
        assertTrue(set.contains(513));

        set.add(2);
        assertTrue(set.contains(2));
        set.add(63);
        assertTrue(set.contains(513));

    }

    @Test
    public void remove() throws Exception {
        final IntSet set = new IntSet();

        set.add(13);
        assertTrue(set.contains(13));
        set.remove(13);
        assertFalse(set.contains(13));

        set.remove(520);
        assertFalse(set.contains(520));
        set.add(257);
        assertTrue(set.contains(257));
        set.remove(257);
        assertFalse(set.contains(257));
    }

    @Test
    public void contains() throws Exception {
        final IntSet set = new IntSet();
        for (int i = 0; i < 63; i++) {
            assertFalse(set.contains(i));
        }

        set.add(-1);
        set.add(-1);
        set.add(0);
        set.add(0);
        set.add(64);
        set.add(64);
        set.add(63);
        set.add(7);
        set.add(45);
        set.add(513);
        set.add(476);
        set.add(328);

        for (int i = -1; i < 65; i++) {
            if (i == 0 || i == 7 || i == 45 || i == 63 || i == 64 || i == 328 || i == 476 || i == 513) {
                assertTrue("at index" + i, set.contains(i));
            } else {
                assertFalse("At index " + i, set.contains(i));
            }
        }

    }

    @Test
    public void union() throws Exception {
        final IntSet firstSet = new IntSet();
        firstSet.add(0);
        firstSet.add(64);
        firstSet.add(128);
        firstSet.add(256);

        final IntSet secondSet = new IntSet();
        secondSet.add(1);
        secondSet.add(65);
        secondSet.add(129);
        secondSet.add(257);
        secondSet.add(513);

        final IntSet testingSet = firstSet.union(secondSet);

        for (int i = -1; i < 550; i++) {
            if (i == 0 || i == 1 || i == 64 || i == 65 || i == 128 || i == 129 || i == 256 || i == 257 || i == 513) {
                assertTrue("at index" + i, testingSet.contains(i));
            } else {
                assertFalse("At index " + i, testingSet.contains(i));
            }
        }
    }

    @Test
    public void intersection() throws Exception {
        final IntSet firstSet = new IntSet();
        firstSet.add(13);
        firstSet.add(26);
        firstSet.add(84);
        firstSet.add(100);
        firstSet.add(327);
        firstSet.add(46);
        final IntSet secondSet = new IntSet();
        secondSet.add(13);
        secondSet.add(46);
        secondSet.add(327);
        secondSet.add(24);
        secondSet.add(671);
        secondSet.add(433);
        secondSet.add(1);
        secondSet.add(0);
        secondSet.add(8);
        secondSet.add(222);
        secondSet.add(194);

        final IntSet intersectedSet1 = firstSet.intersection(secondSet);

        for (int i = -1; i < 328; i++) {
            if (i == 13 || i == 46 || i == 327) {
                assertTrue("at index" + i, intersectedSet1.contains(i));
            } else {
                assertFalse("At index " + i, intersectedSet1.contains(i));
            }
        }


        final IntSet intersectedSet2 = secondSet.intersection(firstSet);


        for (int i = -1; i < 328; i++) {
            if (i == 13 || i == 46 || i == 327) {
                assertTrue("at index" + i, intersectedSet2.contains(i));
            } else {
                assertFalse("At index " + i, intersectedSet2.contains(i));
            }
        }
    }

    @Test
    public void difference() throws Exception {
        final IntSet firstSet = new IntSet();
        firstSet.add(13);
        firstSet.add(26);
        firstSet.add(84);
        firstSet.add(100);
        firstSet.add(327);
        firstSet.add(46);
        final IntSet secondSet = new IntSet();
        secondSet.add(13);
        secondSet.add(46);
        secondSet.add(327);
        secondSet.add(24);
        secondSet.add(671);
        secondSet.add(433);
        secondSet.add(1);
        secondSet.add(0);
        secondSet.add(8);
        secondSet.add(222);
        secondSet.add(194);

        final IntSet testingSet1 = firstSet.difference(secondSet);

        for (int i = -1; i < 127; i++) {
            if (i == 26 || i == 84 || i == 100) {
                assertTrue("at index" + i, testingSet1.contains(i));
            } else {
                assertFalse("At index " + i, testingSet1.contains(i));
            }
        }

        final IntSet testingSet2 = secondSet.difference(firstSet);

        for (int i = -1; i < 328; i++) {
            if (i == 0 || i == 1 || i == 8 || i == 24 || i == 194 || i == 222 || i == 433 || i == 671) {
                assertTrue("at index" + i, testingSet2.contains(i));
            } else {
                assertFalse("At index " + i, testingSet2.contains(i));
            }
        }
    }

    @Test
    public void isSubsetOf() throws Exception {
        final IntSet firstSet = new IntSet();
        firstSet.add(13);
        firstSet.add(26);
        firstSet.add(84);
        firstSet.add(100);
        firstSet.add(327);
        firstSet.add(46);
        final IntSet secondSet = new IntSet();
        secondSet.add(13);
        secondSet.add(46);
        secondSet.add(327);
        secondSet.add(24);
        secondSet.add(671);
        secondSet.add(433);
        secondSet.add(1);
        secondSet.add(0);
        secondSet.add(8);
        secondSet.add(222);
        secondSet.add(194);
        final IntSet testingSet1 = new IntSet();
        assertTrue(testingSet1.isSubsetOf(secondSet));
        assertFalse(firstSet.isSubsetOf(secondSet));
        testingSet1.add(327);
        assertTrue(testingSet1.isSubsetOf(firstSet));
        assertTrue(testingSet1.isSubsetOf(secondSet));
        testingSet1.add(433);
        assertFalse(testingSet1.isSubsetOf(firstSet));
        assertTrue(testingSet1.isSubsetOf(secondSet));
        testingSet1.add(700);
        assertFalse(testingSet1.isSubsetOf(secondSet));
    }

}