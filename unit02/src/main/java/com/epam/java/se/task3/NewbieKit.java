package com.epam.java.se.task3;

import com.epam.java.se.task3.Items.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by chris on 26.02.2017.
 */
public class NewbieKit {

    private final ArrayList<StationeryItem> itemList;

    public NewbieKit() {
        this.itemList = new ArrayList<>();
        init();
    }

    private void init() {
        itemList.add(new Pen("blue", 20, PenType.BALLPOINT, 50, "Prarker"));
        itemList.add(new Pencil("black", 10, PencilType.SIMPLE, 30, "Stabilo"));
        itemList.add(new Eraser(5, "Delta"));
        itemList.add(new Blocknote(64, 100, "Moleskine"));
    }

    public ArrayList<StationeryItem> getItemList() {
        return itemList;
    }

    public void sortItemsByCost() {
        Comparator<StationeryItem> comparator = new Comparator<StationeryItem>() {
            @Override
            public int compare(StationeryItem item1, StationeryItem item2) {
                return Integer.compare(item1.getCost(), item2.getCost());
            }
        };

        Collections.sort(itemList, comparator);
    }
}
