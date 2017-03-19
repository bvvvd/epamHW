package com.epam.java.se;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class test {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();

        File file = new File("pom.xml");
        Document document = builder.parse(file);
        Element root = document.getDocumentElement();

        NodeList nodeList = root.getElementsByTagName("version");

        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println((nodeList.item(i)).getTextContent());
        }
    }
}
