package com.epam.java.se.task3andtask4;

import com.epam.java.se.task3andtask4.Items.*;
import com.epam.java.se.task3andtask4.NewbieKit;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by chris on 26.02.2017.
 */
public class StationeryItemsSorterTest {

    @Test
    public void testSortByCost() throws Exception {
        final NewbieKit newbieKit = new NewbieKit();

        newbieKit.sortItemsByCost();

        final List<StationeryItem> sortedList = newbieKit.getItemList();

        assertTrue(sortedList.get(0) instanceof Eraser);
        assertTrue(sortedList.get(1) instanceof Pencil);
        assertTrue(sortedList.get(2) instanceof Pen);
        assertTrue(sortedList.get(3) instanceof Blocknote);
    }

    @Test
    public void testSortByManufacturer() throws Exception {
        final NewbieKit newbieKit = new NewbieKit();

        newbieKit.sortItemsByManufacturer();

        final List<StationeryItem> sortedList = newbieKit.getItemList();

        assertTrue(sortedList.get(0) instanceof Pencil);
        assertTrue(sortedList.get(1) instanceof Blocknote);
        assertTrue(sortedList.get(2) instanceof Pen);
        assertTrue(sortedList.get(3) instanceof Eraser);
    }

    @Test
    public void testSortByCostAndManufacturer() throws Exception {
        final NewbieKit newbieKit = new NewbieKit();

        newbieKit.sortItemsByCostAndManufacturer();

        final List<StationeryItem> sortedList = newbieKit.getItemList();

        assertTrue(sortedList.get(0) instanceof Eraser);
        assertTrue(sortedList.get(1) instanceof Pencil);
        assertTrue(sortedList.get(2) instanceof Pen);
        assertTrue(sortedList.get(3) instanceof Blocknote);
    }
}
