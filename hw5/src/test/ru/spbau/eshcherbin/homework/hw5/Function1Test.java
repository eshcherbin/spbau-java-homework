package ru.spbau.eshcherbin.homework.hw5;

import org.junit.Test;

import static org.junit.Assert.*;

public class Function1Test {
    @Test
    public void applyTest() throws Exception {
        Function1<Integer, Integer> doubleInteger = a -> 2 * a;
        assertEquals(Integer.valueOf(0), doubleInteger.apply(0));
        assertEquals(Integer.valueOf(234), doubleInteger.apply(117));

        Function1<String, Integer> parseInteger = Integer::parseInt;
        assertEquals(Integer.valueOf(117), parseInteger.apply("117"));
    }

    @Test
    public void composeTest() throws Exception {
        Function1<Integer, Integer> doubleInteger = a -> 2 * a;
        Function1<String, Integer> parseInteger = Integer::parseInt;
        Function1<String, Integer> parseAndDoubleInteger = parseInteger.compose(doubleInteger);
        assertEquals(Integer.valueOf(234), parseAndDoubleInteger.apply("117"));
    }
}