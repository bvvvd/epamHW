package com.epam.java.se;

import java.util.*;

public class CustomHashMap<K, V> implements Map<K, V> {
    private CustomEntry<K, V>[] buckets = new CustomEntry[16];

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        CustomEntry<K, V> bucket = buckets[0];
        if (bucket != null) {
            return bucket.key.equals(key);
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);

        if (buckets[0] == null) {
            buckets[0] = new CustomEntry<>(key, value);
            return null;
        } else {
            V previousValue = buckets[0].value;
            buckets[0] = new CustomEntry<>(key, value);
            return previousValue;
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
