package ru.spbau.eshcherbin.homework.hw4;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A utility class that can read some integers from file, square them and write the result to another file
 */
public class IntegerSquarer {

    /**
     * A list that contains all read numbers.
     */
    private @NotNull List<Maybe<Integer>> numbers;

    public IntegerSquarer() {
        numbers = new ArrayList<>();
    }

    /**
     * Reads numbers from given stream line by line.
     * If current line is not a number Maybe.nothing() is added to numbers
     */
    public void readFromFile(@NotNull InputStream in) {
        numbers.clear();
        Scanner scanner = new Scanner(new InputStreamReader(in));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Maybe<Integer> current;
            try {
                current = Maybe.just(Integer.parseInt(line));
            } catch (NumberFormatException e) {
                current = Maybe.nothing();
            }
            numbers.add(current);
        }
    }

    /**
     * Squares contained numbers
     */
    public void squareNumbers() {
        numbers = numbers.stream().map(m -> m.map(n -> n * n)).collect(Collectors.toList());
    }

    /**
     * Writes numbers to given stream
     */
    public void writeToFile(@NotNull OutputStream out) {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(out));
        for (Maybe maybe : numbers) {
            printWriter.print(maybe.isPresent() ? maybe.get().toString() : "null");
            printWriter.println();
        }
    }
}
