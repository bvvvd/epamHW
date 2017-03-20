package com.epam.java.se.task1;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankMachineTest {
    private BankMachine machine;
    private Account firstAccount;
    private Account secondAccount;

    @Before
    public void init() {
        this.machine = new BankMachine();
        this.firstAccount = new Account(1, 10000);
        this.secondAccount = new Account(2, 50000);
    }

    @Test
    public void testTransferCashBetweenTwoAccounts() throws InvalidTransferAmountException, InterruptedException {
        machine.transfer(firstAccount, secondAccount, 10000);

        assertThat(firstAccount.getBalance() == 0 && secondAccount.getBalance() == 60000, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransferCashBetweenTwoAccountsWithNegativeAmountOfTransferThrowsIllegalArgumentException() throws InvalidTransferAmountException, InterruptedException {
        machine.transfer(firstAccount, secondAccount, -1);
    }

    @Test(expected = InvalidTransferAmountException.class)
    public void testTransferCashBetweenTwoAccountsWithAmountOfTransferMoreThanAccountOfWithdrawBalanceThrowsInvalidTransferAmountException() throws InvalidTransferAmountException, InterruptedException {
        machine.transfer(firstAccount, secondAccount, 1000000);
    }

    @Test(expected = NullPointerException.class)
    public void testTransferWithNullFirstAccountThrowsNPE() throws InvalidTransferAmountException, InterruptedException {
        machine.transfer(null, secondAccount, 0);
    }

    @Test(expected = NullPointerException.class)
    public void testTransferWithNullSecondAccountThrowsNPE() throws InvalidTransferAmountException, InterruptedException {
        machine.transfer(firstAccount, null, 0);
    }
}
