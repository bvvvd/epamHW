package com.epam.java.se;

import java.util.*;

/**
 * Custom implementation of HashMap.
 * Stores key-value pairs as <code>CustomEntry</code>.
 * Number of buckets is <code>CAPACITY</code>.
 * This Map does not permit storage {@code null} keys and {@code null} values.
 *
 * @param <K> type of keys
 * @param <V> type of mapped values
 * @author Valeriy Burmistrov
 */
public class CustomHashMap<K, V> implements Map<K, V> {
    private int CAPACITY = 16;
    private CustomEntry<K, V>[] buckets = new CustomEntry[CAPACITY];
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
        CustomEntry<K, V> entry = findEntryWithTheSameKey((K) key);

        return entry != null;
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

        CustomEntry<K, V> entry = findEntryWithTheSameKey((K) key);

        return entry == null ? null : entry.value;
    }

    private int getNumberOfBucket(Object key) {
        return Math.abs(key.hashCode()) % CAPACITY;
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

        int numberOfBucket = getNumberOfBucket(key);

        if (buckets[numberOfBucket] == null) {
            buckets[numberOfBucket] = new CustomEntry<>(key, value);
            size += 1;
        } else {

            CustomEntry currentEntry = findEntryWithTheSameKey(key);

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

    private CustomEntry findEntryWithTheSameKey(K key) {
        int numberOfBucket = getNumberOfBucket(key);

        CustomEntry entry = buckets[numberOfBucket];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry;
            }
            entry = entry.next;
        }

        return null;
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
        int numberOfBucket = getNumberOfBucket(key);

        CustomEntry<K, V> entry = findEntryWithTheSameKey((K) key);

        if (entry != null) {
            CustomEntry<K, V> accessoryEntry = buckets[numberOfBucket];

            if (accessoryEntry == entry) {
                buckets[numberOfBucket] = entry.next;
                size -= 1;
                return accessoryEntry.value;
            }

            while (accessoryEntry.next != entry) {
                accessoryEntry = accessoryEntry.next;
            }
            accessoryEntry.next = entry.next;
            size -= 1;
            return entry.value;
        }

        return null;
    }

    /**
     * Copies all entries from specified map to this map.
     * @param m map of entries to be copied in this map
     * @throws NullPointerException if specified map is {@code null}
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Removes all the mappings from this map
     */
    @Override
    public void clear() {
        buckets = new CustomEntry[CAPACITY];
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
    public Set keySet() {
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
    public Collection values() {
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
    private class CustomEntry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;
        private CustomEntry<K, V> next = null;

        public CustomEntry(K key, V value) {
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

            CustomEntry<?, ?> that = (CustomEntry<?, ?>) o;

            if (!key.equals(that.key)) return false;
            return value.equals(that.value);
        }

        @Override
        public int hashCode() {
            int result = key.hashCode();
            result = 31 * result + value.hashCode();
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
            return CustomHashMap.this.containsKey(o);
        }

        @Override
        public boolean remove(Object o) {
            return CustomHashMap.this.remove(o) != null;
        }

        @Override
        public void clear() {
            CustomHashMap.this.clear();
        }
    }

    private abstract class CustomIterator implements Iterator {
        protected CustomEntry<K, V>[] mapEntries = new CustomEntry[size];
        protected int index = 0;

        public CustomIterator() {
            fillMapEntriesArray();

            index = -1;
        }

        private void fillMapEntriesArray() {
            for (int i = 0; i < buckets.length; i++) {
                CustomEntry currentEntry = buckets[i];

                while (currentEntry != null) {
                    mapEntries[index] = currentEntry;
                    index += 1;
                    currentEntry = currentEntry.next;
                }
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
            CustomHashMap.this.remove(mapEntries[index].key);
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
            return CustomHashMap.this.containsValue(o);
        }

        @Override
        public void clear() {
            CustomHashMap.this.clear();
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
            return new EntryIterator();
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean contains(Object o) {
            Objects.requireNonNull(o);

            if (o instanceof CustomEntry) {
                CustomEntry<K, V> entryToCheck = (CustomEntry<K, V>) o;
                K entryToCheckKey = entryToCheck.key;

                CustomEntry<K, V> entryWithThisKeyFromMap = new CustomEntry<>(entryToCheckKey, CustomHashMap.this.get(entryToCheckKey));

                return entryToCheck.equals(entryWithThisKeyFromMap);
            }
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return CustomHashMap.this.remove(((CustomEntry<K, V>) o).key) != null;
        }

        @Override
        public void clear() {
            CustomHashMap.this.clear();
        }
    }

    private class EntryIterator extends CustomIterator {
        @Override
        public Object next() {
            index += 1;
            return mapEntries[index];
        }
    }
}
