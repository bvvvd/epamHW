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
        itemList.add(new Pen("blue", 10, PenType.BALLPOINT, 50, "Prarker"));
        itemList.add(new Pencil("black", 10, PencilType.SIMPLE, 30, "Delta"));
        itemList.add(new Eraser(5, "Stabilo"));
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

    public void sortItemsByManufacturer() {

        Comparator<StationeryItem> comparator = new Comparator<StationeryItem>() {
            @Override
            public int compare(StationeryItem item1, StationeryItem item2) {
                return item1.getManufacturer().compareTo(item2.getManufacturer());
            }
        };

        Collections.sort(itemList, comparator);

    }

    public void sortItemsByCostAndManufacturer() {
        Comparator<StationeryItem> comparator = new Comparator<StationeryItem>() {
            @Override
            public int compare(StationeryItem item1, StationeryItem item2) {
                int mainComparator = Integer.compare(item1.getCost(), item2.getCost());
                if (mainComparator != 0) {
                    return mainComparator;
                } else {
                    return item1.getManufacturer().compareTo(item2.getManufacturer());
                }
            }
        };

        Collections.sort(itemList, comparator);
    }
}
