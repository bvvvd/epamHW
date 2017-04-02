package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
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

    private void fillMap(int amount) {
        IntStream.range(0, amount).forEach(
                i -> map.put((int) (i * Math.pow(-1, i)), String.valueOf(i))
        );
    }
}
