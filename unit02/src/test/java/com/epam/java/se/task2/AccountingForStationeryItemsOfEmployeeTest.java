package com.epam.java.se.task2;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chris on 26.02.2017.
 */
public class AccountingForStationeryItemsOfEmployeeTest {

    @Test
    public void testGetTotalCostOfStationeryItems() {
        final Employee employee = new Employee("mark");
        employee.addItem("pen", 5000);
        employee.addItem("stapler", 20000);
        employee.addItem("corrector", 20000);
        employee.addItem("hole puncher", 15000);
        employee.addItem("pencil", 1000);

        final int totalCost = 61000;

        assertThat(AccountingForStationeryItemsOfEmployee.getTotalCost(employee), is(totalCost));
    }
}
