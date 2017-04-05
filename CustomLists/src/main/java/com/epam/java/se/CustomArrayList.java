package com.epam.java.se;

import java.util.*;

/**
 * Custom implementation of ArrayList.
 * {@code CustomArrayList} permits to store all elements, including <tt>null</tt>
 *
 * @param <E> type of elements
 * @author Valeriy Burmistrov
 */
public class CustomArrayList<E> implements List<E> {

    /**
     * Default initial capacity
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The array in which elements are stored.
     */
    private Object[] data = new Object[DEFAULT_CAPACITY];

    /**
     * The number of element CustomArrayList contains
     */
    private int size;
    private int capacity = DEFAULT_CAPACITY;

    /**
     * Returns the size of CustomArrayList
     *
     * @return the number of element CustomArrayList contains
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
        return size == 0;
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i] == null) {
                if (o == null) {
                    return true;
                }
            } else if (data[i].equals(o)) {
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
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    /**
     * @return an array containing all of the elements in this list in
     * proper sequence
     */
    @Override
    public E[] toArray() {
        E[] result = (E[]) new Object[size];
        System.arraycopy(data, 0, result, 0, size);
        return result;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> if the element was added to list successfully
     */
    @Override
    public boolean add(E e) {
        ensureCapacity();

        data[size++] = e;
        return false;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            capacity = (data.length * 3) / 2 + 1;
            data = Arrays.copyOf(data, capacity);
        }
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
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                int length = data.length - i;
                System.arraycopy(data, i + 1, data, i, length - 1);
                size -= 1;
                return true;
            }
        }

        return false;
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
    public boolean addAll(Collection c) {
        c.forEach(
                element -> add((E) element)
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
    public boolean addAll(int index, Collection c) {
        for (Object o : c) {
            add(index, o);
            index += 1;
        }
        return c.size() != 0;
    }

    /**
     * Removes all of the elements from this list.  The list will
     * be empty after this call returns.
     */
    @Override
    public void clear() {
        data = new Object[DEFAULT_CAPACITY];
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
    public E get(int index) {
        checkBounds(index);
        return (E) data[index];
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
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
    public E set(int index, E element) {
        checkBounds(index);

        E oldValue = (E) data[index];
        data[index] = element;
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
    public void add(int index, Object element) {
        checkBoundsToAdd(index);

        size += 1;
        Object[] newData = new Object[capacity];

        System.arraycopy(data, 0, newData, 0, index);
        System.arraycopy(data, index, newData, index + 1, data.length - index - 1);

        newData[index] = element;
        data = newData;
    }

    private void checkBoundsToAdd(int index) {
        if ((index < 0) || (index > size)) {
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
    public E remove(int index) {
        E current = (E) data[index];
        System.arraycopy(data, index + 1, data, index, data.length - index - 1);
        size -= 1;

        return current;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     **/
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(o)) {
                    return i;
                }
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
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    index = i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(o)) {
                    index = i;
                }
            }
        }
        return index != 0 ? index : -1;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     */
    @Override
    public ListIterator listIterator() {
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
    public ListIterator listIterator(int index) {
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
    public List subList(int fromIndex, int toIndex) {
        checkBounds(fromIndex);
        checkBounds(toIndex);
        checkFrameSize(fromIndex, toIndex);
        List<E> result = new ArrayList<E>();

        for (int i = fromIndex; i <= toIndex; i++) {
            result.add((E) data[i]);
        }

        return result;
    }

    private void checkFrameSize(int fromIndex, int toIndex) {
        if (toIndex - fromIndex < 0) {
            throw new IllegalArgumentException();
        }
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
    public boolean retainAll(Collection c) {
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
     * Removes from this list all of its elements that are contained in the
     * specified collection.
     *
     * @param c collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection c) {
        int startSize = size;
        c.forEach(
                element -> remove(element)
        );

        return startSize != size;
    }

    /**
     * Checks that this list contains elements of specified collection
     *
     * @param c collection containing elements to check containing
     * @return {@code true} if this list contains all elements of specified collection
     */
    @Override
    public boolean containsAll(Collection c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
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
        if (a.length < size) {
            return (T[]) Arrays.copyOf(data, size, a.getClass());
        }

        System.arraycopy(data, 0, a, 0, size);
        return a;
    }

    private class CustomIterator implements Iterator<E> {
        int index = 0;

        @Override
        public void remove() {
            CustomArrayList.this.remove(index);
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if (index + 1 > size) {
                throw new NoSuchElementException();
            }
            return (E) data[index++];
        }
    }

    private class CustomListIterator implements ListIterator<E> {
        private int cursor;
        private int lastPosition;

        public CustomListIterator(int index) {
            super();
            this.cursor = index;
            this.lastPosition = -1;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            if (cursor > size) {
                throw new NoSuchElementException();
            }
            lastPosition = cursor;
            return (E) data[cursor++];
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public E previous() {
            if (cursor < 1) {
                throw new NoSuchElementException();
            }
            lastPosition = cursor;
            return (E) data[--cursor];
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
            CustomArrayList.this.remove(lastPosition);
            cursor = lastPosition;
            lastPosition = -1;
        }

        @Override
        public void set(E e) {
            if (lastPosition < 0) {
                throw new IllegalStateException();
            }
            CustomArrayList.this.set(lastPosition, e);
            cursor = lastPosition;
            lastPosition = -1;
        }

        @Override
        public void add(E e) {
            if (lastPosition < 0) {
                throw new IllegalStateException();
            }
            CustomArrayList.this.add(cursor++, e);
            lastPosition = -1;
        }
    }
}