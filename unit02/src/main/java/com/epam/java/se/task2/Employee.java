package com.epam.java.se.task2;

import javax.annotation.Nonnull;
import java.util.ArrayList;

/**
 * Created by chris on 26.02.2017.
 */
public class Employee {


    private final String name;

    private ArrayList<StationeryItem> itemList;

    public Employee(@Nonnull String name) {
        this.name = name;
        itemList = new ArrayList<>();
    }

    public void addItem(String itemName, int cost) {
        StationeryItem stationeryItem = new StationeryItem(itemName, cost);
        itemList.add(stationeryItem);
    }

    public ArrayList<StationeryItem> getItemList() {
        return itemList;
    }
}
