package ru.spbau.eshcherbin.java.homework.hw1.task1;

/**
 * Implementation of LinkedList data structure
 * Stores KeyValuePairs in a row and can search a value by given key with worst-case linear time complexity
 */
public class LinkedList {
    /**
     * An auxiliary class that represents a node of the list
     */
    private static class ListItem {
        private ListItem next;
        private KeyValuePair data;

        public ListItem(ListItem next, KeyValuePair data) {
            this.next = next;
            this.data = data;
        }

        public ListItem(KeyValuePair data) {
            this(null, data);
        }
    }

    /**
     * First node of the list
     */
    private ListItem head;

    public LinkedList() {
        head = null;
    }

    /**
     * @return Whether a value with given key is stored
     */
    public boolean contains(String key) {
        ListItem currentItem = head;
        while (currentItem != null) {
            if (currentItem.data.getKey().equals(key)) {
                return true;
            }
            currentItem = currentItem.next;
        }
        return false;
    }

    /**
     * Access to value by key with worst-case linear time complexity
     * @return The value that corresponds to the given key if there is any, null otherwise
     */
    public String get(String key) {
        ListItem currentItem = head;
        while (currentItem != null) {
            if (currentItem.data.getKey().equals(key)) {
                return currentItem.data.getValue();
            }
            currentItem = currentItem.next;
        }
        return null;
    }

    /**
     * Stores a value with given key
     * @return Old value with the key if there is any, null otherwise
     */
    public String put(KeyValuePair keyValuePair) {
        ListItem currentItem = head;
        while (currentItem != null) {
            if (currentItem.data.getKey().equals(keyValuePair.getKey())) {
                String oldValue = currentItem.data.getValue();
                currentItem.data.setValue(keyValuePair.getValue());
                return oldValue;
            }
            currentItem = currentItem.next;
        }
        head = new ListItem(head, keyValuePair);
        return null;
    }

    /**
     * Removes the value with given key
     * @return The deleted value if there is any, null otherwise
     */
    public String remove(String key) {
        ListItem dummyItem = new ListItem(head, null);
        ListItem currentItem = dummyItem;
        while (currentItem.next != null) {
            if (currentItem.next.data.getKey().equals(key)) {
                String deletedValue = currentItem.next.data.getValue();
                currentItem.next = currentItem.next.next;
                head = dummyItem.next;
                return deletedValue;
            }
            currentItem = currentItem.next;
        }
        return null;
    }

    /**
     * Removes the head node and returns its data
     * @return Data stored in the removed node
     */
    public KeyValuePair popHead() {
        if (head == null) {
            return null;
        }
        KeyValuePair result = head.data;
        head = head.next;
        return result;
    }

    /**
     * Removes all stored values
     */
    public void clear() {
        head = null;
    }

    /**
     * @return Whether the list is empty or not
     */
    public boolean empty() {
        return head == null;
    }
}
