package ru.spbau.eshcherbin.java.homework.hw1.task1;

public class LinkedList {
    private static class ListItem {
        private ListItem next;
        public final HashTable.Entry data;

        public ListItem(ListItem next, HashTable.Entry data) {
            this.next = next;
            this.data = data;
        }

        public ListItem(HashTable.Entry data) {
            this(null, data);
        }
    }

    private ListItem head;

    public boolean contains(String key) {
        ListItem item = head;
        while (item != null) {
            if (item.data.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public String get(String key) {
        ListItem item = head;
        while (item != null) {
            if (item.data.key.equals(key)) {
                return item.data.getValue();
            }
        }
        return null;
    }

    public String put(HashTable.Entry entry) {
        ListItem currentItem = head;
        while (currentItem != null) {
            if (currentItem.data.key.equals(entry.key)) {
                String oldValue = currentItem.data.getValue();
                currentItem.data.setValue(entry.getValue());
                return oldValue;
            }
        }
        head = new ListItem(head, entry);
        return null;
    }

    public String remove(String key) {
        ListItem dummyItem = new ListItem(head, null),
                 currentItem = dummyItem;
        while (currentItem.next != null) {
            if (currentItem.next.data.key.equals(key)) {
                String deletedValue = currentItem.next.data.getValue();
                currentItem.next = currentItem.next.next;
                head = dummyItem.next;
                return deletedValue;
            }
            currentItem = currentItem.next;
        }
        return null;
    }

    public HashTable.Entry popHead() {
        if (head == null) {
            return null;
        }
        HashTable.Entry result = head.data;
        head = head.next;
        return result;
    }

    public void clear() {
        head = null;
    }

    public boolean empty() {
        return head == null;
    }
}
