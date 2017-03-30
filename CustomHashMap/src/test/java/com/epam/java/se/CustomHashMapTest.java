package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
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

    @Test(expected = NullPointerException.class)
    public void testThatWeCanNotPutNullValue() {
        customMap.put(1, null);
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

        CustomHashMap<Object, Object> map = new CustomHashMap<>();

        map.put(key1, String.valueOf(key1));
        map.put(key2, String.valueOf(key2));

        assertThat(map.containsKey(key1), is(true));
        assertThat(map.containsKey(key2), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testThatContainsKeyMethodThrowsNPEIfArgumentIsNull() {
        customMap.containsKey(null);
    }

    @Test(expected = NullPointerException.class)
    public void testThatContainsValueMethodThrowsNPEIfArgumentIsNull() {
        customMap.containsValue(null);
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

    @Test
    public void testThatContainsValueMethodReturnsTrueOnPuttedValue() {
        customMap.put(1, "value");

        assertThat(customMap.containsValue("value"), is(true));
    }

    @Test
    public void testThatContainsValueMethodReturnsFalseOnNotPuttedValue() {
        assertThat(customMap.containsValue("value"), is(false));
    }

    @Test
    public void testThatMapDoesNotContainPuttedPairsAfterClear() {
        fillMap(10);

        customMap.clear();

        IntStream.range(0, 10).forEach(
                i -> assertFalse(customMap.containsKey(i))
        );
    }

    @Test
    public void testThatMapSizeIsZeroAfterClear() {
        int size = 10;

        fillMap(size);

        assertThat(customMap.size(), is(size));

        customMap.clear();

        assertThat(customMap.size(), is(0));
    }

    @Test
    public void testThatMapIsEmptyAfterClear() {
        fillMap(10);

        customMap.clear();

        assertTrue(customMap.isEmpty());
    }

    @Test
    public void testThatWeCanGetValueByPresentedKey() {
        String value = "value";
        int key = 1;
        customMap.put(key, value);

        assertThat(customMap.get(key), is(equalTo(value)));
    }

    @Test(expected = NullPointerException.class)
    public void testThatGetMethodThrowsNPEIfArgumentIsNull() {
        customMap.get(null);
    }

    @Test
    public void testThatGetMethodReturnsNullIfMapDoesNotContainKey() {
        fillMap(20);

        assertThat(customMap.get("nosuchvalue"), is(nullValue()));
    }

    @Test
    public void testThatMapDoesNotContainPairAfterRemovingIt() {
        fillMap(20);
        int key = 18;
        customMap.remove(key);

        assertFalse(customMap.containsKey(key));
    }

    @Test
    public void testThatRemoveMethodReturnsNullIfMapDoesNotContainKey() {
        assertThat(customMap.remove(1), is(nullValue()));
    }

    @Test
    public void testThatRemoveMethodReturnsPreviousValue() {
        fillMap(20);
        int key = 15;

        assertThat(customMap.remove(key), is(equalTo(String.valueOf(key))));
    }

    @Test
    public void testThatRemoveWorksProperlyOnTheTailOfTheBucket() {
        customMap.put(1, "a");
        customMap.put(17, "aa");
        customMap.put(33, "aaa");
        customMap.put(49, "aaaa");

        customMap.remove(1);

        assertFalse(customMap.containsKey(1));
        assertTrue(customMap.containsKey(17));
        assertTrue(customMap.containsKey(49));
        assertTrue(customMap.containsKey(33));
    }

    @Test
    public void testThatKeySetReturnsEmptySetOnEmptyMap() {
        Set set = customMap.keySet();

        assertTrue(set.isEmpty());
    }

    @Test
    public void testThatKeySetWorksProperly() {
        fillMap(20);

        Set set = customMap.keySet();

        IntStream.range(0, 20).forEach(
                set::contains
        );
    }

    @Test(expected = NullPointerException.class)
    public void testThatKeySetRemoveMethodThrowsNPEWithNullArgument() {
        Set set = customMap.keySet();

        set.remove(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testThatKeySetAddThrowsUnsupportedOperationException() {
        Set set = customMap.keySet();

        set.add(1);
    }

    @Test
    public void testThatKeySetRemoveMethodRemovesKeyFromMap() {
        int key = 15;

        fillMap(20);

        Set set = customMap.keySet();

        set.remove(key);

        assertFalse(customMap.containsKey(key));
    }

    @Test
    public void testThatRemovesKeyFromMapRemovesItFromSetKeySet() {
        int key = 10;

        fillMap(20);

        Set set = customMap.keySet();
        assertThat(set.contains(key), is(true));

        customMap.remove(key);
        assertThat(set.contains(key), is(false));
    }

    @Test
    public void testThatKeySetClearMethodClearsMap() {
        fillMap(20);

        Set set = customMap.keySet();

        set.clear();

        assertThat(customMap.isEmpty(), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testThatKeySetContainsMethodThrowsNPEIfArgumentIsNull() {
        fillMap(20);

        Set set = customMap.keySet();

        set.contains(null);
    }

    @Test
    public void testThatMapSizeDecrementingIfSomePairRemoved() {
        fillMap(20);

        assertThat(customMap.size(), is(20));

        customMap.remove(15);

        assertThat(customMap.size(), is(19));
    }

    @Test
    public void testThatKeySetIteratorWorksProperly() {
        int size = 20;
        fillMap(size);

        Set set = customMap.keySet();

        Iterator iterator = set.iterator();

        int count = 0;
        while (iterator.hasNext()) {
            assertThat(customMap.containsKey(iterator.next()), is(true));
            count += 1;
        }

        assertEquals(count, size);
    }

    @Test
    public void testThatKeySetSizeIsTheSameAsMapSize() {
        fillMap(20);

        Set set = customMap.keySet();

        assertEquals(set.size(), customMap.size());
    }

    @Test
    public void testThatKeySetIteratorCanRemoveKeys() {
        fillMap(20);

        Set set = customMap.keySet();

        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            if ((int) iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }

        IntStream.range(0, 20).forEach(
                i -> {
                    if (i % 2 == 0) {
                        assertThat(set.contains(i), is(false));
                    } else {
                        assertThat(set.contains(i), is(true));
                    }
                }
        );
    }

    @Test
    public void testThatRemovingElementsByKeyIteratorRemovesThemFromMap() {
        fillMap(20);

        Set set = customMap.keySet();

        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            if ((int) iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }

        IntStream.range(0, 20).forEach(
                i -> {
                    if (i % 2 == 0) {
                        assertThat(customMap.containsKey(i), is(false));
                    } else {
                        assertThat(customMap.containsKey(i), is(true));
                    }
                }
        );
    }

    @Test
    public void testThatValuesCollectionIsEmptyOnEmptyMap() {
        assertThat(customMap.values().isEmpty(), is(true));
    }

    @Test
    public void testThatValuesCollectionWorksProperly() {
        int size = 20;
        fillMap(size);

        Collection values = customMap.values();
        assertThat(values.size(), is(size));

        IntStream.range(0, 20).forEach(
                i -> assertThat(values.contains(String.valueOf(i)), is(true))
        );
    }

    @Test
    public void testThatValuesClearMethodClearsMap() {
        fillMap(20);

        Collection values = customMap.values();
        values.clear();

        assertTrue(customMap.isEmpty());
    }

    @Test
    public void testThatClearingMapClearsValueCollection() {
        fillMap(20);

        Collection values = customMap.values();

        customMap.clear();

        assertTrue(values.isEmpty());
    }

    @Test
    public void testThatRemovingElementFromMapRemovesValueFromValueCollection() {
        int size = 20;
        fillMap(size);

        Collection values = customMap.values();

        IntStream.range(0, 10).forEach(
                customMap::remove
        );

        assertThat(values.size(), is(equalTo(size / 2)));
    }

    @Test(expected = NullPointerException.class)
    public void testThatValuesContainsMethodThrowsNPEIfArgumentIsNull() {
        Collection values = customMap.values();

        values.contains(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testThatValueCollectionDoesNotSupportAddOperation() {
        Collection values = customMap.values();

        values.add(new Object());
    }

    @Test
    public void testThatValueIteratorWorksProperly() {
        int size = 20;

        fillMap(size);

        Collection values = customMap.values();

        Iterator iterator = values.iterator();

        int count = 0;
        while (iterator.hasNext()) {
            assertTrue(customMap.containsValue(iterator.next()));
            count += 1;
        }

        assertThat(count, is(size));
    }

    @Test
    public void testThatValueIteratorRemovesElements() {
        int size = 20;
        fillMap(size);

        Collection values = customMap.values();

        Iterator iterator = values.iterator();

        int index = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (index < 10) {
                iterator.remove();
            }
            index += 1;
        }

        assertThat(values.size(), is(size / 2));
    }

    @Test
    public void testThatValueIteratorRemovesElementsFromMap() {
        int size = 20;
        fillMap(size);

        Collection values = customMap.values();

        Iterator iterator = values.iterator();

        int index = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (index < 10) {
                iterator.remove();
            }
            index += 1;
        }

        assertThat(customMap.size(), is(size / 2));
    }

    @Test
    public void testThatEntrySetReturnsEmptySetOnEmptyMap() {
        Set entrySet = customMap.entrySet();

        assertTrue(entrySet.isEmpty());
    }

    @Test
    public void testThatEntrySetWorksProperly() {
        int size = 20;
        fillMap(size);

        Set<Map.Entry<Integer, String>> entrySet = customMap.entrySet();

        assertThat(entrySet.size(), is(equalTo(size)));

        for (Map.Entry<Integer, String> entry : entrySet) {
            assertThat(customMap.containsKey(entry.getKey()), is(true));
            assertThat(customMap.get(entry.getKey()), is(entry.getValue()));
        }
    }

    private void fillMap(int endExclusive) {
        IntStream.range(0, endExclusive).forEach(
                i -> customMap.put(i, String.valueOf(i))
        );
    }
}