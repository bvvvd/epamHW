package com.epam.java.se;

import java.util.*;

public class CustomArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data = new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null && data[i] == null) {
                return true;
            }
            if (data[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(E e) {
        if (size == data.length) {
            data = Arrays.copyOf(data, (data.length * 3) / 2 + 1);
        }

        data[size++] = e;
        return false;
    }

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

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) data[index];
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {
        size += 1;
        Object[] newData = new Object[DEFAULT_CAPACITY];
        System.arraycopy(data, 0, newData, 0, index);
        System.arraycopy(data, index, newData, index + 1, data.length - index - 1);
        newData[index] = element;
        data = newData;
    }

    @Override
    public E remove(int index) {
        E current = (E) data[index];
        System.arraycopy(data, index + 1, data, index, data.length - index - 1);
        size -= 1;
        return current;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
