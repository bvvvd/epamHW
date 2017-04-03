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
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
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
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
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
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return getCustomNode(index).value;
    }

    @Override
    public T set(int index, T element) {
        if ((index <0)||(index>size)) {
            throw  new IndexOutOfBoundsException();
        }

        CustomNode<T> currentNode = getCustomNode(index);
        T oldValue = currentNode.value;
        currentNode.value = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        if ((index > size) || (index < 0)) {
            throw new IndexOutOfBoundsException();
        }

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

    @Override
    public T remove(int index) {
        CustomNode<T> current = getCustomNode(index - 1);
        size -= 1;
        T value = current.value;
        current.next = current.next.next;
        return value;
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
