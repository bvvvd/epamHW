package com.epam.java.se.task3;

import com.epam.java.se.task3.Items.*;

import java.util.ArrayList;

/**
 * Created by chris on 26.02.2017.
 */
public class NewbieKit {

    private final ArrayList<StationeryItem> itemList;

    public NewbieKit() {
        this.itemList = new ArrayList<StationeryItem>();
        init();
    }

    private void init() {
        itemList.add(new Pen("blue", 10, PenType.BALLPOINT, 50, "Stabilo"));
        itemList.add(new Pencil("black", 10, PencilType.SIMPLE, 30, "Stabilo"));
        itemList.add(new Eraser(10, "Stabilo"));
        itemList.add(new Blocknote(64, 100, "Moleskine"));
    }

    public ArrayList<StationeryItem> getItemList() {
        return itemList;
    }
}
