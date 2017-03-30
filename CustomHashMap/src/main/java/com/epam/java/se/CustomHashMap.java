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
        CustomEntry<K, V> entry = findEntryWithTheSameKey((K) key);

        return entry != null;
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
        Objects.requireNonNull(key);

        CustomEntry<K, V> entry = findEntryWithTheSameKey((K) key);

        return entry == null ? null : entry.value;
    }

    private int getNumberOfBucket(Object key) {
        return Math.abs(key.hashCode()) % CAPACITY;
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

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        buckets = new CustomEntry[CAPACITY];
        size = 0;
    }

    @Override
    public Set keySet() {
        return new KeySet();
    }

    @Override
    public Collection values() {
        return new Values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new EntrySet();
    }

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
