package com.epam.java.se;

import java.util.*;

public class CustomLinkedList<T> implements List<T> {

    private CustomNode<T> head = new CustomNode<>(null);
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return !head.hasNext();
    }

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

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
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
                return getCustomNode(index++).value;
            }
        };
    }

    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = getCustomNode(i).value;
        }
        return result;
    }

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

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(
                element -> add((T) element)
        );
        return c.size() != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for (Object o : c) {
            add(index, (T) o);
            index += 1;
        }
        return c.size() != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int startSize = size;
        c.forEach(
                element -> remove(element)
        );

        return startSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = new CustomNode<>(null);
        size = 0;
    }

    @Override
    public T get(int index) {
        checkBounds(index);

        return getCustomNode(index).value;
    }

    @Override
    public T set(int index, T element) {
        checkBounds(index);

        CustomNode<T> currentNode = getCustomNode(index);
        T oldValue = currentNode.value;
        currentNode.value = element;
        return oldValue;
    }

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

    @Override
    public T remove(int index) {
        checkBounds(index);

        CustomNode<T> current = getCustomNode(index - 1);
        size -= 1;
        if (size == 0) {
            T value = head.value;
            clear();
            return value;
        }
        T value = current.value;
        current.next = current.next.next;
        return value;
    }

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

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
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
}
