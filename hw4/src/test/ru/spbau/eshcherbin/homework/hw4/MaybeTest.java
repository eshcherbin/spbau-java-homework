package ru.spbau.eshcherbin.homework.hw4;

import org.junit.Test;
import static org.junit.Assert.*;

public class MaybeTest {
    @Test
    public void getTest() throws Exception {
        Maybe<Integer> maybeInteger = Maybe.just(117);
        assertEquals(new Integer(117), maybeInteger.get());
        assertNotEquals(new Integer(42), maybeInteger.get());
    }

    @Test(expected = MaybeDataIsNullException.class)
    public void getNullTest() throws Exception {
        Maybe<Integer> nothingInteger = Maybe.nothing();
        nothingInteger.get();
    }

    @Test
    public void isPresentTest() throws Exception {
        Maybe<Integer> justInteger = Maybe.just(117);
        Maybe<Integer> nothingInteger = Maybe.nothing();
        assertTrue(justInteger.isPresent());
        assertFalse(nothingInteger.isPresent());
    }

    @Test
    public void mapTest() throws Exception {
        Maybe<Integer> justInteger = Maybe.just(117);
        Maybe<Integer> nothingInteger = Maybe.nothing();
        assertNotEquals(Maybe.just(117), justInteger.map(n -> n.toString()));
        assertEquals(Maybe.just("117"), justInteger.map(n -> n.toString()));
        assertEquals(Maybe.nothing(), nothingInteger.map(n -> n.toString()));
    }

    @Test
    public void equalsTest() throws Exception {
        Maybe<Integer> justInteger = Maybe.just(117);
        Maybe<Integer> justAnotherInteger = Maybe.just(117);
        Maybe<Integer> justYetAnotherInteger = Maybe.just(42);
        Maybe<Integer> nothingInteger = Maybe.nothing();
        Maybe<String> nothingString = Maybe.nothing();
        assertTrue(justInteger.equals(justInteger));
        assertTrue(justInteger.equals(justAnotherInteger));
        assertFalse(justInteger.equals(justYetAnotherInteger));
        assertFalse(justInteger.equals(nothingInteger));
        assertFalse(nothingInteger.equals(Maybe.just("10")));
        assertTrue(nothingInteger.equals(nothingString));
        assertTrue(nothingInteger.equals(Maybe.nothing()));
    }
}