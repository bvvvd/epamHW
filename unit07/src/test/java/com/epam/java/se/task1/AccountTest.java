package com.epam.java.se.task1;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AccountTest {

    @Test
    public void testCreateAccountWithSpecifiedIdAndBalance() {
        final Account account = new Account(0, 10000);

        assertThat(account.getID() == 0 && account.getBalance() == 10000, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAccountWithNegativeBalanceThrowsIllegalArgumentException() {
        final Account account = new Account(0, -1);
    }

    @Test
    public void testDepositMethodWorksCorrectly() {
        final Account account = new Account(0, 0);

        account.deposit(10000);

        assertThat(account.getID() == 0 && account.getBalance() == 10000, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepositMethodWithNegativeAmountThrowsIllegalArgumentException() {
        final Account account = new Account(0, 0);

        account.deposit(-100);
    }

    @Test
    public void testWithdrawMethodWorksCorrectly() {
        final Account account = new Account(0, 10000);

        account.withdraw(10000);

        assertThat(account.getID() == 0 && account.getBalance() == 0, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawMethodWithNegativeAmountThrowsIllegalArgumentException() {
        final Account account = new Account(0, 0);

        account.deposit(-100);
    }


    // TODO: 19.03.2017 equals() and hashcode()
}
