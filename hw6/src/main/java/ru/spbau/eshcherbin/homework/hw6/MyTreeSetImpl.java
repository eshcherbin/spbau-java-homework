package ru.spbau.eshcherbin.homework.hw6;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Set of comparable elements that is capable of finding elements higher or lower than given
 * Implementation is based on a binary search tree
 * @param <E> the type of elements maintained by this set
 */
public class MyTreeSetImpl<E> extends AbstractSet<E> implements MyTreeSet<E> {
    /**
     * Number of elements contained
     */
    private int size;

    /**
     * The root of the tree
     */
    private
    @Nullable
    TreeNode<E> root;

    /**
     * A comparator which is used to compare elements of the set
     */
    private
    @Nullable
    Comparator<? super E> comparator;

    /**
     * Creates a set that expects its elements to be Comparable
     */
    public MyTreeSetImpl() {
    }

    /**
     * Creates a set that uses given comparator to compare its elements
     */
    public MyTreeSetImpl(@NotNull Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Compares two elements either using given comparator or assuming that the elements implement Comparable
     *
     * @return the result of comparison
     */
    private int compareElements(@NotNull E element1, @NotNull E element2) {
        if (comparator == null) {
            return ((Comparable<E>) element1).compareTo(element2);
        } else {
            return comparator.compare(element1, element2);
        }
    }

    /**
     * Adds given element to the set
     *
     * @return true if the element wasn't already present in this set
     */
    @Override
    public boolean add(@NotNull E element) {
        if (root == null) {
            root = new TreeNode<>(element);
            size++;
            return true;
        }
        @NotNull TreeNode<E> currentNode = root;
        while (true) {
            int compareResult = compareElements(element, currentNode.data);
            if (compareResult == 0) {
                return false;
            } else if (compareResult < 0) {
                if (currentNode.leftSon == null) {
                    currentNode.leftSon = new TreeNode<>(element, currentNode);
                    size++;
                    return true;
                } else {
                    currentNode = currentNode.leftSon;
                }
            } else {
                if (currentNode.rightSon == null) {
                    currentNode.rightSon = new TreeNode<>(element, currentNode);
                    size++;
                    return true;
                } else {
                    currentNode = currentNode.rightSon;
                }
            }
        }
    }

    /**
     * Searches for given object in the set
     *
     * @return whether the object is present in this set
     */
    @Override
    public boolean contains(Object o) {
        E element = (E) o;
        if (o == null) {
            return false;
        }
        if (root == null) {
            return false;
        }
        TreeNode<E> currentNode = root;
        while (true) {
            int compareResult = compareElements(element, currentNode.data);
            if (compareResult == 0) {
                return true;
            } else if (compareResult < 0) {
                if (currentNode.leftSon == null) {
                    return false;
                } else {
                    currentNode = currentNode.leftSon;
                }
            } else {
                if (currentNode.rightSon == null) {
                    return false;
                } else {
                    currentNode = currentNode.rightSon;
                }
            }
        }
    }

    /**
     * Removes given object from the set
     *
     * @return true if the object was present in this set before the removal
     */
    @Override
    public boolean remove(Object o) {
        E element = (E) o;
        if (o == null) {
            return false;
        }
        TreeNode<E> currentNode = root;
        while (currentNode != null && compareElements(currentNode.data, element) != 0) {
            currentNode = (compareElements(element, currentNode.data) > 0) ? currentNode.rightSon : currentNode.leftSon;
        }
        if (currentNode == null) {
            return false;
        }
        if (currentNode.leftSon == null) {
            if (currentNode.parent == null) {
                root = currentNode.rightSon;
            } else {
                if (currentNode == currentNode.parent.leftSon) {
                    currentNode.parent.leftSon = currentNode.rightSon;
                } else {
                    currentNode.parent.rightSon = currentNode.rightSon;
                }
            }
            if (currentNode.rightSon != null) {
                currentNode.rightSon.parent = currentNode.parent;
            }
        } else if (currentNode.rightSon == null) {
            if (currentNode.parent == null) {
                root = currentNode.leftSon;
            } else {
                if (currentNode == currentNode.parent.leftSon) {
                    currentNode.parent.leftSon = currentNode.leftSon;
                } else {
                    currentNode.parent.rightSon = currentNode.leftSon;
                }
            }
            currentNode.leftSon.parent = currentNode.parent;
        } else {
            TreeNode<E> nextNode = currentNode.rightSon;
            while (nextNode.leftSon != null) {
                nextNode = nextNode.leftSon;
            }
            currentNode.data = nextNode.data;
            nextNode.parent.leftSon = nextNode.rightSon;
            if (nextNode.rightSon != null) {
                nextNode.rightSon.parent = nextNode.parent;
            }
        }
        size--;
        return true;
    }

    /**
     * Returns the number of elements contained in this set
     *
     * @return the number of elements contained in this set
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in this set
     *
     * @return an iterator over the elements in this set
     */
    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new MyTreeSetIterator();
    }

    /**
     * Returns an iterator over the elements in this set in descending order
     *
     * @return an iterator over the elements in this set in descending order
     */
    @NotNull
    @Override
    public Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    /**
     * Returns a reverse order view of the elements contained in this set
     *
     * @return a reverse order view of this set
     */
    @NotNull
    @Override
    public MyTreeSet<E> descendingSet() {
        return new MyTreeDescendingSet();
    }

    /**
     * Returns the first (lowest) element currently in this set
     *
     * @return the first (lowest) element currently in this set
     */
    @NotNull
    @Override
    public E first() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        TreeNode<E> currentNode = root;
        while (currentNode.leftSon != null) {
            currentNode = currentNode.leftSon;
        }
        return currentNode.data;
    }

    /**
     * Returns the last (highest) element currently in this set
     *
     * @return the last (highest) element currently in this set
     */
    @NotNull
    @Override
    public E last() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        TreeNode<E> currentNode = root;
        while (currentNode.rightSon != null) {
            currentNode = currentNode.rightSon;
        }
        return currentNode.data;
    }

    /**
     * Returns the greatest element in this set strictly less than the given element
     *
     * @return the greatest element less than e, or null if there is no such element
     */
    @Nullable
    @Override
    public E lower(@NotNull E e) {
        E result = null;
        TreeNode<E> currentNode = root;
        while (currentNode != null) {
            if (compareElements(currentNode.data, e) < 0) {
                result = currentNode.data;
                currentNode = currentNode.rightSon;
            } else {
                currentNode = currentNode.leftSon;
            }
        }
        return result;
    }

    /**
     * Returns the greatest element in this set less than or equal to the given element
     *
     * @return the greatest element less than or equal to e, or null if there is no such element
     */
    @Nullable
    @Override
    public E floor(@NotNull E e) {
        E result = null;
        TreeNode<E> currentNode = root;
        while (currentNode != null) {
            if (compareElements(currentNode.data, e) <= 0) {
                result = currentNode.data;
                currentNode = currentNode.rightSon;
            } else {
                currentNode = currentNode.leftSon;
            }
        }
        return result;
    }

    /**
     * Returns the least element in this set strictly greater than the given element
     *
     * @return the least element greater then e, or null if there is no such element
     */
    @Nullable
    @Override
    public E ceiling(@NotNull E e) {
        E result = null;
        TreeNode<E> currentNode = root;
        while (currentNode != null) {
            if (compareElements(currentNode.data, e) >= 0) {
                result = currentNode.data;
                currentNode = currentNode.leftSon;
            } else {
                currentNode = currentNode.rightSon;
            }
        }
        return result;
    }

    /**
     * Returns the least element in this set strictly greater than the given element
     *
     * @return the least element greater than e, or null if there is no such element
     */
    @Nullable
    @Override
    public E higher(@NotNull E e) {
        E result = null;
        TreeNode<E> currentNode = root;
        while (currentNode != null) {
            if (compareElements(currentNode.data, e) > 0) {
                result = currentNode.data;
                currentNode = currentNode.leftSon;
            } else {
                currentNode = currentNode.rightSon;
            }
        }
        return result;
    }

    /**
     * An auxiliary class representing a node of the tree
     */
    private static class TreeNode<E> {
        private @NotNull E data;
        private @Nullable TreeNode<E> leftSon;
        private @Nullable TreeNode<E> rightSon;
        private @Nullable TreeNode<E> parent;

        public TreeNode(@NotNull E data) {
            this.data = data;
            this.parent = null;
        }

        public TreeNode(@NotNull E data, @Nullable TreeNode<E> parent) {
            this.data = data;
            this.parent = parent;
        }
    }

    /**
     * Enumeration of two possible states of the vertices during the traversal
     */
    private enum IteratorNodeState {VISITED, NOT_VISITED}

    /**
     * An iterator that is used to go over the elements of the set
     */
    private class MyTreeSetIterator implements Iterator<E> {
        /**
         * A stack of states of the nodes on the way from the root to the current node
         */
        private Stack<IteratorNodeState> stateStack;

        /**
         * The node we are currently staying at
         */
        private TreeNode<E> currentNode;

        MyTreeSetIterator() {
            stateStack = new Stack<>();
            if (root != null) {
                currentNode = root;
                stateStack.push(IteratorNodeState.NOT_VISITED);
                advanceLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E nextElement = currentNode.data;
            stateStack.pop();
            if (currentNode.rightSon == null) {
                currentNode = currentNode.parent;
                while (currentNode != null && stateStack.peek() == IteratorNodeState.VISITED) {
                    stateStack.pop();
                    currentNode = currentNode.parent;
                }
            } else {
                stateStack.push(IteratorNodeState.VISITED);
                currentNode = currentNode.rightSon;
                stateStack.push(IteratorNodeState.NOT_VISITED);
                advanceLeft();
            }
            return nextElement;
        }

        /**
         * Goes to the first element in current node's subtree
         */
        private void advanceLeft() {
            if (currentNode == null) {
                return;
            }
            while (currentNode.leftSon != null) {
                currentNode = currentNode.leftSon;
                stateStack.push(IteratorNodeState.NOT_VISITED);
            }
        }
    }

    /**
     * View of the set with elements going in reverse order
     * All methods that don't have documentation are just doing the inverse of the
     * corresponding methods in the main set.
     */
    private class MyTreeDescendingSet extends AbstractSet<E> implements MyTreeSet<E> {

        MyTreeDescendingSet() {
        }

        @NotNull
        @Override
        public Iterator<E> iterator() {
            return new MyTreeDescendingSetIterator();
        }

        @Override
        public int size() {
            return MyTreeSetImpl.this.size();
        }

        @NotNull
        @Override
        public Iterator<E> descendingIterator() {
            return MyTreeSetImpl.this.iterator();
        }

        @NotNull
        @Override
        public MyTreeSet<E> descendingSet() {
            return MyTreeSetImpl.this;
        }

        @NotNull
        @Override
        public E first() {
            return MyTreeSetImpl.this.last();
        }

        @NotNull
        @Override
        public E last() {
            return MyTreeSetImpl.this.first();
        }

        @Nullable
        @Override
        public E lower(@NotNull E e) {
            return MyTreeSetImpl.this.higher(e);
        }

        @Nullable
        @Override
        public E floor(@NotNull E e) {
            return MyTreeSetImpl.this.ceiling(e);
        }

        @Nullable
        @Override
        public E ceiling(@NotNull E e) {
            return MyTreeSetImpl.this.floor(e);
        }

        @Nullable
        @Override
        public E higher(@NotNull E e) {
            return MyTreeSetImpl.this.lower(e);
        }

        /**
         * An iterator that is used to go over the elements of the descending set
         */
        private class MyTreeDescendingSetIterator implements Iterator<E> {
            private Stack<IteratorNodeState> stateStack;
            private TreeNode<E> currentNode;

            MyTreeDescendingSetIterator() {
                stateStack = new Stack<>();
                if (root != null) {
                    currentNode = root;
                    stateStack.push(IteratorNodeState.NOT_VISITED);
                    advanceRight();
                }
            }

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E nextElement = currentNode.data;
                stateStack.pop();
                if (currentNode.leftSon == null) {
                    currentNode = currentNode.parent;
                    while (currentNode != null && stateStack.peek() == IteratorNodeState.VISITED) {
                        stateStack.pop();
                        currentNode = currentNode.parent;
                    }
                } else {
                    stateStack.push(IteratorNodeState.VISITED);
                    currentNode = currentNode.leftSon;
                    stateStack.push(IteratorNodeState.NOT_VISITED);
                    advanceRight();
                }
                return nextElement;
            }

            /**
             * Goes to the last element in the current node's subtree
             */
            private void advanceRight() {
                if (currentNode == null) {
                    return;
                }
                while (currentNode.rightSon != null) {
                    currentNode = currentNode.rightSon;
                    stateStack.push(IteratorNodeState.NOT_VISITED);
                }
            }
        }
    }
}
