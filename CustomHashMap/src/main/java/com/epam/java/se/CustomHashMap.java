package com.epam.java.se;

import java.util.*;

public class CustomHashMap<K, V> implements Map<K, V> {
    private int CAPACITY = 16;
    private CustomEntry<K, V>[] buckets = new CustomEntry[CAPACITY];
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
        int numberOfBucket = key.hashCode() % CAPACITY;
        CustomEntry<K, V> currentEntry = buckets[numberOfBucket];

        while (currentEntry != null) {
            if (currentEntry.key.equals(key)) {
                return true;
            }
            currentEntry = currentEntry.next;
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Objects.requireNonNull(value);

        for (int i = 0; i < CAPACITY; i++) {
            CustomEntry currentEntry = buckets[i];
            while (currentEntry != null) {
                if (currentEntry.value.equals(value)) {
                    return true;
                }
                currentEntry = currentEntry.next;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);

        int numberOfBucket = key.hashCode() % CAPACITY;

        if (buckets[numberOfBucket] == null) {
            buckets[numberOfBucket] = new CustomEntry<>(key, value);
            size += 1;
            return null;
        } else {
            CustomEntry currentEntry = buckets[numberOfBucket];
            while (currentEntry != null) {
                if (currentEntry.key.equals(key)) {
                    break;
                }
                currentEntry = currentEntry.next;
            }

            if (currentEntry == null) {
                V previousValue = buckets[numberOfBucket].value;
                CustomEntry newEntry = new CustomEntry<>(key, value);
                newEntry.next = buckets[numberOfBucket];
                buckets[numberOfBucket] = newEntry;
                size += 1;
                return previousValue;
            } else {
                V previousValue = (V) currentEntry.value;
                currentEntry.value = value;
                return previousValue;
            }
        }
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {
        buckets = new CustomEntry[CAPACITY];
        size = 0;
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    private class CustomEntry<K, V> implements Iterator<CustomEntry<K, V>> {
        private final K key;
        private V value;
        private CustomEntry<K, V> next = null;

        public CustomEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public CustomEntry<K, V> next() {
            return null;
        }
    }
}
