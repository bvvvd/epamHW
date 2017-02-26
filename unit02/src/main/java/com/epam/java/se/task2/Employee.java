package com.epam.java.se.task2;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.ArrayList;

public class Employee {

    private final String name;

    private ArrayList<StationeryItem> itemList;

    public Employee(@Nonnull String name) {
        this.name = name;
        itemList = new ArrayList<>();
    }

    public void addItem(@Nonnull String itemName, @Nonnegative int cost) {
        StationeryItem stationeryItem = new StationeryItem(itemName, cost);
        itemList.add(stationeryItem);
    }

    public ArrayList<StationeryItem> getItemList() {
        return itemList;
    }

    public String getName() {
        return name;
    }
}
