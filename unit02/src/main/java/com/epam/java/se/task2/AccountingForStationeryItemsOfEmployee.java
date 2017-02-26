package com.epam.java.se.task2;

import javax.annotation.Nonnull;

/**
 * Provide an opportunity to account total cost of employee's stationery items
 *
 * @author Valeriy Burmistrov
 */
public class AccountingForStationeryItemsOfEmployee {

    /**
     * accounts total cost of all stationery items owned by employee
     *
     * @param employee employee whose items cost needed to account
     * @return total cost
     * @throws  IllegalArgumentException
     *          If the {@code employee} argument is null
     */
    public static int getTotalCost(@Nonnull Employee employee) {
        int totalCost = 0;
        for (StationeryItem stationeryItem: employee.getItemList()){
            totalCost += stationeryItem.getCost();
        }

        return totalCost;
    }
}
