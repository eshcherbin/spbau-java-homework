package ru.spbau.eshcherbin.homework.hw5;

import org.junit.Test;

import static org.junit.Assert.*;

public class PredicateTest {
    @Test
    public void orTest() throws Exception {
        Predicate<Integer> is0Modulo4 = a -> a % 4 == 0;
        Predicate<Integer> is2Modulo4 = a -> a % 4 == 2;
        Predicate<Integer> isEven = is0Modulo4.or(is2Modulo4);
        assertFalse(isEven.apply(117));
        assertTrue(isEven.apply(234));

        Predicate<Integer> isOdd = a -> a % 2 == 1;
        Predicate<Integer> isOddOrDivisionByZero = isOdd.or(a -> a / 0 == 0);
        assertTrue(isOddOrDivisionByZero.apply(117));
    }

    @Test
    public void andTest() throws Exception {
        Predicate<Integer> isNot1Modulo4 = a -> a % 4 != 1;
        Predicate<Integer> isNot3Modulo4 = a -> a % 4 != 3;
        Predicate<Integer> isEven = isNot1Modulo4.and(isNot3Modulo4);
        assertFalse(isEven.apply(117));
        assertTrue(isEven.apply(234));

        Predicate<Integer> isOdd = a -> a % 2 == 1;
        Predicate<Integer> isOddAndDivisionByZero = isOdd.and(a -> a / 0 == 0);
        assertFalse(isOddAndDivisionByZero.apply(118));
    }

    @Test
    public void notTest() throws Exception {
        Predicate<Integer> is1Modulo4 = a -> a % 4 == 1;
        Predicate<Integer> is3Modulo4 = a -> a % 4 == 3;
        Predicate<Integer> isEven = is1Modulo4.or(is3Modulo4).not();
        assertFalse(isEven.apply(117));
        assertTrue(isEven.apply(234));
    }

    @Test
    public void getAlwaysTruePredicateTest() throws Exception {
        Predicate<Integer> alwaysTrue = Predicate.getAlwaysTruePredicate();
        for (int i = 0; i < 117; i++) {
            assertTrue(alwaysTrue.apply(i));
        }
    }

    @Test
    public void getAlwaysFalsePredicateTest() throws Exception {
        Predicate<Integer> alwaysFalse = Predicate.getAlwaysFalsePredicate();
        for (int i = 0; i < 117; i++) {
            assertFalse(alwaysFalse.apply(i));
        }
    }
}