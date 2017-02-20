package com.epam.java.se;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chris on 20.02.2017.
 */
public class Task5Test {
    @Test
    public void test() throws Exception {
        final Task5 task5TestingClass1 = new Task5(2);
        final int[][] testingMatrix1 =
                {{1, 1},
                 {1, 1}};
        Assert.assertArrayEquals(task5TestingClass1.getMatrix(), testingMatrix1);


        final Task5 task5TestingClass2 = new Task5(0);
        final int[][] testingMatrix2 = {};
        Assert.assertArrayEquals(task5TestingClass2.getMatrix(), testingMatrix2);

        final Task5 task5TestingClass3 = new Task5(5);
        final int[][] testingMatrix3 =
                {{1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1}};
        Assert.assertArrayEquals(task5TestingClass3.getMatrix(), testingMatrix3);
    }
}