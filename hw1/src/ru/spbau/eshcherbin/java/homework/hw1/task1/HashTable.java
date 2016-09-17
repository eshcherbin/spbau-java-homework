package ru.spbau.eshcherbin.java.homework.hw1.task1;

/**
 * Implementation of HashMap data structure
 * Stores String values that are accessible by corresponding keys in constant time
 */
public class HashTable {
    /**
     * Initial number of buckets
     */
    private static final int INITIAL_TABLE_SIZE = 4;

    /**
     * Number of values stored
     */
    private int size;

    /**
     * Array of buckets
     */
    private LinkedList[] table;

    public HashTable(int tableSize) {
        size = 0;
        table = new LinkedList[tableSize];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList();
        }
    }

    public HashTable() {
        this(INITIAL_TABLE_SIZE);
    }

    /**
     * @return Number of values stored
     */
    public int size() {
        return size;
    }

    /**
     * @return Whether a value with given key is stored
     */
    public boolean contains(String key) {
        if (key == null) {
            return false;
        }
        int index = getIndex(key);
        return table[index].contains(key);
    }

    /**
     * Access to value by key with expected constant time complexity
     * @return The value that corresponds to the given key if there is any, null otherwise
     */
    public String get(String key) {
        int index = getIndex(key);
        return table[index].get(key);
    }

    /**
     * Stores a value with given key
     * @return Old value with the key if there is any, null otherwise
     */
    public String put(String key, String value) {
        return put(new KeyValuePair(key, value));
    }

    private String put(KeyValuePair keyValuePair) {
        int index = getIndex(keyValuePair.getKey());
        String result = table[index].put(keyValuePair);
        if (result == null) {
            size++;
            if (size == table.length) {
                extendTable();
            }
        }
        return result;
    }

    /**
     * Removes the value with given key
     * @return The deleted value if there is any, null otherwise
     */
    public String remove(String key) {
        int index = getIndex(key);
        String result = table[index].remove(key);
        if (result != null) {
            size--;
        }
        return result;
    }

    /**
     * Removes all stored values
     */
    public void clear() {
        size = 0;
        for (LinkedList list : table) {
            list.clear();
        }
    }

    /**
     * Maps a string to its bucket
     * @return string's bucket index in current table
     */
    private int getIndex(String string) {
        int index = string.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        return index;
    }

    /**
     * Doubles the HashTable capacity
     */
    private void extendTable() {
        HashTable biggerTable = new HashTable(table.length * 2);
        for (LinkedList list : table) {
            while (!list.empty()) {
                KeyValuePair keyValuePair = list.popHead();
                biggerTable.put(keyValuePair);
            }
        }
        table = biggerTable.table;
    }
}
