package ru.spbau.eshcherbin.homework.hw7_2;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.spbau.eshcherbin.homework.hw7_2.SecondPartTasks.*;

public class SecondPartTasksTest {

    private final static double DELTA = 1e-3;

    @Test
    public void testFindQuotes() {
        assertEquals(Collections.emptyList(), findQuotes(Collections.emptyList(), ""));
        List<String> paths = Arrays.asList("test/test1", "test/test2", "test/test3");
        assertEquals(Arrays.asList("test/test2", "test/test3"), findQuotes(paths, "Billy Brown"));
        assertEquals(Arrays.asList("test/test1", "test/test2", "test/test3"), findQuotes(paths, "."));
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(Math.PI / 4, piDividedBy4(), DELTA);
    }

    @Test
    public void testFindPrinter() {
        assertEquals("", findPrinter(Collections.emptyMap()));
        assertEquals(
                "Tolstoy",
                findPrinter(ImmutableMap.of(
                        "Tolstoy",
                        Arrays.asList("War and Peace and war and peace and war and what?",
                                "I lack imagination to create a better test"),
                        "Dostoevsky",
                        Collections.singletonList("Lorem ipsum dolor sit amet, consectetur cras amet")
                        )
                )
        );
        assertEquals(
                "Mika",
                findPrinter(ImmutableMap.of(
                        "Lordi",
                        Arrays.asList("Beast Loose in Paradise!", "Hard rock hallelujah"),
                        "Mika",
                        Arrays.asList("Big girl, you are beautiful!", "In any other world you can tell the difference",
                                "I tried to be like Grace Kelly")
                        )
                )
        );
    }

    @Test
    public void testCalculateGlobalOrder() {
        assertEquals(Collections.emptyMap(), calculateGlobalOrder(Collections.emptyList()));
        assertEquals(ImmutableMap.of("Nuts", 117, "Lollipop", 225, "Bread", 1),
                calculateGlobalOrder(Arrays.asList(ImmutableMap.of("Nuts", 117, "Bread", 1),
                                                   ImmutableMap.of("Lollipop", 225))));
        assertEquals(ImmutableMap.of("Nuts", 117, "Lollipop", 225, "Bread", 33, "Butter", 12),
                calculateGlobalOrder(Arrays.asList(ImmutableMap.of("Nuts", 117, "Bread", 1),
                        ImmutableMap.of("Lollipop", 225, "Bread", 32, "Butter", 12))));
    }
}
