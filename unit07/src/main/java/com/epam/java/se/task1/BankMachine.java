package com.epam.java.se.task1;

import java.util.List;
import java.util.Objects;

public class BankMachine {
    public void transfer(Account firstAccount, Account secondAccount, long amount) throws InvalidTransferAmountException, InterruptedException {
        Objects.requireNonNull(firstAccount);
        Objects.requireNonNull(secondAccount);

        checkTransferAmountValidity(firstAccount.getBalance(), amount);

        secondAccount.deposit(amount);
        firstAccount.withdraw(amount);
    }

    private void checkTransferAmountValidity(long firstAccountBalance, long amount) throws InvalidTransferAmountException {
        if (amount < 0) {
            throw new IllegalArgumentException("amount of transfer can not be negative: " + amount);
        }

        if (amount > firstAccountBalance) {
            throw new
                    InvalidTransferAmountException(
                    "amount of transfer can not be more than account's of withdraw balance: amount of withdrawing "
                            + amount + " - " + firstAccountBalance);
        }
    }


    public void transfer(Transaction transaction, List<Account> accountsList) throws InterruptedException, InvalidTransferAmountException {
        boolean containsTo = false;
        boolean containsFrom = false;
        Account accountFrom = null;
        Account accountTo = null;

        for (Account account : accountsList) {
            if (account.getID() == transaction.getToID()) {
                containsTo = true;
                accountFrom = account;
            }
            if (account.getID() == transaction.getFromID()) {
                containsFrom = true;
                accountTo = account;
            }
        }
        if (containsFrom && containsTo) {
            transfer(accountFrom, accountTo, transaction.getAmountOfTransfer());
        }
    }
}
