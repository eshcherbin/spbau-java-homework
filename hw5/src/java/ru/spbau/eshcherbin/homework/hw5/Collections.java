package ru.spbau.eshcherbin.homework.hw5;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

/**
 * A class providing some common operations on iterable collections
 */
public class Collections {
    private Collections() {
        // Shouldn't be inherited
    }

    /**
     * @return A list of results of applying function on elements of iterable
     */
    @NotNull
    public static <A, R> List<R> map(@NotNull Function1<? super A, ? extends R> function,
                                     @NotNull Iterable<A> iterable) {
        List<R> result = new LinkedList<>();
        for (A element : iterable) {
            result.add(function.apply(element));
        }
        return result;
    }

    /**
     * @return A list of elements of iterable on which predicate returns true
     */
    @NotNull
    public static <A> List<A> filter(@NotNull Predicate<? super A> predicate, @NotNull Iterable<A> iterable) {
        List<A> result = new LinkedList<>();
        for (A element : iterable) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * @return A longest prefix of iterable on which predicate always returns true
     */
    @NotNull
    public static <A> List<A> takeWhile(@NotNull Predicate<? super A> predicate, @NotNull Iterable<A> iterable) {
        List<A> result = new LinkedList<>();
        for (A element : iterable) {
            if (!predicate.apply(element)) {
                break;
            }
            result.add(element);
        }
        return result;
    }

    /**
     * @return A longest prefix of iterable on which predicate always returns false
     */
    @NotNull
    public static <A> List<A> takeUnless(@NotNull Predicate<? super A> predicate, @NotNull Iterable<A> iterable) {
        return takeWhile(predicate.not(), iterable);
    }

    //TODO: implement foldl and foldr

}
