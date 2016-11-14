package ru.spbau.eshcherbin.homework.hw5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Function2Test {

    private Function2<String, Integer, String> appendInteger = null;

    @Before
    public void setUp() throws Exception {
        appendInteger = (s, a) -> s + a.toString();
    }

    @Test
    public void applyTest() throws Exception {
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        assertEquals(Integer.valueOf(117 + 225), sum.apply(117, 225));

        assertEquals("117117", appendInteger.apply("117", 117));
    }

    @Test
    public void composeTest() throws Exception {
        Function1<String, Integer> parseInteger = Integer::parseInt;
        assertEquals(Integer.valueOf(117225), appendInteger.compose(parseInteger).apply("117", 225));
    }

    @Test
    public void bind1Test() throws Exception {
        Function1<Integer, String> appendTo117 = appendInteger.bind1("117");
        assertEquals("117234", appendTo117.apply(234));
        assertEquals("117225", appendTo117.apply(225));
    }

    @Test
    public void bind2Test() throws Exception {
        Function1<String, String> append225To = appendInteger.bind2(225);
        assertEquals("117225", append225To.apply("117"));
        assertEquals("430225", append225To.apply("430"));
    }

    @Test
    public void curryTest() throws Exception {
        Function1<String, Function1<Integer, String>> appendSomethingToSomething = appendInteger.curry();
        Function1<Integer, String> appendTo117 = appendSomethingToSomething.apply("117");
        assertEquals("117234", appendTo117.apply(234));
        assertEquals("117225", appendTo117.apply(225));
        Function1<Integer, String> appendTo430 = appendSomethingToSomething.apply("430");
        assertEquals("430234", appendTo430.apply(234));
        assertEquals("430225", appendTo430.apply(225));
    }
}