package ru.spbau.eshcherbin.homework.hw4;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IntegerSquarer {

    private @NotNull List<Maybe<Integer>> numbers;

    public IntegerSquarer() {
        numbers = new ArrayList<Maybe<Integer>>();
    }

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

    public void squareNumbers() {
        numbers = numbers.stream().map(m -> m.map(n -> n * n)).collect(Collectors.toList());
    }

    public void writeToFile(@NotNull OutputStream out) {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(out));
        for (Maybe maybe : numbers) {
            printWriter.print(maybe.isPresent() ? maybe.get().toString() : "null");
            printWriter.println();
        }
    }
}
