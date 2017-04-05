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
        return new CustomIterator();
    }

    @Override
    public E[] toArray() {
        E[] result = (E[]) new Object[size];
        System.arraycopy(data, 0, result, 0, size);
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
        c.forEach(
                element -> add((E) element)
        );
        return c.size() != 0;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        for (Object o : c) {
            add(index, o);
            index += 1;
        }
        return c.size() != 0;
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
        return new CustomListIterator(-1);
    }

    @Override
    public ListIterator listIterator(int index) {
        return new CustomListIterator(index);
    }

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

    @Override
    public boolean removeAll(Collection c) {
        int startSize = size;
        c.forEach(
                element -> remove(element)
        );

        return startSize != size;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

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

        private int index;

        public CustomListIterator(int index) {
            super();
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index < size - 1;
        }

        @Override
        public E next() {
            if (index > size) {
                throw new NoSuchElementException();
            }
            return (E) data[++index];
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public E previous() {
            if (index < 1) {
                throw new NoSuchElementException();
            }
            return (E) data[--index];
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }
}