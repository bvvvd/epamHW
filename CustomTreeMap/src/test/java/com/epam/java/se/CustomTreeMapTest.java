package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomTreeMapTest {

    private CustomTreeMap<Integer, String> map;

    @Before
    public void init() {
        map = new CustomTreeMap<>();
    }

    @Test
    public void testThatWeCanCreate() {
        assertThat(map, is(notNullValue()));
    }

    @Test
    public void testThatNewMapIsEmpty() {
        assertThat(map.isEmpty(), is(true));
    }

    @Test
    public void testThatWeCanAddKeyValuePairAndCheckIt() {
        map.put(1, "a");
        assertThat(map.containsKey(1), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testThatPutMethodThrowsNPEIfKeyIsNull() {
        map.put(null, "a");
    }

    @Test(expected = NullPointerException.class)
    public void testThatPutMethodThrowsNPEIfValueIsNull() {
        map.put(1, null);
    }

    @Test
    public void testThatWeCanPutPairWithKeyThatAlreadyPresented() {
        String previousValue = "a";
        String nextValue = "b";

        map.put(1, previousValue);
        map.put(1, nextValue);

        assertThat(map.containsValue(previousValue), is(false));
        assertThat(map.containsValue(nextValue), is(true));
    }

    @Test
    public void testThatIfWePutNewValueOnExistingKeyPreviousValueWillBeReturned() {
        String previousValue = "a";
        String nextValue = "b";

        map.put(1, previousValue);
        String returnedValue = map.put(1, nextValue);

        assertThat(returnedValue, is(equalTo(previousValue)));
    }

    @Test
    public void testThatPutMethodReturnsNullIfWePutNewKey() {
        assertThat(map.put(1, "a"), is(nullValue()));
    }

    @Test(expected = NullPointerException.class)
    public void testThatContainsKeyMethodThrowsNPEOnNullKey() {
        map.containsKey(null);
    }

    @Test
    public void testThatContainsKeyMethodReturnsTrueOnPresentedKey() {
        map.put(1, "a");

        assertThat(map.containsKey(1), is(true));
    }

    @Test
    public void testThatContainsKeyMethodReturnsFalseOnNotPresentedKey() {
        map.put(-20, "asdas");

        assertThat(map.containsKey(1), is(false));
    }

    @Test
    public void testThatContainsValueMethodReturnsTrueOnPresentedValue() {
        map.put(1, "a");

        assertThat(map.containsValue("a"), is(true));
    }

    @Test
    public void testThatContainsValueMethodReturnsFalseOnNotPresentedValue() {
        assertThat(map.containsValue("a"), is(false));
    }

    @Test
    public void testThatWeCanPut20KeysValuesInMap() {
        int size = 20;
        fillMap(size);

        IntStream.range(0, size).forEach(
                i -> assertThat(map.containsKey((int) (i * Math.pow(-1, i))), is(true))
        );
    }

    @Test(expected = NullPointerException.class)
    public void testThatContainsValueMethodThrowsNPEIfArgumentIsNull() {
        map.containsValue(null);
    }

    @Test
    public void testThatSizeOfEmptyMapIs0() {
        assertThat(map.size(), is(equalTo(0)));
    }

    @Test
    public void testThatSizeIncrementsWithAddingNewKeys() {
        map.put(1, "a");
        assertThat(map.size(), is(equalTo(1)));

        map.put(2, "b");
        assertThat(map.size(), is(equalTo(2)));
    }

    @Test
    public void testThatSizeDoesNotIncrementsIfWeAddPresentedKey() {
        map.put(1, "a");
        map.put(1, "b");

        assertThat(map.size(), is(equalTo(1)));
    }

    @Test
    public void testThatMapDoesNotContainsPuttedPairsAfterClear() {
        int size = 20;
        fillMap(size);

        map.clear();

        IntStream.range(0, size).forEach(
                i -> assertThat(map.containsKey((int) (i * Math.pow(-1, i))), is(false))
        );
    }

    @Test
    public void testThatMapSizeIs0AfterClear() {
        int size = 20;

        fillMap(size);

        assertThat(map.size(), is(size));

        map.clear();

        assertThat(map.size(), is(0));
    }

    @Test
    public void testThatMapIsEmptyAfterClear() {
        fillMap(20);

        map.clear();

        assertThat(map.isEmpty(), is(true));
    }

    @Test
    public void testThatWeCanGetValueBySpecifiedKey() {
        String value = "value";
        int key = 45;

        map.put(key, value);

        assertThat(map.get(key), is(equalTo(value)));
    }

    @Test(expected = NullPointerException.class)
    public void testThatGetMethodThrowsNPEIfArgumentIsNull() {
        map.get(null);
    }

    @Test
    public void testThatGetMethodReturnsNullIfMapDoesNotContainNodeWithSpecifiedKey() {
        assertThat(map.get(1), is(nullValue()));
    }

    @Test
    public void testThatMapDoesNotContainNodeAfterRemovingItByKey() {
        int key = 1;
        int size = 20;
        fillMap(size);

        map.remove(key);
        assertThat(map.containsKey(key), is(false));
    }

    @Test
    public void testThatRemoveWorksCorrectlyWithRandomlyFilledMap() {
        map.put(10, "a");
        map.put(-10, "a");
        map.put(20, "a");
        map.put(20, "a");
        map.put(250, "a");
        map.put(10, "a");
        map.put(17, "a");
        map.put(1, "a");
        map.put(-60, "a");
        map.put(-12, "a");
        map.put(8, "a");


        map.remove(10);
        map.remove(8);
        map.remove(-12);
        map.remove(250);

        assertThat(map.containsKey(10), is(false));
        assertThat(map.containsKey(8), is(false));
        assertThat(map.containsKey(-12), is(false));
        assertThat(map.containsKey(250), is(false));

        assertThat(map.containsKey(-10), is(true));
        assertThat(map.containsKey(20), is(true));
        assertThat(map.containsKey(17), is(true));
        assertThat(map.containsKey(1), is(true));
        assertThat(map.containsKey(-60), is(true));
    }

    @Test
    public void testThatRemoveWorksCorrectlyIfThereIsOnlyRootInMap() {
        int key = 1;
        map.put(key, "a");

        map.remove(key);
        assertThat(map.containsKey(key), is(false));
    }

    @Test
    public void testThatRemoveMethodReturnsPreviousValueInCaseOfSuccessfulRemoving() {
        map.put(1, "a");

        assertThat(map.remove(1), is(equalTo("a")));
    }

    @Test
    public void testThatRemoveMethodReturnsNullInCaseOfRemovingNotPresentedKey() {
        assertThat(map.remove(1), is(nullValue()));
    }

    @Test(expected = NullPointerException.class)
    public void testThatRemoveMethodThrowsNPEIfArgumentIsNull() {
        map.remove(null);
    }

    @Test
    public void testThatFilledMapIsNotEmpty() {
        fillMap(20);

        assertThat(map.isEmpty(), is(false));
    }

    @Test
    public void testThatContainsValueWorksProperlyWithPresentedValue() {
        fillMap(20);

        assertThat(map.containsValue("5"), is(true));
    }

    @Test
    public void testThatContainsValueWorksProperlyWithNotPresentedValue() {
        fillMap(50);

        assertThat(map.containsValue("100"), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void testThatContainsValueThrowsNPEIfArgumentIsNull() {
        map.containsValue(null);
    }

    @Test
    public void testThatKeySetReturnsEmptySetOnEmptyMap() {
        Set set = map.keySet();

        assertThat(set.isEmpty(), is(true));
    }

    @Test
    public void testThatKeySetWorksProperly() {
        fillMap(20);

        Set set = map.keySet();

        IntStream.range(0, 20).forEach(
                set::contains
        );
    }

    @Test(expected = NullPointerException.class)
    public void testThatKeySetRemoveMethodThrowsNPEWithNullArgument() {
        Set set = map.keySet();

        set.remove(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testThatKeySetAddThrowsUnsupportedOperationException() {
        Set set = map.keySet();

        set.add(1);
    }

    @Test
    public void testThatKeySetRemoveMethodRemovesKeyFromMap() {
        int key = 15;

        fillMap(20);

        Set set = map.keySet();

        set.remove(key);

        assertThat(map.containsKey(key), is(false));
    }

    @Test
    public void testThatRemovesKeyFromMapRemovesItFromSetKeySet() {
        int key = 10;

        fillMap(20);

        Set set = map.keySet();
        assertThat(set.contains(key), is(true));

        map.remove(key);
        assertThat(set.contains(key), is(false));
    }

    @Test
    public void testThatKeySetClearMethodClearsMap() {
        fillMap(20);

        Set set = map.keySet();

        set.clear();

        assertThat(map.isEmpty(), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testThatKeySetContainsMethodThrowsNPEIfArgumentIsNull() {
        fillMap(20);

        Set set = map.keySet();

        set.contains(null);
    }

    @Test
    public void testThatMapSizeDecrementingIfSomePairRemoved() {
        fillMap(20);

        assertThat(map.size(), is(20));

        map.remove(0);

        assertThat(map.size(), is(19));
    }

    @Test
    public void testThatKeySetSizeWorksProperly() {
        Set set = map.keySet();

        assertThat(map.size(), is(equalTo(set.size())));

        fillMap(20);

        set = map.keySet();

        assertThat(map.size(), is(equalTo(set.size())));
    }

    @Test
    public void testThatKeySetIteratorWorksProperly() {
        int size = 20;
        fillMap(size);

        Set set = map.keySet();

        Iterator iterator = set.iterator();

        int count = 0;
        while (iterator.hasNext()) {
            assertThat(map.containsKey(iterator.next()), is(true));
            count += 1;
        }

        assertEquals(count, size);
    }


    @Test
    public void testThatKeySetSizeIsTheSameAsMapSize() {
        fillMap(20);

        Set set = map.keySet();

        assertEquals(set.size(), map.size());
    }

    @Test
    public void testThatKeySetIteratorCanRemoveKeys() {
        IntStream.range(0, 20).forEach(
                i -> map.put(i, String.valueOf(i))
        );

        Set set = map.keySet();

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
        IntStream.range(0, 20).forEach(
                i -> map.put(i, String.valueOf(i))
        );
        Set set = map.keySet();

        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            if ((int) iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }

        IntStream.range(0, 20).forEach(
                i -> {
                    if (i % 2 == 0) {
                        assertThat(map.containsKey(i), is(false));
                    } else {
                        assertThat(map.containsKey(i), is(true));
                    }
                }
        );
    }

    @Test
    public void testThatValuesCollectionIsEmptyOnEmptyMap() {
        assertThat(map.values().isEmpty(), is(true));
    }

    @Test
    public void testThatValuesCollectionWorksProperly() {
        int size = 20;
        fillMap(size);

        Collection values = map.values();
        assertThat(values.size(), is(size));

        IntStream.range(0, 20).forEach(
                i -> assertThat(values.contains(String.valueOf(i)), is(true))
        );
    }

    @Test
    public void testThatValuesClearMethodClearsMap() {
        fillMap(20);

        Collection values = map.values();
        values.clear();

        assertTrue(map.isEmpty());
    }

    @Test
    public void testThatClearingMapClearsValueCollection() {
        fillMap(20);

        Collection values = map.values();

        map.clear();

        assertTrue(values.isEmpty());
    }

    @Test
    public void testThatRemovingElementFromMapRemovesValueFromValueCollection() {
        int size = 20;

        IntStream.range(0, size).forEach(
                i -> map.put(i, String.valueOf(i))
        );

        Collection values = map.values();

        IntStream.range(0, 10).forEach(
                map::remove
        );

        assertThat(values.size(), is(equalTo(size / 2)));
    }

    @Test(expected = NullPointerException.class)
    public void testThatValuesContainsMethodThrowsNPEIfArgumentIsNull() {
        Collection values = map.values();

        values.contains(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testThatValueCollectionDoesNotSupportAddOperation() {
        Collection values = map.values();

        values.add(new Object());
    }

    @Test
    public void testThatValueIteratorWorksProperly() {
        int size = 20;

        fillMap(size);

        Collection values = map.values();

        Iterator iterator = values.iterator();

        int count = 0;
        while (iterator.hasNext()) {
            assertTrue(map.containsValue(iterator.next()));
            count += 1;
        }

        assertThat(count, is(size));
    }

    @Test
    public void testThatValueIteratorRemovesElements() {
        int size = 20;
        fillMap(size);

        Collection values = map.values();

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

        Collection values = map.values();

        Iterator iterator = values.iterator();

        int index = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (index < 10) {
                iterator.remove();
            }
            index += 1;
        }

        assertThat(map.size(), is(size / 2));
    }

    @Test
    public void testThatEntrySetReturnsEmptySetOnEmptyMap() {
        Set entrySet = map.entrySet();

        assertTrue(entrySet.isEmpty());
    }

    @Test
    public void testThatEntrySetWorksProperly() {
        int size = 20;
        fillMap(size);

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        assertThat(entrySet.size(), is(equalTo(size)));

        for (Map.Entry<Integer, String> entry : entrySet) {
            assertThat(map.containsKey(entry.getKey()), is(true));
            assertThat(map.get(entry.getKey()), is(entry.getValue()));
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testThatEntrySetDoesNotSupportAddOperation() {
        Set entrySet = map.entrySet();

        entrySet.add(new Object());
    }

    @Test
    public void testThatEntrySetRemovesMethodRemovesEntryFromMap() {
        int size = 20;
        fillMap(size);

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        for (Map.Entry<Integer, String> entry : entrySet) {
            entrySet.remove(entry);
        }

        assertThat(map.isEmpty(), is(true));
    }

    @Test
    public void testThatRemovingEntriesFromMapRemovesThemFromEntrySet() {
        int size = 5;
        fillMap(size);

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        assertThat(entrySet.size(), is(size));

        IntStream.range(0, size).forEach(
                i -> map.remove((int) (i * Math.pow(-1, i)))
        );

        assertTrue(entrySet.isEmpty());
    }

    @Test
    public void testThatClearMethodOnMapClearsEntrySet() {
        int size = 5;
        fillMap(size);

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        assertThat(entrySet.size(), is(size));

        map.clear();

        assertTrue(entrySet.isEmpty());
    }

    @Test
    public void testThatClearMethodOnEntrySetClearsMap() {
        int size = 5;
        fillMap(size);

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        assertThat(entrySet.size(), is(size));

        entrySet.clear();

        assertTrue(map.isEmpty());
    }

    @Test
    public void testThatEntrySetRemovesMethodReturnsCorrectResult() {
        int size = 20;
        fillMap(size);

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        CustomTreeMap<Integer, String> otherMap = new CustomTreeMap<>();
        otherMap.put(20, "20");
        otherMap.put(-5, "5");
        Set<Map.Entry<Integer, String>> otherMapEntrySet = otherMap.entrySet();

        for (Map.Entry<Integer, String> entry : otherMapEntrySet) {
            if (entry.getKey() == size) {
                assertThat(entrySet.remove(entry), is(false));
            } else {
                assertThat(entrySet.remove(entry), is(true));
            }
        }
    }

    @Test
    public void testThatEntrySetContainsMethodWorksProperly() {
        int size = 20;
        fillMap(size);
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        CustomTreeMap<Integer, String> otherMap = new CustomTreeMap<>();
        otherMap.put(100, "100");
        otherMap.put(-100, "100");
        otherMap.put(10, "10");

        for (Map.Entry<Integer, String> entry : otherMap.entrySet()) {
            if (entry.getKey() == 10) {
                assertThat(entrySet.contains(entry), is(true));
            } else {
                assertThat(entrySet.contains(entry), is(false));
            }
        }
    }

    @Test
    public void testThatEntrySetIteratorWorksProperly() {
        int size = 20;
        fillMap(size);

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        Iterator<Map.Entry<Integer, String>> entryIterator = entrySet.iterator();

        int count = 0;
        while (entryIterator.hasNext()) {
            count += 1;
            Map.Entry<Integer, String> currentEntry = entryIterator.next();
            assertThat(map.containsKey(currentEntry.getKey()), is(true));
            assertThat(map.get(currentEntry.getKey()), is(equalTo(currentEntry.getValue())));
        }

        assertThat(count, is(equalTo(size)));
    }

    @Test
    public void testThatEntrySetIteratorCanRemoveEntries() {
        int size = 20;
        fillMap(size);

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        Iterator<Map.Entry<Integer, String>> entryIterator = entrySet.iterator();

        int count = 0;
        while (entryIterator.hasNext()) {
            count += 1;
            entryIterator.next();
            entryIterator.remove();
        }

        assertThat(count, is(equalTo(size)));
        assertThat(entrySet.isEmpty(), is(true));
    }

    @Test
    public void testThatEntrySetIteratorCanRemovesEntriesFromMap() {
        int size = 20;
        fillMap(size);

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        Iterator<Map.Entry<Integer, String>> entryIterator = entrySet.iterator();

        int count = 0;
        while (entryIterator.hasNext()) {
            count += 1;
            entryIterator.next();
            entryIterator.remove();
        }

        assertThat(map.isEmpty(), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testThatEntrySetContainsMethodThrowsNPEIfArgumentIsNull() {
        Set entrySet = map.entrySet();

        entrySet.contains(null);
    }

    @Test(expected = NullPointerException.class)
    public void testThatEntrySetRemoveMethodThrowsNPEIfArgumentIsNull() {
        Set entrySet = map.entrySet();

        entrySet.remove(null);
    }

    @Test
    public void testThatEntrySetContainsMethodReturnFalseOnNotPresentedEntry() {
        int size = 20;
        fillMap(size);

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        Map<Integer, String> otherMap = new TreeMap<>();
        otherMap.put(40, "40");

        for (Map.Entry<Integer, String> entry : otherMap.entrySet()) {
            assertThat(entrySet.contains(entry), is(false));
        }
    }

    @Test
    public void testThatPutAllWorksProperly() {
        int size = 20;
        IntStream.range(0, size).forEach(
                i -> map.put(i, String.valueOf(i))
        );

        Map<Integer, String> otherMap = new HashMap<>();
        IntStream.range(size, size * 2).forEach(
                i -> otherMap.put(i, String.valueOf(i))
        );

        map.putAll(otherMap);

        assertThat(map.size(), is(equalTo(size * 2)));

        IntStream.range(0, size * 2).forEach(
                i -> assertThat(map.containsKey(i), is(true))
        );
    }

    private void fillMap(int amount) {
        IntStream.range(0, amount).forEach(
                i -> map.put((int) (i * Math.pow(-1, i)), String.valueOf(i))
        );
    }
}
