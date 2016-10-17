package ru.spbau.eshcherbin.homework.hw4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A simple container based on binary search tree
 * @param <T> Type of the contained values
 */
public class MyTreeSet<T extends Comparable<T>> {

    /**
     * Number of elements contained
     */
    private int size;

    /**
     * The root of the tree
     */
    private @Nullable TreeNode root;

    public MyTreeSet() {
    }

    /**
     * Adds an element
     * @return true if element wasn't already contained, false otherwise
     */
    public boolean add(@NotNull T element) {
        if (root == null) {
            root = new TreeNode(element);
            size++;
            return true;
        }
        TreeNode currentNode = root;
        while (true) {
            int compareResult = element.compareTo(currentNode.data);
            if (compareResult == 0) {
                return false;
            } else if (compareResult < 0) {
                if (currentNode.leftSon == null) {
                    currentNode.leftSon = new TreeNode(element);
                    size++;
                    return true;
                } else {
                    currentNode = currentNode.leftSon;
                }
            } else {
                if (currentNode.rightSon == null) {
                    currentNode.rightSon = new TreeNode(element);
                    size++;
                    return true;
                }
                else {
                    currentNode = currentNode.rightSon;
                }
            }
        }
    }

    /**
     * @return true if element is contained, false otherwise
     */
    public boolean contains(@NotNull T element) {
        if (root == null) {
            return false;
        }
        TreeNode currentNode = root;
        while (true) {
            int compareResult = element.compareTo(currentNode.data);
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
                }
                else {
                    currentNode = currentNode.rightSon;
                }
            }
        }
    }

    /**
     * @return Number of elements contained
     */
    public int size() {
        return size;
    }

    /**
     * An auxiliary class representing a node of the tree
     */
    private class TreeNode {
        private @NotNull T data;
        private @Nullable TreeNode leftSon;
        private @Nullable TreeNode rightSon;

        public TreeNode(@NotNull T data) {
            this.data = data;
            leftSon = null;
            rightSon = null;
        }
    }
}
