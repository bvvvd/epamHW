package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.omg.PortableInterceptor.IORInfoOperations;

import java.util.*;
import java.util.stream.IntStream;

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

    @Test
    public void testThatRemovingNotExistingElementReturnsFalse() {
        addValues();

        assertThat(customList.remove(""), is(false));
    }

    @Test
    public void testThatSizeDecrementsWithRemovingElement() {
        addValues();

        customList.remove("b");

        assertThat(customList.size(), is(4));
    }

    @Test
    public void testThatWeCanAddElementByIndex() {
        addValues();
        customList.add(3, "x");

        assertThat(customList.get(0), is("a"));
        assertThat(customList.get(1), is("b"));
        assertThat(customList.get(2), is("c"));
        assertThat(customList.get(3), is("x"));
        assertThat(customList.get(4), is("d"));
        assertThat(customList.get(5), is("e"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantAddElementByIndexMoreThanSizePlusOne() {
        addValues();
        customList.add(8, "x");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantAddElementByNegativeIndex() {
        customList.add(-1, "x");
    }

    @Test
    public void testThatWeCanSetElementByIndex() {
        addValues();
        assertThat(customList.get(0), is(equalTo("a")));

        customList.set(0, "x");
        assertThat(customList.get(0), is(equalTo("x")));
    }

    @Test
    public void testThatSetMethodReturnsPreviousElement() {
        addValues();

        assertThat(customList.set(0, "x"), is(equalTo("a")));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantSetElementWithIndexMoreThanSize() {
        addValues();

        customList.set(20, "a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatWeCantSetElementWithNegativeIndex() {
        addValues();

        customList.set(-1, "a");
    }

    @Test
    public void testThatIndexOfMethodWorksProperlyOnPresentedElement() {
        addValues();

        assertThat(customList.indexOf("c"), is(2));
    }

    @Test
    public void testThatIndexOfMethodReturnsNegative1OnNotPresentedValue() {
        addValues();

        assertThat(customList.indexOf("x"), is(-1));
    }

    @Test
    public void testThatIndexOfMethodWorksProperlyWithNull() {
        addValues();

        customList.add(3, null);

        assertThat(customList.indexOf(null), is(3));
    }

    @Test
    public void testThatLastIndexOfMethodWorksProperlyOnPresentedValue() {
        addValues();

        customList.add("a");

        assertThat(customList.lastIndexOf("a"), is(5));
    }

    @Test
    public void testThatLastIndexOfMethodReturnsNegative1OnNotPresentedValue() {
        addValues();

        assertThat(customList.lastIndexOf("x"), is(-1));
    }

    @Test
    public void testThatLastIndexOfMethodWorksProperlyWithNull() {
        addValues();

        customList.add(3, null);
        customList.add(1, null);

        assertThat(customList.lastIndexOf(null), is(4));
    }

    @Test
    public void testThatIteratorWorksProperly() {
        addValues();

        Iterator<String> iterator = customList.iterator();

        while (iterator.hasNext()) {
            assertThat(customList.contains(iterator.next()), is(true));
        }
    }

    @Test
    public void testThatIteratorRemoveMethodRemovesElementFromList() {
        addValues();

        Iterator<String> iterator = customList.iterator();

        while (iterator.hasNext()) {
            iterator.remove();
        }

        assertThat(customList.isEmpty(), is(true));
    }

    @Test
    public void testThatToArrayMethodWorksProperly() {
        String[] expected = {"a", "b", "c", "d", "e"};

        addValues();

        Object[] actual = customList.toArray();
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void testThatToArrayMethodReturnsEmptyArrayIfListIsEmpty() {
        Object[] actual = customList.toArray();
        assertThat(actual.length, is(0));
    }

    @Test
    public void testThatToArrayMethodSavesOrder() {
        addValues();

        Object[] actual = customList.toArray();

        IntStream.range(0, 5).forEach(
                i -> assertThat(actual[i], is(equalTo(customList.get(i))))
        );
    }

    @Test
    public void testThatToArrayMethodWorksProperlyWithSpecifiedArray() {
        addValues();

        String[] actual = new String[customList.size()];

        actual = customList.toArray(actual);

        assertThat(actual, is(equalTo(customList.toArray())));
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
