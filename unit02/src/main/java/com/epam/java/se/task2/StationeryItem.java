package com.epam.java.se.task2;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

public class StationeryItem {

    private final String name;

    private final int cost;

    public StationeryItem(@Nonnull String itemName,@Nonnegative int itemCost) {
        this.name = itemName;
        this.cost = itemCost;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
