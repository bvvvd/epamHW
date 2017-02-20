package com.epam.java.se;

/**
 * Created by chris on 20.02.2017.
 */
public class Task4 {

    private final double[] values;

    public Task4(double[] values) {
        this.values = values;
    }

    private int getLength() {
        return this.values.length;
    }

    public double findMaxWithStrangeWay() {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < getLength() / 2 + 1; i++) {
            max = (max < values[i] + values[getLength() - 1 - i]) ? values[i] + values[getLength() - 1 - i] : max;
        }
        return max;
    }

}
