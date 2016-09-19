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
        assertEquals(0, hashTable.size());
        hashTable.put("testKey", "testValue");
        assertEquals(1, hashTable.size());
        hashTable.put("anotherTestKey", "anotherTestValue");
        assertEquals(2, hashTable.size());
        hashTable.remove("testKey");
        assertEquals(1, hashTable.size());
        hashTable.remove("testKey");
        assertEquals(1, hashTable.size());
        hashTable.remove("anotherTestKey");
        assertEquals(0, hashTable.size());

        for (int i = 0; i < 100; i++) {
            String keyValue = (new Integer(i)).toString();
            hashTable.put(keyValue, keyValue);
            assertEquals(i + 1, hashTable.size());
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
        assertEquals("testValue", hashTable.get("testKey"));
        assertEquals("anotherTestValue", hashTable.get("anotherTestKey"));
        assertEquals(null, hashTable.get("wrongTestKey"));

        for (int i = 0; i < 100; i++) {
            String keyValue = (new Integer(i)).toString();
            hashTable.put(keyValue, keyValue);
            assertEquals(keyValue, hashTable.get(keyValue));
        }
    }

    @Test
    public void put() throws Exception {
        assertEquals(null, hashTable.put("testKey", "testValue"));
        assertEquals(null, hashTable.put("anotherTestKey", "anotherTestValue"));
        assertEquals("testValue", hashTable.put("testKey", "newTestValue"));

        for (int i = 0; i < 100; i++) {
            String keyValue = (new Integer(i)).toString();
            assertEquals(null, hashTable.put(keyValue, keyValue));
            assertEquals(keyValue, hashTable.put(keyValue, keyValue));
        }
    }

    @Test
    public void remove() throws Exception {
        hashTable.put("testKey", "testValue");
        hashTable.put("anotherTestKey", "anotherTestValue");
        assertEquals("testValue", hashTable.remove("testKey"));
        assertEquals(null, hashTable.remove("wrongTestKey"));
        assertEquals("anotherTestValue", hashTable.remove("anotherTestKey"));
        assertEquals(null, hashTable.remove("anotherTestKey"));

        for (int i = 0; i < 100; i++) {
            String keyValue = (new Integer(i)).toString();
            hashTable.put(keyValue, keyValue);
            assertEquals(keyValue, hashTable.remove(keyValue));
            assertEquals(null, hashTable.remove(keyValue));
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