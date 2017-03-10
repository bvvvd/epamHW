package com.epam.java.se.task3andtask4.Items;

/**
 * Created by chris on 26.02.2017.
 */
public abstract class WritingStationeryItem extends StationeryItem {

    private final String color;

    private final int thickness;

    public WritingStationeryItem(String color, int thickness, int cost, String manufacturer) {
        super(cost, manufacturer);
        this.color = color;
        this.thickness = thickness;
    }

    public String getColor() {
        return color;
    }

    public int getThickness() {
        return thickness;
    }
}
