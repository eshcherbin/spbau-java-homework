package ru.spbau.eshcherbin.homework.hw4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * A container that either stores a value of given type or is empty
 * @param <T> type of the stored data
 */
public class Maybe<T> {
    /**
     * Data that is stored
     */
    private @Nullable T data;

    private Maybe(@Nullable T data) {
        this.data = data;
    }

    /**
     * Constructs a Maybe with t stored in it
     */
    @NotNull
    public static <T> Maybe<T> just(@NotNull T t) {
        return new Maybe<>(t);
    }

    /**
     * Constructs a Maybe with nothing stored in it
     */
    @NotNull
    public static <T> Maybe<T> nothing() {
        return new Maybe<>(null);
    }

    /**
     * Returns data that is stored in the Maybe
     * @return Data that is stored in the Maybe
     * @throws MaybeDataIsNullException when trying to extract data from nothing
     */
    @NotNull
    public T get() throws MaybeDataIsNullException {
        if (data == null) {
            throw new MaybeDataIsNullException();
        }
        return data;
    }

    /**
     * @return true if anything is stored, false otherwise
     */
    public boolean isPresent() {
        return data != null;
    }

    /**
     * Applies mapper to stored data
     * @param mapper function to be applied
     * @return Result of applying mapper
     */
    @NotNull
    public <U> Maybe<U> map(@NotNull Function<? super T, ? extends U> mapper) {
        return isPresent() ? Maybe.just(mapper.apply(data)) : Maybe.nothing();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Maybe<?> maybe = (Maybe<?>) o;

        return data != null ? data.equals(maybe.data) : maybe.data == null;

    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }
}
