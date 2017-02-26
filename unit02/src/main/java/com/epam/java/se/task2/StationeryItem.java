package com.epam.java.se.task2;

/**
 * Created by chris on 26.02.2017.
 */
public class StationeryItem {


    private final String name;

    private final int cost;

    public StationeryItem(String itemName, int itemCost) {
        this.name = itemName;
        this.cost = itemCost;
    }

    public int getCost() {
        return cost;
    }
}
