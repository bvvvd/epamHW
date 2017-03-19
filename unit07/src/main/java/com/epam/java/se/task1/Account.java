package com.epam.java.se.task1;

public class Account {
    private final int id;
    private long balance;

    public Account(int id, long balance) {
        this.id = id;

        if (balance >= 0) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("balance can not be negative: " + balance);
        }
    }

    public Integer getID() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public void deposit(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount of deposit can not be negative: " + amount);
        }

        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount < 0 ) {
            throw new IllegalArgumentException("amount of withdrawing can not be negative: " + amount);
        }

        balance -= amount;
    }
}
