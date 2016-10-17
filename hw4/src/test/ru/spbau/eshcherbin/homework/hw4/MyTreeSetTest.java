package ru.spbau.eshcherbin.homework.hw4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyTreeSetTest {
    private MyTreeSet<Integer> myTreeSet;

    @Before
    public void setUp() throws Exception {
        myTreeSet = new MyTreeSet<>();
    }

    @Test
    public void addTest() throws Exception {
        assertTrue(myTreeSet.add(1));
        assertTrue(myTreeSet.add(2));
        assertTrue(myTreeSet.add(0));
        assertFalse(myTreeSet.add(1));
        assertFalse(myTreeSet.add(2));
        assertFalse(myTreeSet.add(0));
        assertTrue(myTreeSet.add(3));
    }

    @Test
    public void containsTree() throws Exception {
        myTreeSet.add(1);
        myTreeSet.add(2);
        myTreeSet.add(0);
        myTreeSet.add(3);
        assertTrue(myTreeSet.contains(1));
        assertTrue(myTreeSet.contains(2));
        assertTrue(myTreeSet.contains(3));
        assertTrue(myTreeSet.contains(0));
        assertFalse(myTreeSet.contains(-1));
    }

    @Test
    public void sizeTree() throws Exception {
        assertEquals(0, myTreeSet.size());
        myTreeSet.add(1);
        assertEquals(1, myTreeSet.size());
        assertEquals(1, myTreeSet.size());
        myTreeSet.add(2);
        assertEquals(2, myTreeSet.size());
        myTreeSet.add(0);
        assertEquals(3, myTreeSet.size());
        myTreeSet.add(0);
        assertEquals(3, myTreeSet.size());
        myTreeSet.add(3);
        assertEquals(4, myTreeSet.size());
    }
}