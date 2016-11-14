package ru.spbau.eshcherbin.homework.hw5;

/**
 * An interface describing a predicate
 * @param <A> Type of the predicate's argument
 */
public interface Predicate<A> extends Function1<A, Boolean> {
    /**
     * @return A predicate that returns logical OR of the predicate and otherPredicate
     */
    default Predicate<A> or(Predicate<? super A> otherPredicate) {
        return argument -> apply(argument) || otherPredicate.apply(argument);
    }

    /**
     * @return A predicate that returns logical AND of the predicate and otherPredicate
     */
    default Predicate<A> and(Predicate<? super A> otherPredicate) {
        return argument -> apply(argument) && otherPredicate.apply(argument);
    }

    /**
     * @return A predicate that returns logical NOT of the predicate
     */
    default Predicate<A> not() {
        return argument -> !apply(argument);
    }

    /**
     * @return A predicate that always returns true
     */
    static <A> Predicate<A> getAlwaysTruePredicate() {
        return ignoredArgument -> true;
    }

    /**
     * @return A predicate that always returns false */
    static <A> Predicate<A> getAlwaysFalsePredicate() {
        return ignoredArgument -> false;
    }
}
