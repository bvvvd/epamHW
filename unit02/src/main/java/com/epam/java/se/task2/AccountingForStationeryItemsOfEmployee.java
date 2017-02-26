package com.epam.java.se.task2;

import javax.annotation.Nonnull;

/**
 * Created by chris on 26.02.2017.
 */
public class AccountingForStationeryItemsOfEmployee {

    public static int getTotalCost(@Nonnull Employee employee) {
        int totalCost = 0;
        for (StationeryItem stationeryItem: employee.getItemList()){
            totalCost += stationeryItem.getCost();
        }
        return totalCost;
    }
}
