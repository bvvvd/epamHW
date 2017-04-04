package com.epam.java.se;

import java.util.*;

public class CustomArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data = new Object[DEFAULT_CAPACITY];
    private int size;
    private int capacity = DEFAULT_CAPACITY;

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

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
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
                return (E) data[index++];
            }
        };
    }

    @Override
    public E[] toArray() {
        E[] result = (E[]) new Object[size];
        System.arraycopy(data,0,result,0,size);
        return result;
    }

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
        checkBounds(index);
        return (E) data[index];
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E set(int index, E element) {
        checkBounds(index);

        E oldValue = (E) data[index];
        data[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, Object element) {
        checkBounds(index);

        size += 1;
        Object[] newData = new Object[capacity];

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
