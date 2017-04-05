package com.epam.java.se.task1;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class XMLParserTest {
    private XMLParser parser;
    private String filepath = "transactions.xml";

    @Before
    public void init() {
        this.parser = new XMLParser(filepath);
    }

    @Test
    public void testThatWeCanGetContentFromXML() throws FileNotFoundException {
        final BufferedReader reader = new BufferedReader(new FileReader(filepath));
        final StringBuilder builder = new StringBuilder();

        reader.lines().forEach(builder::append);
        final String expected = builder.toString();
        assertThat(parser.getContent().equals(expected), is(true));
    }

    @Test
    public void testThatWeCanExtractListNodesFromXML() throws IOException, SAXException, ParserConfigurationException, InvalidTransferAmountException {
        final List<Transaction> transactionList = parser.getTransactionsList();

        final Transaction firstTransaction = new Transaction(0, 2, 1000);
        final Transaction lastTransaction = new Transaction(2, 0, 1500);

        assertThat(transactionList.get(0).equals(firstTransaction), is(true));
        assertThat(transactionList.get(transactionList.size() - 1).equals(lastTransaction), is(true));
    }
}