package com.epam.java.se;

import java.util.*;

/**
 * Custom implementation of LinkedList.
 * {@code CustomLinkedList} permits to store all elements, including <tt>null</tt>
 * Uses one-linked list as implementation
 *
 * @param <T> type of elements
 * @author Valeriy Burmistrov
 */
@SuppressWarnings("unchecked")
public class CustomLinkedList<T> implements List<T> {


    /**
     * The head element of list
     */
    private CustomNode<T> head = new CustomNode<>(null);
    private int size = 0;

    /**
     * Returns the size of list
     *
     * @return the number of element list contains
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return <tt>true</tt> if this list does not contain any elements
     */
    @Override
    public boolean isEmpty() {
        return !head.hasNext();
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        CustomNode<T> iterator = head;
        while (iterator.hasNext()) {
            iterator = iterator.next;
            if (o == null && iterator.value == null) {
                return true;
            }
            if (iterator.value.equals(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    /**
     * @return an array containing all of the elements in this list in
     * proper sequence
     */
    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = getCustomNode(i).value;
        }
        return result;
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element); the type of the returned
     * array is that of the specified array.  If the list fits in the
     * specified array, it is returned therein.  Otherwise, creates a new array
     * with type of the specified array and the size of this list.
     * If the list fits in the specified array with room to spare, the element in
     * the array immediately following the end of the collection is set to
     * <tt>null</tt>.
     *
     * @param a the array into which the elements of the list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of the list
     * @throws ArrayStoreException  if the runtime type of the specified array
     *                              is not a supertype of the runtime type of every element in
     *                              this list
     * @throws NullPointerException if the specified array is null
     **/
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);

        for (int i = 0; i < size; i++) {
            a[i] = (T) getCustomNode(i).value;
        }

        return a;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> if the element was added to list successfully
     */
    @Override
    public boolean add(T e) {
        CustomNode<T> iterator = head;
        while (iterator.hasNext()) {
            iterator = iterator.next;
        }
        iterator.next = new CustomNode<>(e);
        size += 1;
        return false;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        CustomNode<T> current = head.next;
        CustomNode<T> prev = head;

        while (current != null) {
            if (o.equals(current.value)) {
                prev.next = current.next;
                size -= 1;
                return true;
            }
            prev = current;
            current = current.next;
        }

        return false;
    }

    /**
     * Checks that this list contains elements of specified collection
     *
     * @param c collection containing elements to check containing
     * @return {@code true} if this list contains all elements of specified collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the
     * specified collection's Iterator.
     *
     * @param c collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(
                element -> add((T) element)
        );
        return c.size() != 0;
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified collection's iterator.
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws IndexOutOfBoundsException if the specified index is
     *          not the index of the elements of this list
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for (Object o : c) {
            add(index, (T) o);
            index += 1;
        }
        return c.size() != 0;
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection.
     *
     * @param c collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        int startSize = size;
        c.forEach(
                this::remove
        );

        return startSize != size;
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection.  In other words, removes from this list all
     * of its elements that are not contained in the specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return {@code true} if this list changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] dataCopy = toArray();
        int startSize = size;

        for (Object element : dataCopy) {
            if (!c.contains(element)) {
                remove(element);
            }
        }

        return startSize != size;
    }

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    @Override
    public void clear() {
        head = new CustomNode<>(null);
        size = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the specified index is
     *          not the index of the elements of this list
     */
    @Override
    public T get(int index) {
        checkBounds(index);

        return getCustomNode(index).value;
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the specified index is
     *          not the index of the elements of this list
     */
    @Override
    public T set(int index, T element) {
        checkBounds(index);

        CustomNode<T> currentNode = getCustomNode(index);
        T oldValue = currentNode.value;
        currentNode.value = element;
        return oldValue;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the specified index is
     *          not the index of the elements of this list
     */
    @Override
    public void add(int index, T element) {
        checkBoundsToAdd(index);

        size += 1;

        if (index == 0) {
            CustomNode<T> node = new CustomNode<>(element);
            node.next = head;
            head = node;
            return;
        }

        if (index == size) {
            add(element);
        }

        CustomNode<T> currentNode = head;
        for (int currentNodeIndex = 1; currentNodeIndex <= index; currentNodeIndex++) {
            currentNode = currentNode.next;
        }

        CustomNode<T> nodeToInsert = new CustomNode<>(element);
        nodeToInsert.next = currentNode.next;
        currentNode.next = nodeToInsert;
    }

    private void checkBoundsToAdd(int index) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkBounds(int index) {
        if ((index >= size) || (index < 0)) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the specified index is
     *          not the index of the elements of this list
     */
    @Override
    public T remove(int index) {
        checkBounds(index);

        CustomNode<T> current = getCustomNode(index);
        size -= 1;
        if (size == 0) {
            T value = head.value;
            clear();
            return value;
        }
        T value = current.value;

        CustomNode<T> previous = getCustomNode(index - 1);
        previous.next = current.next;
        return value;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     **/
    @Override
    public int indexOf(Object o) {
        int index = 0;

        if (o == null) {
            for (CustomNode<T> currentNode = head.next; currentNode != null; currentNode = currentNode.next) {
                if (currentNode.value == null) {
                    return index;
                }
                index += 1;
            }
        } else {
            for (CustomNode<T> currentNode = head.next; currentNode != null; currentNode = currentNode.next) {
                if (o.equals(currentNode.value)) {
                    return index;
                }
                index += 1;
            }
        }

        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     **/
    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        int resultIndex = 0;

        if (o == null) {
            for (CustomNode<T> currentNode = head.next; currentNode != null; currentNode = currentNode.next) {
                if (currentNode.value == null) {
                    resultIndex = index;
                }
                index += 1;
            }
        } else {
            for (CustomNode<T> currentNode = head.next; currentNode != null; currentNode = currentNode.next) {
                if (o.equals(currentNode.value)) {
                    resultIndex = index;
                }
                index += 1;
            }
        }

        return resultIndex != 0 ? resultIndex : -1;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     */
    @Override
    public ListIterator<T> listIterator() {
        return new CustomListIterator(0);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     * @param index iterator starting position
     * @throws IndexOutOfBoundsException if the specified index is not
     *          index of this list elements
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        return new CustomListIterator(index);
    }

    /**
     * Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.  (If
     * {@code fromIndex} and {@code toIndex} are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations.
     *
     * @throws IndexOutOfBoundsException if fromIndex is less than 0 or toIndex
     *          is more that size of the list
     * @throws IllegalArgumentException if toIndex is less than fromIndex
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        checkBounds(fromIndex);
        checkBounds(toIndex);
        checkFrameSize(fromIndex, toIndex);
        List<T> result = new LinkedList<T>();

        for (int i = fromIndex; i <= toIndex; i++) {
            result.add(getCustomNode(i).value);
        }

        return result;
    }

    private void checkFrameSize(int fromIndex, int toIndex) {
        if (toIndex - fromIndex < 0) {
            throw new IllegalArgumentException();
        }
    }

    private CustomNode<T> getCustomNode(int index) {
        CustomNode<T> current = head.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private class CustomNode<T> {

        private CustomNode<T> next = null;
        private T value;

        public CustomNode(T t) {
            this.value = t;
        }

        public boolean hasNext() {
            return next != null;
        }
    }

    private class CustomListIterator implements ListIterator<T> {
        private int cursor;
        private int lastPosition;

        public CustomListIterator(int index) {
            super();
            this.cursor = index;
            this.lastPosition = -1;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            if (cursor > size) {
                throw new NoSuchElementException();
            }
            lastPosition = cursor;
            return getCustomNode(cursor++).value;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public T previous() {
            if (cursor < 1) {
                throw new NoSuchElementException();
            }
            lastPosition = cursor;
            return getCustomNode(--cursor).value;
        }

        @Override
        public int nextIndex() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return cursor;
        }

        @Override
        public int previousIndex() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            return cursor - 1;
        }

        @Override
        public void remove() {
            if (lastPosition < 0) {
                throw new IllegalStateException();
            }
            CustomLinkedList.this.remove(lastPosition);
            cursor = lastPosition;
            lastPosition = -1;
        }

        @Override
        public void set(T t) {
            if (lastPosition < 0) {
                throw new IllegalStateException();
            }
            CustomLinkedList.this.set(lastPosition, t);
            cursor = lastPosition;
            lastPosition = -1;
        }

        @Override
        public void add(T t) {
            if (lastPosition < 0) {
                throw new IllegalStateException();
            }
            CustomLinkedList.this.add(cursor++, t);
            lastPosition = -1;
        }
    }

    private class CustomIterator implements Iterator<T> {
        public int index = 0;

        @Override
        public void remove() {
            CustomLinkedList.this.remove(index);
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (index + 1 > size) {
                throw new NoSuchElementException();
            }
            return getCustomNode(index++).value;
        }
    }
}
