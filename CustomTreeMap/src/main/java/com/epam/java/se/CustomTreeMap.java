package com.epam.java.se;

import java.util.*;

/**
 * Custom implementation of TreeMap.
 * Stores key-value pairs as <code>Node</code>.
 * Uses BinaryTree to store Nodes.
 * This Map does not permit storage {@code null} keys and {@code null} values.
 *
 * @param <K> type of keys
 * @param <V> type of mapped values
 * @author Valeriy Burmistrov
 */
public class CustomTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node<K, V> root;
    private V previousValue;
    private int size;

    /**
     * @return number of mappings in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if this map does not contain any mappings, otherwise return false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks that map contains mapping for specified key
     *
     * @param key key to find in map
     * @return true if map contain mapping for specified key
     * @throws NullPointerException if key is {@code null}
     */
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


    /**
     * Check that map contains key or keys, mapped to specified value
     *
     * @param value value to check presence in map
     * @return true if map contains mapping or mappings to specified value
     * @throws NullPointerException if value is {@code null}
     */
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

    /**
     * Returns value to which specified key is mapped, or {@code null} if this
     * map contains no mappings for this key.
     *
     * @param key key associated value needed to be returned
     * @return value to which specified key is mapped, or {@code null} if this
     * map contains no mappings for specified key.
     * @throws NullPointerException if specified key is {@code null}
     */
    @Override
    public V get(Object key) {
        Objects.requireNonNull(key);

        if (root != null) {
            Node<K, V> neededNode = find(root, (K) key);
            return neededNode != null ? neededNode.value : null;
        }

        return null;
    }

    /**
     * Associates the specified key with the specified value. Replaces value, if this map
     * contained a mapping for specified key previously.
     * @param key key which needed to be mapped to specified value
     * @param value value to be mapping for specified key
     * @return previous value mapped to specified key, if this map contained a mapping previously,
     * otherwise returns {@code null}
     * @throws NullPointerException if specified key is {@code null} or value is {@code null}
     */
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

    /**
     * Removes mapping for specified key from this map if map contained it.
     *
     * @param key key whose mapping needed to be removed
     * @return previous value, mapped to specified key, or null if there was no mapping
     * @throws NullPointerException if specified key if {@code null}
     */
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

    /**
     * Copies all entries from specified map to this map.
     * @param m map of entries to be copied in this map
     * @throws NullPointerException if specified map is {@code null}
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.entrySet().forEach(
                entry -> CustomTreeMap.this.put(entry.getKey(), entry.getValue())
        );
    }

    /**
     * Removes all the mappings from this map
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns set of keys, stored in this map. Set does not support adding operation.
     * Set supports remove and clear operations, that affect the map.
     * Supports Iterator
     *
     * @return set of keys, stored in this map
     */
    @Override
    public Set<K> keySet() {
        return new KeySet();
    }

    /**
     * Returns collection of values, stored in this map. Collection does not support adding operation.
     * Collection supports remove and clear operations, that affect the map.
     * Supports Iterator
     *
     * @return Collection of values, stored in this map
     */
    @Override
    public Collection<V> values() {
        return new Values();
    }

    /**
     * Returns set of entries, stored in this map. Set does not support adding operation.
     * Set supports remove and clear operations, that affect the map.
     * Supports Iterator
     *
     * @return set of entries, stored in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    /**
     * Custom entry to store key-value mappings
     *
     * @param <K> type of key
     * @param <V> type of value
     */
    private class Node<K extends Comparable<K>, V> implements Entry<K, V> {
        private final K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V previousValue = this.value;
            this.value = value;
            return previousValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?, ?> node = (Node<?, ?>) o;

            if (key != null ? !key.equals(node.key) : node.key != null) return false;
            return value != null ? value.equals(node.value) : node.value == null;
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
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

    private class EntrySet extends AbstractSet<Entry<K, V>> {
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntrySetIterator();
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean contains(Object o) {
            Objects.requireNonNull(o);

            if (o instanceof Node) {
                Node<K, V> entryToCheck = (Node<K, V>) o;
                K entryToCheckKey = entryToCheck.key;

                Node<K, V> entryWithThisKeyFromMap = new Node<>(entryToCheckKey, CustomTreeMap.this.get(entryToCheckKey));

                return entryToCheck.equals(entryWithThisKeyFromMap);
            }

            return false;
        }

        @Override
        public boolean remove(Object o) {
            return CustomTreeMap.this.remove(((Node<K, V>) o).key) != null;
        }
    }

    private class EntrySetIterator extends CustomIterator {
        @Override
        public Object next() {
            index += 1;
            return mapEntries[index];
        }
    }
}
