package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testThatPutMethodReturnsNullOnPuttingNewKey() {
        assertThat(customMap.put(1, "a"), is(nullValue()));
    }

    @Test
    public void testThatContainsKeyReturnsTrueOnPuttedKey() {
        customMap.put(1, "a");

        assertTrue(customMap.containsKey(1));
    }

    @Test
    public void testThatContainsKeyReturnsFalseOnNotPuttedKey() {
        assertFalse(customMap.containsKey(1));
    }

    @Test
    public void testThatMapIsNotEmptyAfterWePutSomePairs() {
        customMap.put(1, "a");

        assertFalse(customMap.isEmpty());
    }

    @Test
    public void testThatMapCanStore20Entries() {
        fillMap(20);

        IntStream.range(0, 20).forEach(
                i -> assertThat(customMap.containsKey(i), is(true))
        );
    }

    @Test
    public void testThatMapCanStoreKeysWithEqualsHashcodes() {
        Object key1 = "a";
        Object key2 = 97;

        assertThat(key1.hashCode(), is(equalTo(key2.hashCode())));

        CustomHashMap<Object, String> customHashMap = new CustomHashMap<>();

        customHashMap.put(key1, String.valueOf(key1));
        customHashMap.put(key2, String.valueOf(key2));

        assertThat(customHashMap.containsKey(key1), is(true));
        assertThat(customHashMap.containsKey(key2), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testThatContainsKeyMethodThrowsNPEIfArgumentIsNull() {
        customMap.containsKey(null);
    }

    @Test
    public void testThatSizeOfNewMapIsZero() {
        assertThat(customMap.size(), is(0));
    }

    @Test
    public void testThatMapSizeIncrementingOnPuttingNewPairs() {
        assertThat(customMap.size(), is(0));

        fillMap(20);

        assertThat(customMap.size(), is(20));
    }

    @Test
    public void testThatMapDoesNotStoreDuplicateKeysPairs() {
        String oldValue = "oldValue";
        String newValue = "newValue";
        Integer key = 1;

        customMap.put(key, oldValue);
        customMap.put(key, newValue);

        assertThat(customMap.containsValue(newValue), is(true));
        assertThat(customMap.containsValue(oldValue), is(false));
    }

    private void fillMap(int endExclusive) {
        IntStream.range(0, endExclusive).forEach(
                i -> customMap.put(i, String.valueOf(i))
        );
    }
}