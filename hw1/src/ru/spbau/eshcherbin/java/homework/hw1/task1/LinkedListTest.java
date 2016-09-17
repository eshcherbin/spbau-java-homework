package ru.spbau.eshcherbin.java.homework.hw1.task1;

import org.junit.Before;

import static org.junit.Assert.*;

public class LinkedListTest {
    private LinkedList linkedList = null;

    @Before
    public void setUp() throws Exception {
        linkedList = new LinkedList();
    }

    @org.junit.Test
    public void contains() throws Exception {
        linkedList.put(new KeyValuePair("testKey", "testValue"));
        linkedList.put(new KeyValuePair("anotherTestKey", "anotherTestValue"));
        assertTrue(linkedList.contains("testKey"));
        assertTrue(linkedList.contains("anotherTestKey"));
        assertFalse(linkedList.contains("wrongTestKey"));
        assertFalse(linkedList.contains(null));
    }

    @org.junit.Test
    public void get() throws Exception {
        linkedList.put(new KeyValuePair("testKey", "testValue"));
        linkedList.put(new KeyValuePair("anotherTestKey", "anotherTestValue"));
        assertEquals(linkedList.get("testKey"), "testValue");
        assertEquals(linkedList.get("anotherTestKey"), "anotherTestValue");
        assertEquals(linkedList.get("wrongTestKey"), null);
    }

    @org.junit.Test
    public void put() throws Exception {
        assertEquals(linkedList.put(new KeyValuePair("testKey", "testValue")), null);
        assertEquals(linkedList.put(new KeyValuePair("anotherTestKey", "anotherTestValue")), null);
        assertEquals(linkedList.put(new KeyValuePair("testKey", "newTestValue")), "testValue");
    }

    @org.junit.Test
    public void remove() throws Exception {
        assertTrue(true);
        linkedList.put(new KeyValuePair("testKey", "testValue"));
        linkedList.put(new KeyValuePair("anotherTestKey", "anotherTestValue"));
        assertEquals(linkedList.remove("testKey"), "testValue");
        assertEquals(linkedList.remove("wrongTestKey"), null);
        assertEquals(linkedList.remove("anotherTestKey"), "anotherTestValue");
        assertEquals(linkedList.remove("anotherTestKey"), null);
    }

    @org.junit.Test
    public void popHead() throws Exception {
        linkedList.put(new KeyValuePair("testKey", "testValue"));
        linkedList.put(new KeyValuePair("anotherTestKey", "anotherTestValue"));
        assertEquals(linkedList.popHead(), new KeyValuePair("anotherTestKey", "anotherTestValue"));
        assertEquals(linkedList.popHead(), new KeyValuePair("testKey", "testValue"));
        assertEquals(linkedList.popHead(), null);
    }

    @org.junit.Test
    public void clear() throws Exception {
        linkedList.put(new KeyValuePair("testKey", "testValue"));
        linkedList.put(new KeyValuePair("anotherTestKey", "anotherTestValue"));
        linkedList.clear();
        assertFalse(linkedList.contains("testKey"));
        assertFalse(linkedList.contains("anotherTestKey"));
    }

    @org.junit.Test
    public void empty() throws Exception {
        assertTrue(linkedList.empty());
        linkedList.put(new KeyValuePair("testKey", "testValue"));
        linkedList.put(new KeyValuePair("anotherTestKey", "anotherTestValue"));
        assertFalse(linkedList.empty());
        linkedList.remove("testKey");
        assertFalse(linkedList.empty());
        linkedList.remove("anotherTestKey");
        assertTrue(linkedList.empty());
    }
}