package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomHashMapTest {

    private Map<Integer, String> customMap;

    @Before
    public void init() {
        customMap = new CustomHashMap<>();
    }

    @Test
    public void testThatWeCanCreate() {
        assertThat(customMap, is(notNullValue()));
    }

    @Test
    public void testThatNewMapIsEmpty() {
        assertThat(customMap.isEmpty(), is(true));
    }

    @Test
    public void testThatWeCanAddKeyValuePairAndCheckIt() {
        customMap.put(1, "a");

        assertThat(customMap.containsKey(1), is(true));
    }


    @Test(expected = NullPointerException.class)
    public void testThatWeCanNotPutNullKey() {
        customMap.put(null, "someValue");
    }

    @Test
    public void testThatWeCanPutNullValue() {
        customMap.put(1, null);

        assertThat(customMap.containsKey(1), is(true));
    }

    @Test
    public void testThatPutMethodReturnsPreviousValueOnPuttingExistingKey() {
        customMap.put(1, "a");

        assertThat(customMap.put(1, "b"), is(equalTo("a")));
    }

//    @Test
//    public void testThatMapCanStore20Entries() {
//        fillMap(20);
//
//        IntStream.range(0, 20).forEach(
//                i -> assertThat(customMap.containsKey(i), is(true))
//        );
//    }
//
//    private void fillMap(int endExclusive) {
//        IntStream.range(0, endExclusive).forEach(
//                i -> customMap.put(i, String.valueOf(i))
//        );
//    }
}