package com.epam.java.se.task3andtask4.Items;

/**
 * Created by chris on 26.02.2017.
 */
public class Pen extends WritingStationeryItem{

    private final PenType type;

    public Pen(String color, int thickness, PenType type, int cost, String manufacturer) {
        super(color, thickness, cost, manufacturer);
        this.type = type;
    }

    public PenType getType() {
        return type;
    }
}
