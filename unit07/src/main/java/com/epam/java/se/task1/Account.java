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
        if (amount < 0) {
            throw new IllegalArgumentException("amount of withdrawing can not be negative: " + amount);
        }

        balance -= amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        if (id != account.id) {
            return false;
        }

        return balance == account.balance;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (balance ^ (balance >>> 32));
        return result;
    }
}
