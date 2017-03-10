package com.epam.java.se.task3;

import org.junit.Test;

import java.io.IOException;

public class RecoderTest {

    @Test(expected = NullPointerException.class)
    public void testThatNullArgumentThrowsNullPointerException() {
        final Recoder recoder = new Recoder(null, "UTF-8");
    }

    @Test
    public void testThatWorksCorrectly() throws IOException {
        final Recoder recoder = new Recoder("utf8.txt", "UTF-8");
        recoder.rewriteWithNewCode("outputTask3.txt", "UTF-16");
    }

}
