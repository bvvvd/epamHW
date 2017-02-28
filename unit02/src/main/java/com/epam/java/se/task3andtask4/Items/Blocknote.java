package com.epam.java.se.task3andtask4.Items;

/**
 * Created by chris on 26.02.2017.
 */
public class Blocknote extends ItemsForNote {

    private final int numberOfPages;

    public Blocknote(int numberOfPages, int cost, String manufacturer) {
        super(cost, manufacturer);
        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}
