package ru.spbau.eshcherbin.homework.hw5;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CollectionsTest {

    private List<Integer> integers123 = null;

    @Before
    public void setUp() throws Exception {
        integers123 = Arrays.asList(1, 2, 3);
    }

    @Test
    public void mapTest() throws Exception {
        List<Integer> doubled123 = Collections.map(a -> 2 * a, integers123);
        assertEquals(integers123.size(), doubled123.size());
        Iterator<Integer> integers123Iterator = integers123.iterator();
        for (Integer doubled : doubled123) {
            assertEquals(Integer.valueOf(2 * integers123Iterator.next()), doubled);
        }
    }

    @Test
    public void filterTest() throws Exception {
        List<Integer> filtered = Collections.filter(a -> a % 2 == 1, integers123);
        assertEquals(2, filtered.size());
        Iterator<Integer> odd123Iterator = Arrays.asList(1, 3).iterator();
        for (Integer doubled : filtered) {
            assertEquals(odd123Iterator.next(), doubled);
        }
    }

    @Test
    public void takeWhileTest() throws Exception {
        List<Integer> takenWhile = Collections.filter(a -> a < 3, integers123);
        assertEquals(2, takenWhile.size());
        Iterator<Integer> lessThan3Iterator = Arrays.asList(1, 2).iterator();
        for (Integer doubled : takenWhile) {
            assertEquals(lessThan3Iterator.next(), doubled);
        }
    }

    @Test
    public void takeUnlessTest() throws Exception {
        List<Integer> takenUnless = Collections.takeUnless(a -> a > 2, integers123);
        assertEquals(2, takenUnless.size());
        Iterator<Integer> notGreaterThen2Iterator = Arrays.asList(1, 2).iterator();
        for (Integer doubled : takenUnless) {
            assertEquals(notGreaterThen2Iterator.next(), doubled);
        }
    }

    @Test
    public void foldrTest() throws Exception {
        Integer poweredRight = Collections.foldr((a, b) -> 2 * a + b, 0, integers123);
        assertEquals(Integer.valueOf(12), poweredRight);
    }

    @Test
    public void foldlTest() throws Exception {
        Integer poweredLeft = Collections.foldl((a, b) -> 2 * a + b, 0, integers123);
        assertEquals(Integer.valueOf(11), poweredLeft);
    }
}