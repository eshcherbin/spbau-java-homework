package ru.spbau.eshcherbin.homework.hw5;

/**
 * An interface describing a function of one argument
 * @param <A> Type of the function's argument
 * @param <R> Type of the function's result
 */
public interface Function1<A, R> {
    /**
     * @return Result of application of the function on argument
     */
    R apply(A argument);

    /**
     * @return Composition of the function with function g
     */
    default <R2> Function1<A, R2> compose(Function1<? super R, R2> g) {
        return argument -> g.apply(apply(argument));
    }
}
