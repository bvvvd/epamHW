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
    public void testTransferCashBetweenTwoAccounts() {
        machine.transfer(firstAccount, secondAccount, 10000);

        assertThat(firstAccount.getBalance() == 0 && secondAccount.getBalance() == 60000, is(true));
    }
}
