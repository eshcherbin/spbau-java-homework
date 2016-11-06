package ru.spbau.eshcherbin.homework.hw6;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MyTreeSetImplComparatorTest {

    private MyTreeSet<Integer> treeSet;

    @Before
    public void setUp() throws Exception {
        treeSet = new MyTreeSetImpl<>((a, b) -> -a.compareTo(b));
    }

    @Test
    public void addComparatorTest() throws Exception {
        assertTrue(treeSet.add(0));
        assertTrue(treeSet.add(117));
        assertTrue(treeSet.add(-225));
        assertFalse(treeSet.add(0));
        assertFalse(treeSet.add(117));
        assertFalse(treeSet.add(-225));
        assertTrue(treeSet.add(1));
    }

    @Test
    public void containsComparatorTest() throws Exception {
        treeSet.add(0);
        treeSet.add(117);
        treeSet.add(-225);
        assertTrue(treeSet.contains(0));
        assertFalse(treeSet.contains(1));
        assertTrue(treeSet.contains(117));
    }

    @Test
    public void removeComparatorTest() throws Exception {
        treeSet.add(0);
        treeSet.add(117);
        treeSet.add(-225);
        assertTrue(treeSet.contains(0));
        assertTrue(treeSet.remove(0));
        assertFalse(treeSet.contains(0));
        assertFalse(treeSet.remove(0));
    }

    @Test
    public void sizeComparatorTest() throws Exception {
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
    public void firstEmptyComparatorTest() throws Exception {
        treeSet.first();
    }

    @Test(expected = NoSuchElementException.class)
    public void lastEmptyComparatorTest() throws Exception {
        treeSet.last();
    }

    @Test
    public void firstComparatorTest() throws Exception {
        treeSet.add(0);
        treeSet.add(117);
        treeSet.add(-225);
        assertEquals(Integer.valueOf(117), treeSet.first());
        treeSet.remove(117);
        assertEquals(Integer.valueOf(0), treeSet.first());
    }

    @Test
    public void lastComparatorTest() throws Exception {
        treeSet.add(0);
        treeSet.add(117);
        treeSet.add(-225);
        assertEquals(Integer.valueOf(-225), treeSet.last());
        treeSet.remove(-225);
        assertEquals(Integer.valueOf(0), treeSet.last());
    }

    @Test
    public void lowerComparatorTest() throws Exception {
        treeSet.add(0);
        treeSet.add(-225);
        assertEquals(null, treeSet.lower(0));
        treeSet.add(117);
        assertEquals(Integer.valueOf(117), treeSet.lower(0));
        assertEquals(null, treeSet.lower(117));
        assertEquals(Integer.valueOf(-225), treeSet.lower(-1000));
        assertEquals(Integer.valueOf(0), treeSet.lower(-224));
    }

    @Test
    public void floorComparatorTest() throws Exception {
        treeSet.add(0);
        treeSet.add(-225);
        assertEquals(null, treeSet.floor(1));
        treeSet.add(117);
        assertEquals(Integer.valueOf(0), treeSet.floor(0));
        assertEquals(Integer.valueOf(117), treeSet.floor(117));
        assertEquals(Integer.valueOf(-225), treeSet.floor(-225));
        assertEquals(Integer.valueOf(-225), treeSet.floor(-1000));
        assertEquals(null, treeSet.floor(1000));
        assertEquals(Integer.valueOf(0), treeSet.floor(-224));
    }

    @Test
    public void ceilingComparatorTest() throws Exception {
        treeSet.add(0);
        treeSet.add(117);
        assertEquals(null, treeSet.ceiling(-1));
        treeSet.add(-225);
        assertEquals(Integer.valueOf(0), treeSet.ceiling(0));
        assertEquals(Integer.valueOf(-225), treeSet.ceiling(-225));
        assertEquals(Integer.valueOf(117), treeSet.ceiling(117));
        assertEquals(Integer.valueOf(117), treeSet.ceiling(1000));
        assertEquals(null, treeSet.ceiling(-1000));
        assertEquals(Integer.valueOf(0), treeSet.ceiling(116));
    }

    @Test
    public void higherComparatorTest() throws Exception {
        treeSet.add(0);
        treeSet.add(117);
        assertEquals(null, treeSet.higher(0));
        treeSet.add(-225);
        assertEquals(Integer.valueOf(-225), treeSet.higher(0));
        assertEquals(null, treeSet.higher(-225));
        assertEquals(Integer.valueOf(117), treeSet.higher(1000));
        assertEquals(Integer.valueOf(0), treeSet.higher(116));
    }

    @Test
    public void iteratorComparatorTest() throws Exception {
        treeSet.add(0);
        treeSet.add(-225);
        treeSet.add(117);
        treeSet.add(-1);
        treeSet.add(-1000);
        Iterator<Integer> iterator = treeSet.iterator();
        for (Integer integer : Arrays.asList(117, 0, -1, -225, -1000)) {
            assertTrue(iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorComparatorFailTest() throws Exception {
        Iterator<Integer> iterator = treeSet.iterator();
        iterator.next();
    }

    @Test
    public void descendingIteratorComparatorTest() throws Exception {
        treeSet.add(0);
        treeSet.add(-225);
        treeSet.add(117);
        treeSet.add(-1);
        treeSet.add(-1000);
        Iterator<Integer> iterator = treeSet.descendingIterator();
        for (Integer integer : Arrays.asList(-1000, -225, -1, 0, 117)) {
            assertTrue(iterator.hasNext());
            assertEquals(integer, iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void descendingIteratorFailComparatorTest() throws Exception {
        Iterator<Integer> iterator = treeSet.descendingIterator();
        iterator.next();
    }

    @Test
    public void descendingSetComparatorTest() throws Exception {
        treeSet.add(0);
        treeSet.add(-225);
        treeSet.add(117);
        treeSet.add(-1);
        treeSet.add(-1000);
        MyTreeSet<Integer> descendingSet = treeSet.descendingSet();
        assertEquals(5, descendingSet.size());
        assertEquals(Integer.valueOf(-1000), descendingSet.first());
        assertEquals(Integer.valueOf(117), descendingSet.last());
        assertEquals(Integer.valueOf(0), descendingSet.lower(117));
        assertEquals(Integer.valueOf(-225), descendingSet.floor(-2));
        assertEquals(Integer.valueOf(117), descendingSet.higher(0));
        assertEquals(Integer.valueOf(-1), descendingSet.ceiling(-1));
    }
}