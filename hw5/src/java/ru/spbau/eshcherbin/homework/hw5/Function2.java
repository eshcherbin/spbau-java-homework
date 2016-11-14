package ru.spbau.eshcherbin.homework.hw5;

/**
 * An interface describing a function of two arguments
 * @param <A1> Type of the function's first argument
 * @param <A2> Type of the function's second argument
 * @param <R> Type of the function's result
 */
public interface Function2<A1, A2, R> {
    /**
     * @return Result of application of the function on argument1
     */
    R apply(A1 argument1, A2 argument2);

    /**
     * @return Composition of the function with function g
     */
    default <R2> Function2<A1, A2, R2> compose(Function1<? super R, R2> g) {
        return (argument1, argument2) -> g.apply(apply(argument1, argument2));
    }

    /**
     * @return A function of one argument that behaves like function(argument1, x)
     */
    default Function1<A2, R> bind1(A1 argument1) {
        return argument2 -> apply(argument1, argument2);
    }

    /**
     * @return A function of one argument that behaves like function(x, argument2)
     */
    default Function1<A1, R> bind2(A2 argument2) {
        return argument1 -> apply(argument1, argument2);
    }

    /**
     * @return A curried version of the function
     */
    default Function1<A1, Function1<A2, R>> curry() {
        return this::bind1;
    }
}
