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
        assertEquals("testValue", linkedList.get("testKey"));
        assertEquals("anotherTestValue", linkedList.get("anotherTestKey"));
        assertEquals(null, linkedList.get("wrongTestKey"));
    }

    @org.junit.Test
    public void put() throws Exception {
        assertEquals(null, linkedList.put(new KeyValuePair("testKey", "testValue")));
        assertEquals(null, linkedList.put(new KeyValuePair("anotherTestKey", "anotherTestValue")));
        assertEquals("testValue", linkedList.put(new KeyValuePair("testKey", "newTestValue")));
    }

    @org.junit.Test
    public void remove() throws Exception {
        assertTrue(true);
        linkedList.put(new KeyValuePair("testKey", "testValue"));
        linkedList.put(new KeyValuePair("anotherTestKey", "anotherTestValue"));
        assertEquals("testValue", linkedList.remove("testKey"));
        assertEquals(null, linkedList.remove("wrongTestKey"));
        assertEquals("anotherTestValue", linkedList.remove("anotherTestKey"));
        assertEquals(null, linkedList.remove("anotherTestKey"));
    }

    @org.junit.Test
    public void popHead() throws Exception {
        linkedList.put(new KeyValuePair("testKey", "testValue"));
        linkedList.put(new KeyValuePair("anotherTestKey", "anotherTestValue"));
        assertEquals(new KeyValuePair("anotherTestKey", "anotherTestValue"), linkedList.popHead());
        assertEquals(new KeyValuePair("testKey", "testValue"), linkedList.popHead());
        assertEquals(null, linkedList.popHead());
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