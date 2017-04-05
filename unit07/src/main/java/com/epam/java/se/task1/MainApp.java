package com.epam.java.se.task1;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;

public class MainApp {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, InvalidTransferAmountException {

        XMLParser parser = new XMLParser("transactions.xml");

        List<Account> accountsList = parser.getAccountsList();
        List<Transaction> transactionList = parser.getTransactionsList();

        BankMachine machine = new BankMachine();

        transactionList.parallelStream().forEach((transaction) -> {
            try {
                machine.transfer(transaction, accountsList);
            } catch (InterruptedException | InvalidTransferAmountException e) {
                e.printStackTrace();
            }
        });

        accountsList.forEach(account -> System.out.println(account.getBalance()));
    }
}
