package ru.spbau.eshcherbin.homework.hw6;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MyTreeSetImplTest {
    private MyTreeSet<Integer> treeSet;

    @Before
    public void setUp() throws Exception {
        treeSet = new MyTreeSetImpl<>();
    }

    @Test
    public void addAndSizeTest() throws Exception {
        assertTrue(treeSet.add(0));
        assertTrue(treeSet.add(117));
        assertTrue(treeSet.add(-225));
        assertFalse(treeSet.add(0));
        assertFalse(treeSet.add(117));
        assertFalse(treeSet.add(-225));
        assertTrue(treeSet.add(1));
    }

    @Test
    public void containsTest() throws Exception {
        treeSet.add(0);
        treeSet.add(117);
        treeSet.add(-225);
        assertTrue(treeSet.contains(0));
        assertFalse(treeSet.contains(1));
        assertTrue(treeSet.contains(117));
    }

    @Test
    public void removeTest() throws Exception {
        treeSet.add(0);
        treeSet.add(117);
        treeSet.add(-225);
        assertTrue(treeSet.contains(0));
        assertTrue(treeSet.remove(0));
        assertFalse(treeSet.contains(0));
        assertFalse(treeSet.remove(0));
    }

    @Test
    public void sizeTest() throws Exception {
        assertEquals(0, treeSet.size());
        treeSet.add(0);
        assertEquals(1, treeSet.size());
        treeSet.add(117);
        assertEquals(2, treeSet.size());
        treeSet.add(-225);
        assertEquals(3, treeSet.size());
        treeSet.remove(-225);
        assertEquals(2, treeSet.size());
        treeSet.remove(0);
        assertEquals(1, treeSet.size());
        treeSet.remove(118);
        assertEquals(1, treeSet.size());
        treeSet.remove(117);
        assertEquals(0, treeSet.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void firstEmptyTest() throws Exception {
        treeSet.first();
    }

    @Test(expected = NoSuchElementException.class)
    public void lastEmptyTest() throws Exception {
        treeSet.last();
    }

    @Test
    public void firstTest() throws Exception {
        treeSet.add(0);
        treeSet.add(117);
        treeSet.add(-225);
        assertEquals(Integer.valueOf(-225), treeSet.first());
        treeSet.remove(-225);
        assertEquals(Integer.valueOf(0), treeSet.first());
    }

    @Test
    public void lastTest() throws Exception {
        treeSet.add(0);
        treeSet.add(117);
        treeSet.add(-225);
        assertEquals(Integer.valueOf(117), treeSet.last());
        treeSet.remove(117);
        assertEquals(Integer.valueOf(0), treeSet.last());
    }

    @Test
    public void lowerTest() throws Exception {
        treeSet.add(0);
        treeSet.add(117);
        assertEquals(null, treeSet.lower(0));
        treeSet.add(-225);
        assertEquals(Integer.valueOf(-225), treeSet.lower(0));
        assertEquals(null, treeSet.lower(-225));
        assertEquals(Integer.valueOf(117), treeSet.lower(1000));
        assertEquals(Integer.valueOf(0), treeSet.lower(116));
    }

    @Test
    public void floorTest() throws Exception {
        treeSet.add(0);
        treeSet.add(117);
        assertEquals(null, treeSet.floor(-1));
        treeSet.add(-225);
        assertEquals(Integer.valueOf(0), treeSet.floor(0));
        assertEquals(Integer.valueOf(-225), treeSet.floor(-225));
        assertEquals(Integer.valueOf(117), treeSet.floor(117));
        assertEquals(Integer.valueOf(117), treeSet.floor(1000));
        assertEquals(null, treeSet.floor(-1000));
        assertEquals(Integer.valueOf(0), treeSet.floor(116));
    }

    @Test
    public void ceilingTest() throws Exception {
        treeSet.add(0);
        treeSet.add(-225);
        assertEquals(null, treeSet.ceiling(1));
        treeSet.add(117);
        assertEquals(Integer.valueOf(0), treeSet.ceiling(0));
        assertEquals(Integer.valueOf(117), treeSet.ceiling(117));
        assertEquals(Integer.valueOf(-225), treeSet.ceiling(-225));
        assertEquals(Integer.valueOf(-225), treeSet.ceiling(-1000));
        assertEquals(null, treeSet.ceiling(1000));
        assertEquals(Integer.valueOf(0), treeSet.ceiling(-224));
    }

    @Test
    public void higherTest() throws Exception {
        treeSet.add(0);
        treeSet.add(-225);
        assertEquals(null, treeSet.higher(0));
        treeSet.add(117);
        assertEquals(Integer.valueOf(117), treeSet.higher(0));
        assertEquals(null, treeSet.higher(117));
        assertEquals(Integer.valueOf(-225), treeSet.higher(-1000));
        assertEquals(Integer.valueOf(0), treeSet.higher(-224));
    }
}