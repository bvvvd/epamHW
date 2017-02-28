package com.epam.java.se.task5;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Created by chris on 28.02.2017.
 */
public class Rating <T extends Number> implements Comparable <Rating>{

    private T rating;

    public Rating(@Nonnull T rating) {
        this.rating = rating;
    }

    public T getRating() {
        return rating;
    }

    @Override
    public int compareTo(@Nonnull Rating otherRating) {
        return Double.compare(rating.doubleValue(), otherRating.getRating().doubleValue());
    }

}
