package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;

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
}
