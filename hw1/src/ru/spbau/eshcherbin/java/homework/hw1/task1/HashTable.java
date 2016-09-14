package ru.spbau.eshcherbin.java.homework.hw1.task1;

public class HashTable {
    public static class Entry {
        public final String key;
        private String value;

        public Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private static final int INITIAL_TABLE_SIZE = 4;

    private int size;
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

    public int size() {
        return size;
    }

    public boolean contains(String key) {
        int index = getIndex(key);
        return table[index].contains(key);
    }

    public String get(String key) {
        int index = getIndex(key);
        return table[index].get(key);
    }

    public String put(String key, String value) {
        return put(new Entry(key, value));
    }

    public String put(Entry entry) {
        int index = getIndex(entry.key);
        String result = table[index].put(entry);
        if (result == null) {
            size++;
            if (size == table.length) {
                extendTable();
            }
        }
        return result;
    }

    public String remove(String key) {
        int index = getIndex(key);
        String result = table[index].remove(key);
        if (result != null) {
            size--;
        }
        return result;
    }

    public void clear() {
        size = 0;
        for (LinkedList list : table) {
            list.clear();
        }
    }

    private int getIndex(String string) {
        int index = string.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        return index;
    }

    private void extendTable() {
        HashTable biggerTable = new HashTable(table.length * 2);
        for (LinkedList list : table) {
            while (!list.empty()) {
                Entry entry = list.popHead();
                biggerTable.put(entry);
            }
        }
        table = biggerTable.table;
    }
}
