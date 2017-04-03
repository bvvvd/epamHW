package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CustomListsTest {

    private List<String> customList;

    public CustomListsTest(List<String> list) {
        this.customList = list;
    }

    @Parameterized.Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[]{
                new CustomLinkedList<>(),
                new CustomArrayList<>()
        });
    }

    @Before
    public void init() {
        customList.clear();
    }

    @Test
    public void testThatNewListIsEmpty() {
        assertTrue(customList.isEmpty());
    }

    @Test
    public void testThatListIsNotEmptyIfWeAddedElement() {
        final String e = "a";

        customList.add(e);

        assertFalse(customList.isEmpty());
    }

    @Test
    public void testThatListContainsAddedElement() {
        final String e = "a";

        customList.add(e);

        assertThat(customList.contains(e), is(true));
    }

    @Test
    public void testThatListDoesNotContainsNotAddedElement() {
        customList.add("1");

        assertFalse(customList.contains("2"));
    }

    @Test
    public void testThatListContainsNullIfItWasAdded() {
        customList.add(null);

        assertThat(customList.contains(null), is(true));
    }

    @Test
    public void testThatListNotContainsNullIfItWasNotAdded() {
        customList.add("1");

        assertFalse(customList.contains(null));
    }

    @Test
    public void testThatListsSizeIsDynamic() {
        for (int i = 0; i < 50; i++) {
            customList.add(String.valueOf(i));
        }

        int size = 50;
        assertThat(customList.size(), is(size));
    }

//    @Test
//    public void testThatWeCanAddElementAtParticularPosition() {
//    }

    @Test
    public void testThatWeCanGetElementByIndex() {
        customList.add("a");
        customList.add("b");
        customList.add("c");

        assertThat(customList.get(1), is(equalTo("b")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantGetElementByIndexMoreThanSize() {
        customList.get(customList.size());
    }


    @Test
    public void testThatWeCanRemoveExistingElement() {
        String value = "a";

        customList.add(value);

        customList.remove(value);

        assertFalse(customList.contains(value));
    }

    @Test
    public void testThatWeCanDeleteElementByIndex() {
        addValues();

        customList.remove(1);

        assertThat(customList.get(1), is(equalTo("c")));
    }

    @Test
    public void testThatRemovingExistingElementReturnsThatElement() {
        addValues();

        assertThat(customList.remove(0), is(equalTo("a")));
    }
    private void addValues() {
        customList.add("a");
        customList.add("b");
        customList.add("c");
        customList.add("d");
        customList.add("e");
    }

    public List<String> getCustomList() {
        return customList;
    }
}
