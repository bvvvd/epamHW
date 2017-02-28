package com.epam.java.se.task3andtask4.Items;

/**
 * Created by chris on 26.02.2017.
 */
public class Pencil extends WritingStationeryItem {

    private final PencilType type;

    public Pencil(String color, int thickness, PencilType type, int cost, String manufacturer) {
        super(color, thickness, cost, manufacturer);
        this.type = type;
    }

    public PencilType getType() {
        return type;
    }
}
