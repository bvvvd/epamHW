package com.epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chris on 20.02.2017.
 */
public class Task4Test {
    @Test
    public void findMaxWithStrangeWay() throws Exception {
        final double[] testArray1 = {1, 1.2, -7.634, 40, 8000.23, 0, 1, 1, 653.151};

        final double[] testArray2 = {1, 1.2, -7.634, 40, 0.23, 0, 1, 1, 653.151};


        final Task4 task4TestingClass1 = new Task4(testArray1);
        assertEquals(task4TestingClass1.findMaxWithStrangeWay(),testArray1[4] * 2, 0.000001);

        final Task4 task4TestingClass2 = new Task4(testArray2);
        assertEquals(task4TestingClass2.findMaxWithStrangeWay(),
                testArray2[0] + testArray2[testArray2.length - 1], 0.000001);
    }

}