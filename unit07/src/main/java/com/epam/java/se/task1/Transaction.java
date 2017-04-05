package com.epam.java.se.task1;

public class Transaction {
    private final int fromID;
    private final int toID;
    private final long amountOfTransfer;

    public Transaction(int fromID, int toID, long amountOfTransfer) throws InvalidTransferAmountException {
        checkAmount(amountOfTransfer);

        this.fromID = fromID;
        this.toID = toID;
        this.amountOfTransfer = amountOfTransfer;
    }

    private void checkAmount(long amountOfTransfer) throws InvalidTransferAmountException {
        if (amountOfTransfer < 0) {
            throw new InvalidTransferAmountException("amount of transfer can not be negative " + amountOfTransfer);
        }
    }

    public int getFromID() {
        return fromID;
    }

    public int getToID() {
        return toID;
    }

    public long getAmountOfTransfer() {
        return amountOfTransfer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (fromID != that.fromID) return false;
        if (toID != that.toID) return false;
        return amountOfTransfer == that.amountOfTransfer;
    }

    @Override
    public int hashCode() {
        int result = fromID;
        result = 31 * result + toID;
        result = 31 * result + (int) (amountOfTransfer ^ (amountOfTransfer >>> 32));
        return result;
    }
}
