package com.epam.java.se.task1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class XMLParser {
    private final String filePath;

    public XMLParser(String filePath) {
        Objects.requireNonNull(filePath);

        checkFileExists(filePath);

        this.filePath = filePath;
    }

    private void checkFileExists(String filePath) {
        final File fileToCheck = new File(filePath);

        if (!fileToCheck.exists() || fileToCheck.isDirectory()) {
            throw new IllegalArgumentException(filePath + " is not valid file");
        }
    }

    public String getContent() throws FileNotFoundException {
        final BufferedReader reader = new BufferedReader(new FileReader(filePath));
        final StringBuilder builder = new StringBuilder();

        reader.lines().forEach(builder::append);
        return builder.toString();
    }

    public List<Transaction> getTransactionsList() throws ParserConfigurationException, IOException, SAXException {
        final NodeList nodeList = getNodeList(filePath, "transaction");

        return convertToTransactionsList(nodeList);
    }

    public List<Account> getAccountsList() throws IOException, SAXException, ParserConfigurationException {
        final NodeList nodeList = getNodeList("accounts.xml", "account");

        return convertToAccountsList(nodeList);
    }

    private List<Account> convertToAccountsList(NodeList nodeList) {
        final List<Account> result = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            result.add((createAccount(nodeList.item(i))));
        }

        return result;
    }

    private Account createAccount(Node item) {
        final StringTokenizer tokenizer =
                new StringTokenizer(item.getTextContent().replaceAll(" ", ""), System.getProperty("line.separator"));

        final int id = Integer.valueOf(tokenizer.nextToken());
        final long balance = Long.valueOf(tokenizer.nextToken());

        return new Account(id, balance);
    }

    private List<Transaction> convertToTransactionsList(NodeList nodeList) {
        final List<Transaction> result = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            result.add(createTransaction(nodeList.item(i)));
        }

        return result;
    }

    private Transaction createTransaction(Node item) {
        final StringTokenizer tokenizer =
                new StringTokenizer(item.getTextContent().replaceAll(" ", ""), System.getProperty("line.separator"));

        final int fromID = Integer.valueOf(tokenizer.nextToken());
        final int toID = Integer.valueOf(tokenizer.nextToken());
        final long amountOfTransfer = Long.valueOf(tokenizer.nextToken());

        return new Transaction(fromID, toID, amountOfTransfer);
    }

    private NodeList getNodeList(String filePath, String tagName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        File file = new File(filePath);
        Document document = builder.parse(file);
        Element root = document.getDocumentElement();

        return root.getElementsByTagName(tagName);
    }
}
