package ru.spbau.eshcherbin.homework.hw4;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IntegerSquarerTest {
    private IntegerSquarer integerSquarer;

    @Before
    public void setUp() throws Exception {
        integerSquarer = new IntegerSquarer();
    }

    @Test
    public void readFromFileTest() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        for (int i = 0; i < 10; i++) {
            printWriter.println(i);
            printWriter.println("null");
        }
        printWriter.flush();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        integerSquarer.readNumbers(inputStream);
        ArrayList<Maybe<Integer>> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(Maybe.just(i));
            arrayList.add(Maybe.nothing());
        }
        assertEquals(arrayList, integerSquarer.getNumbers());
        printWriter.close();
        inputStream.close();
    }

    @Test
    public void squareNumbersTest() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        for (int i = 0; i < 10; i++) {
            printWriter.println(i);
            printWriter.println("null");
        }
        printWriter.flush();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        integerSquarer.readNumbers(inputStream);
        List<Maybe<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Maybe.just(i));
            list.add(Maybe.nothing());
        }
        for (int step = 0; step < 2; step++) {
            integerSquarer.squareNumbers();
            list = list.stream().map(x -> x.map(n -> n * n)).collect(Collectors.toList());
            assertEquals(list, integerSquarer.getNumbers());
        }
        printWriter.close();
        inputStream.close();
    }

    @Test
    public void writeToFileTest() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        for (int i = 0; i < 10; i++) {
            printWriter.println(i);
            printWriter.println("null");
        }
        printWriter.flush();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        integerSquarer.readNumbers(inputStream);
        ByteArrayOutputStream anotherOutputStream = new ByteArrayOutputStream();
        integerSquarer.writeNumbers(anotherOutputStream);
        ByteArrayInputStream anotherInputStream = new ByteArrayInputStream(anotherOutputStream.toByteArray());
        Scanner scanner = new Scanner(anotherInputStream);
        for (int i = 0; i < 10; i++) {
            assertEquals(Integer.toString(i), scanner.nextLine());
            assertEquals("null", scanner.nextLine());
        }
        assertFalse(scanner.hasNextLine());
        printWriter.close();
        inputStream.close();
        anotherOutputStream.close();
        scanner.close();
    }
}