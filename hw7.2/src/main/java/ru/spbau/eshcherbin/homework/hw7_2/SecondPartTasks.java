package ru.spbau.eshcherbin.homework.hw7_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public final class SecondPartTasks {

    private final static int NUMBER_OF_SHOTS = 1000000;

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream().filter(pathString -> {
            try {
                return Files.lines(Paths.get(pathString)).anyMatch(string -> string.contains(sequence));
            } catch (IOException e) {
                return false;
            }
        }).collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        Random random = new Random();
        return DoubleStream.generate(() -> Math.sqrt(Math.pow(random.nextDouble() - 0.5, 2)
                                                     + Math.pow(random.nextDouble() - 0.5, 2)))
                           .limit(NUMBER_OF_SHOTS).filter(d -> d <= 0.5).count() / (double) NUMBER_OF_SHOTS;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet().stream().max(Comparator.comparing(entry -> entry.getValue().stream()
                                                                                       .mapToInt(String::length).sum()))
                                               .map(Map.Entry::getKey)
                                               .orElse("");
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream().flatMap(map -> map.entrySet().stream()).collect(Collectors.toMap(Map.Entry::getKey,
                                                                                                Map.Entry::getValue,
                                                                                                Integer::sum));
    }
}
