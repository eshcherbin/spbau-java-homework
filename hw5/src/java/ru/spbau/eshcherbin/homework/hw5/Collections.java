package ru.spbau.eshcherbin.homework.hw5;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
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
     * An implementation of foldr (see below)
     */
    @NotNull
    private static <A, R> R foldr(@NotNull Function2<? super A, ? super R, ? extends R> function,
                                                R initial, @NotNull Iterator<A> iterator) {
        if (iterator.hasNext()) {
            return foldr(function, function.apply(iterator.next(), initial), iterator);
        } else {
            return initial;
        }
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

    /**
     * @return A combination of iterable elements using function in right-associative way
     */
    @NotNull
    public static <A, R> R foldr(@NotNull Function2<? super A, ? super R, ? extends R> function,
                                 R initial, @NotNull Iterable<A> iterable) {
        return foldr(function, initial, iterable.iterator());
    }

    /**
     * @return A combination of iterable elements using function in left-associative way
     */
    @NotNull
    public static <A, R> R foldl(@NotNull Function2<? super R, ? super A, ? extends R> function,
                                 R initial, @NotNull Iterable<A> iterable) {
        R accumulator = initial;
        for (A element : iterable) {
            accumulator = function.apply(accumulator, element);
        }
        return accumulator;
    }
}
