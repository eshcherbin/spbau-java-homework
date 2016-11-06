package ru.spbau.eshcherbin.homework.hw6;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Interface of a set of comparable elements that is capable of finding elements higher or lower than given
 * @param <E> the type of elements maintained by this set
 */
public interface MyTreeSet<E> extends Set<E> {

    /**
     * Returns an iterator over the elements in this set in descending order
     * @return an iterator over the elements in this set in descending order
     */
    Iterator<E> descendingIterator();

    /**
     * Returns a reverse order view of the elements contained in this set
     * @return a reverse order view of this set
     */
    MyTreeSet<E> descendingSet();


    /**
     * Returns the first (lowest) element currently in this set
     * @return the first (lowest) element currently in this set
     */
    E first();

    /**
     * Returns the last (highest) element currently in this set
     * @return the last (highest) element currently in this set
     */
    E last();


    /**
     * Returns the greatest element in this set strictly less than the given element
     * @return the greatest element less than e, or null if there is no such element
     */
    E lower(E e);

    /**
     * Returns the greatest element in this set less than or equal to the given element
     * @return the greatest element less than or equal to e, or null if there is no such element
     */
    E floor(E e);


    /**
     * Returns the least element in this set strictly greater than the given element
     * @return the least element greater then e, or null if there is no such element
     */
    E ceiling(E e);

    /**
     * Returns the least element in this set strictly greater than the given element
     * @return the least element greater than e, or null if there is no such element
     */
    E higher(E e);
}
