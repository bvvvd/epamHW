package com.epam.java.se.task1;

import java.util.Objects;

public class BankMachine {
    public synchronized void transfer(Account firstAccount, Account secondAccount, int amount) {
        Objects.requireNonNull(firstAccount);
        Objects.requireNonNull(secondAccount);

        checkTransferAmountValidity(amount);

        secondAccount.deposit(amount);
        firstAccount.withdraw(amount);
    }

    private void checkTransferAmountValidity(int amount) {
    }
}
