package com.epam.java.se.task3andtask4;

import com.epam.java.se.task3andtask4.Items.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by chris on 26.02.2017.
 */
public class NewbieKitTest {

    @Test
    public void testNewbieKit() throws Exception {
        NewbieKit newbieKit = new NewbieKit();

        List<StationeryItem> stationeryItemList = newbieKit.getItemList();

        assertTrue(stationeryItemList.get(0) instanceof Pen);
        assertTrue(stationeryItemList.get(1) instanceof Pencil);
        assertTrue(stationeryItemList.get(2) instanceof Eraser);
        assertTrue(stationeryItemList.get(3) instanceof Blocknote);
    }
}
