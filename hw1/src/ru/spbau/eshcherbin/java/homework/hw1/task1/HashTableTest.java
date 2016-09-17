package ru.spbau.eshcherbin.java.homework.hw1.task1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableTest {
    private HashTable hashTable = null;

    @Before
    public void setUp() throws Exception {
        hashTable = new HashTable();
    }

    @Test
    public void size() throws Exception {
        assertEquals(hashTable.size(), 0);
        hashTable.put("testKey", "testValue");
        assertEquals(hashTable.size(), 1);
        hashTable.put("anotherTestKey", "anotherTestValue");
        assertEquals(hashTable.size(), 2);
        hashTable.remove("testKey");
        assertEquals(hashTable.size(), 1);
        hashTable.remove("testKey");
        assertEquals(hashTable.size(), 1);
        hashTable.remove("anotherTestKey");
        assertEquals(hashTable.size(), 0);

        for (int i = 0; i < 100; i++) {
            String keyValue = (new Integer(i)).toString();
            hashTable.put(keyValue, keyValue);
            assertEquals(hashTable.size(), i + 1);
        }
    }

    @Test
    public void contains() throws Exception {
        hashTable.put("testKey", "testValue");
        hashTable.put("anotherTestKey", "anotherTestValue");
        assertTrue(hashTable.contains("testKey"));
        assertTrue(hashTable.contains("anotherTestKey"));
        assertFalse(hashTable.contains("wrongTestKey"));
        assertFalse(hashTable.contains(null));

        for (int i = 0; i < 100; i++) {
            String keyValue = (new Integer(i)).toString();
            hashTable.put(keyValue, keyValue);
            assertTrue(hashTable.contains(keyValue));
        }
    }

    @Test
    public void get() throws Exception {
        hashTable.put("testKey", "testValue");
        hashTable.put("anotherTestKey", "anotherTestValue");
        assertEquals(hashTable.get("testKey"), "testValue");
        assertEquals(hashTable.get("anotherTestKey"), "anotherTestValue");
        assertEquals(hashTable.get("wrongTestKey"), null);

        for (int i = 0; i < 100; i++) {
            String keyValue = (new Integer(i)).toString();
            hashTable.put(keyValue, keyValue);
            assertEquals(hashTable.get(keyValue), keyValue);
        }
    }

    @Test
    public void put() throws Exception {
        assertEquals(hashTable.put("testKey", "testValue"), null);
        assertEquals(hashTable.put("anotherTestKey", "anotherTestValue"), null);
        assertEquals(hashTable.put("testKey", "newTestValue"), "testValue");

        for (int i = 0; i < 100; i++) {
            String keyValue = (new Integer(i)).toString();
            assertEquals(hashTable.put(keyValue, keyValue), null);
            assertEquals(hashTable.put(keyValue, keyValue), keyValue);
        }
    }

    @Test
    public void remove() throws Exception {
        hashTable.put("testKey", "testValue");
        hashTable.put("anotherTestKey", "anotherTestValue");
        assertEquals(hashTable.remove("testKey"), "testValue");
        assertEquals(hashTable.remove("wrongTestKey"), null);
        assertEquals(hashTable.remove("anotherTestKey"), "anotherTestValue");
        assertEquals(hashTable.remove("anotherTestKey"), null);

        for (int i = 0; i < 100; i++) {
            String keyValue = (new Integer(i)).toString();
            hashTable.put(keyValue, keyValue);
            assertEquals(hashTable.remove(keyValue), keyValue);
            assertEquals(hashTable.remove(keyValue), null);
        }
    }

    @Test
    public void clear() throws Exception {
        hashTable.put("testKey", "testValue");
        hashTable.put("anotherTestKey", "anotherTestValue");
        hashTable.clear();
        assertFalse(hashTable.contains("testKey"));
        assertFalse(hashTable.contains("anotherTestKey"));

        for (int i = 0; i < 100; i++) {
            String keyValue = (new Integer(i)).toString();
            hashTable.put(keyValue, keyValue);
        }
        hashTable.clear();
        for (int i = 0; i < 100; i++) {
            String key = (new Integer(i)).toString();
            assertFalse(hashTable.contains(key));
        }
    }

}