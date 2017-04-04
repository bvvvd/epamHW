package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.omg.PortableInterceptor.IORInfoOperations;

import java.io.File;
import java.util.*;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

    @Test(expected = NullPointerException.class)
    public void testThatToArrayThrowsNPEIfArgumentIsNull() {
        addValues();

        String[] actual = customList.toArray(null);
    }

    @Test(expected = ArrayStoreException.class)
    public void testThatToArrayThrowsArrayStoreExceptionIfTypeOfArraysAreNotCompatible() {
        addValues();

        File[] files = new File[customList.size()];

        files = customList.toArray(files);
    }

    @Test
    public void testThatArrayGeneratedByToArrayMethodStoresNullOnIndexSizeIfLengthOfArrayIsSmallerThanListSize() {
        addValues();

        String[] actual = new String[customList.size() * 2];

        actual = customList.toArray(actual);

        assertThat(actual[customList.size()], is(nullValue()));
    }

    @Test
    public void testThatAddAllMethodWorksProperly() {
        addValues();
        int firstListSize = 5;

        List<String> list = new ArrayList<>();
        list.add("f");
        list.add("g");
        list.add("h");
        int secondListSize = 3;

        customList.addAll(list);
        int resultingSize = firstListSize + secondListSize;

        assertThat(customList.size(), is(equalTo(resultingSize)));

        String[] expectedArray = {"a", "b", "c", "d", "e", "f", "g", "h"};
        for (int i = 0; i < expectedArray.length; i++) {
            assertThat(customList.get(i), is(equalTo(expectedArray[i])));
        }
    }

    @Test
    public void testThatAddAllReturnsTrueIfListWasChanged() {
        addValues();

        List<String> list = new ArrayList<>();
        list.add("f");
        list.add("g");
        list.add("h");

        assertThat(customList.addAll(list), is(true));
    }

    @Test
    public void testThatAddAllReturnsFalseIfListWasNotChanged() {
        addValues();

        List<String> list = new ArrayList<>();

        assertThat(customList.addAll(list), is(false));
    }

    @Test
    public void testThatAddAllWithIndexWorksProperly() {
        addValues();
        int firstListSize = 5;

        List<String> list = new ArrayList<>();
        list.add("f");
        list.add("g");
        list.add("h");
        int secondListSize = 3;

        customList.addAll(3, list);
        int resultingSize = firstListSize + secondListSize;

        assertThat(customList.size(), is(equalTo(resultingSize)));

        String[] expectedArray = {"a", "b", "c", "f", "g", "h", "d", "e"};
        for (int i = 0; i < expectedArray.length; i++) {
            assertThat(customList.get(i), is(equalTo(expectedArray[i])));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatPutAllThrowsIndexOutOfBoundIfSpecifiedIndexIfMoreThanSize() {
        List<String> list = new ArrayList<>();
        list.add("f");
        list.add("g");
        list.add("h");

        customList.addAll(3, list);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatPutAllThrowsIndexOutOfBoundIfSpecifiedIndexIfLessThan0() {
        List<String> list = new ArrayList<>();
        list.add("f");
        list.add("g");
        list.add("h");

        customList.addAll(-5, list);
    }

    @Test(expected = NullPointerException.class)
    public void testThatPutAllThrowsNPEIfArgumentIsNull() {
        customList.addAll(null);
    }

    @Test
    public void testThatAddAllWithIndexReturnsTrueOnListChanging() {
        List<String> list = new ArrayList<>();
        list.add("f");
        list.add("g");
        list.add("h");

        assertThat(customList.addAll(0, list), is(true));
    }

    @Test
    public void testThatAddAllWithIndexReturnsFalseOnListChanging() {
        List<String> list = new ArrayList<>();

        assertThat(customList.addAll(0, list), is(false));
    }

    @Test
    public void testThatContainsAllWorksProperly() {
        addValues();

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        assertThat(customList.containsAll(list), is(true));

        list.add("x");
        assertThat(customList.containsAll(list), is(false));
    }

    @Test
    public void testThatRemoveAllWorksProperly() {
        addValues();

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        customList.removeAll(list);

        assertThat(customList.contains("d"), is(true));
        assertThat(customList.contains("e"), is(true));

        assertThat(customList.containsAll(list), is(false));
    }

    @Test
    public void testThatRemoveAllReturnsTrueIfListIsChanged() {
        addValues();

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        assertThat(customList.removeAll(list), is(true));
    }

    @Test
    public void testThatRemoveAllReturnsFalseIfListIsNotChanged() {
        addValues();

        List<String> list = new ArrayList<>();
        list.add("x");
        list.add("y");
        list.add("z");

        assertThat(customList.removeAll(list), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void testThatRemoveAllThrowsNPEIfArgumentIsNull() {
        customList.removeAll(null);
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