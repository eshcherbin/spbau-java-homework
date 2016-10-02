package ru.spbau.eshcherbin.homework.hw4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class Maybe<T> {
    private @Nullable T data;

    public Maybe(@Nullable T data) {
        this.data = data;
    }

    @NotNull
    public static <T> Maybe<T> just(@NotNull T t) {
        return new Maybe<>(t);
    }

    @NotNull
    public static <T> Maybe<T> nothing() {
        return new Maybe<>(null);
    }

    @NotNull
    public T get() throws MaybeDataIsNullException {
        if (data == null) {
            throw new MaybeDataIsNullException();
        }
        return data;
    }

    public boolean isPresent() {
        return data != null;
    }

    @NotNull
    public <U> Maybe<U> map(@NotNull Function<? super T, ? extends U> mapper) {
        return isPresent() ? (Maybe.just(mapper.apply(data))) : (Maybe.nothing());
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
