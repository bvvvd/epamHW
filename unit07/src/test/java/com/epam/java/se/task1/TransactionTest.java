package com.epam.java.se.task1;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TransactionTest {

    @Test
    public void testThatWeCanCreateTransaction() throws InvalidTransferAmountException {
        final Transaction transaction = new Transaction(0,1,10);
        assertThat(transaction.getFromID() == 0 && transaction.getToID() == 1 && transaction.getAmountOfTransfer() == 10, is(true));
    }

    @Test(expected = InvalidTransferAmountException.class)
    public void testThatWeCanNotCreateTransactionWithNegativeAmountOfTransfer() throws InvalidTransferAmountException {
        final Transaction transaction = new Transaction(0,1,-10);
    }
}
