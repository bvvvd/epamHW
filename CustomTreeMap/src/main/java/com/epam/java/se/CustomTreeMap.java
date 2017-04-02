package com.epam.java.se;

import java.util.*;

public class CustomTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node<K, V> root;
    private V previousValue;
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
    public boolean containsKey(Object key) {
        Objects.requireNonNull(key);

        return find(root, (K) key) != null;
    }

    private Node<K, V> find(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) == 0) {
            return node;
        } else if (node.key.compareTo(key) < 0) {
            return find(node.right, key);
        } else {
            return find(node.left, key);
        }
    }


    @Override
    public boolean containsValue(Object value) {
        Objects.requireNonNull(value);

        return findValue(root, (V) value) != null;
    }

    private Node<K, V> findValue(Node<K, V> node, V valueToFind) {
        if (node == null) {
            return null;
        }

        if (node.value.equals(valueToFind)) {
            return node;
        }

        if (findValue(node.left, valueToFind) != null) {
            return node;
        } else if (findValue(node.right, valueToFind) != null) {
            return node;
        }

        return null;
    }

    @Override
    public V get(Object key) {
        Objects.requireNonNull(key);

        if (root != null) {
            Node<K, V> neededNode = find(root, (K) key);
            return neededNode != null ? neededNode.value : null;
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        root = put(root, key, value);

        return previousValue;
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            size += 1;
            return new Node<>(key, value);
        }

        if (node.key.equals(key)) {
            previousValue = node.value;
            node.value = value;
        } else if (node.key.compareTo(key) < 0) {
            node.right = put(node.right, key, value);
        } else {
            node.left = put(node.left, key, value);
        }

        return node;
    }

    @Override
    public V remove(Object key) {
        Objects.requireNonNull(key);
        Node<K, V> nodeToRemove = find(root, (K) key);
        if (nodeToRemove != null) {
            size -= 1;
            root = remove(root, (K) key);
            return nodeToRemove.value;
        }
        return null;
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
        } else if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }

            Node<K, V> accessoryNode = node;
            node = findMin(accessoryNode.right);
            node.right = removeMin(accessoryNode.right);
            node.left = accessoryNode.left;
        }
        return node;
    }

    private Node<K, V> findMin(Node<K, V> node) {
        if (node.left == null) {
            return node;
        } else {
            return findMin(node.left);
        }
    }

    private Node<K, V> removeMin(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node.right;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return new KeySet();
    }

    @Override
    public Collection<V> values() {
        return new Values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private class Node<K extends Comparable<K>, V> {
        private final K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private class KeySet extends AbstractSet<K> {
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean contains(Object o) {
            return CustomTreeMap.this.containsKey(o);
        }

        @Override
        public boolean remove(Object o) {
            return CustomTreeMap.this.remove(o) != null;
        }

        @Override
        public void clear() {
            CustomTreeMap.this.clear();
        }
    }

    private abstract class CustomIterator implements Iterator {
        protected Node<K, V>[] mapEntries = new Node[size];
        protected int index = 0;

        public CustomIterator() {
            fillMapEntriesArray(root);

            index = -1;
        }

        private void fillMapEntriesArray(Node<K, V> node) {
            if (node == null) {
                return;
            }

            mapEntries[index] = node;
            index += 1;

            if (node.left != null) {
                fillMapEntriesArray(node.left);
            }

            if (node.right != null) {
                fillMapEntriesArray(node.right);
            }

        }

        @Override
        public boolean hasNext() {
            return index < mapEntries.length - 1;
        }

        @Override
        public abstract Object next();

        @Override
        public void remove() {
            CustomTreeMap.this.remove(mapEntries[index].key);
        }
    }

    private class KeyIterator extends CustomIterator {
        @Override
        public Object next() {
            index += 1;
            return mapEntries[index].key;
        }
    }

    private class Values extends AbstractCollection<V> {
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean contains(Object o) {
            return CustomTreeMap.this.containsValue(o);
        }

        @Override
        public void clear() {
            CustomTreeMap.this.clear();
        }
    }

    private class ValueIterator extends CustomIterator {
        @Override
        public Object next() {
            index += 1;
            return mapEntries[index].value;
        }
    }
}
