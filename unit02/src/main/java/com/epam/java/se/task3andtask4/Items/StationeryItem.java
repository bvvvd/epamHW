package com.epam.java.se.task3andtask4.Items;

/**
 * Created by chris on 26.02.2017.
 */
public abstract class StationeryItem {

    private int cost;

    private String manufacturer;

    public StationeryItem(int cost, String manufacturer) {
        this.cost = cost;
        this.manufacturer = manufacturer;
    }

    public int getCost() {
        return cost;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
