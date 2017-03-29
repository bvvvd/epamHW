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
        int numberOfBucket = getNumberOfBucket(key);

        CustomEntry<K, V> entry = findEntryWithTheSameKey((K) key, numberOfBucket);

        return entry != null;
    }

    @Override
    public boolean containsValue(Object value) {
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
        Objects.requireNonNull(key);

        int numberOfBucket = getNumberOfBucket(key);

        CustomEntry currentEntry = buckets[numberOfBucket];

        while (currentEntry != null) {
            if (currentEntry.key.equals(key)) {
                return (V) currentEntry.value;
            }
            currentEntry = currentEntry.next;
        }

        return null;
    }

    private int getNumberOfBucket(Object key) {
        return key.hashCode() % CAPACITY;
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);

        int numberOfBucket = getNumberOfBucket(key);

        if (buckets[numberOfBucket] == null) {
            buckets[numberOfBucket] = new CustomEntry<>(key, value);
            size += 1;
        } else {

            CustomEntry currentEntry = findEntryWithTheSameKey(key, numberOfBucket);

            if (currentEntry == null) {
                CustomEntry<K, V> newEntry = new CustomEntry<>(key, value);
                newEntry.next = buckets[numberOfBucket];
                buckets[numberOfBucket] = newEntry;
                size += 1;
            } else {
                V previousValue = (V) currentEntry.value;
                currentEntry.value = value;
                return previousValue;
            }
        }

        return null;
    }

    private CustomEntry findEntryWithTheSameKey(K key, int numberOfBucket) {
        CustomEntry entry = buckets[numberOfBucket];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry;
            }
            entry = entry.next;
        }

        return null;
    }

    @Override
    public V remove(Object key) {
        int numberOfBucket = getNumberOfBucket(key);
        CustomEntry currentEntry = buckets[numberOfBucket];

        while (currentEntry != null) {
            if (currentEntry.key.equals(key)) {
                CustomEntry accessoryEntry = buckets[numberOfBucket];

                if (accessoryEntry == currentEntry) {
                    buckets[numberOfBucket] = currentEntry.next;
                    size -= 1;
                    return (V) currentEntry.value;
                }

                while (accessoryEntry.next != currentEntry) {
                    accessoryEntry = accessoryEntry.next;
                }
                accessoryEntry.next = currentEntry.next;
                size -= 1;
                return (V) currentEntry.value;
            }
            currentEntry = currentEntry.next;
        }
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
        Set result = new HashSet();
        for (int i = 0; i < CAPACITY; i++) {
            CustomEntry currentEntry = buckets[i];
            while (currentEntry != null) {
                result.add(currentEntry.key);
                currentEntry = currentEntry.next;
            }
        }
        return result;
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
