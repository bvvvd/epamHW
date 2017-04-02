package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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
    public void testThatRemoveMethodReturnsNullInCaseOfRemovingNotPresentedKey () {
        assertThat(map.remove(1), is(nullValue()));
    }

    @Test(expected = NullPointerException.class)
    public void testThatRemoveMethodThrowsNPEIfArgumentIsNull() {
        map.remove(null);
    }

    private void fillMap(int amount) {
        IntStream.range(0, amount).forEach(
                i -> map.put((int) (i * Math.pow(-1, i)), String.valueOf(i))
        );
    }
}
