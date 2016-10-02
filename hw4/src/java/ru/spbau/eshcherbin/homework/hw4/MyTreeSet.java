package ru.spbau.eshcherbin.homework.hw4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyTreeSet<T extends Comparable<T>> {

    private int size;

    private @Nullable TreeNode root;

    public MyTreeSet() {
        size = 0;
        root = null;
    }

    public boolean add(@NotNull T element) {
        if (root == null) {
            root = new TreeNode(element);
        }
        TreeNode currentNode = root;
        while (true) {
            int compareResult = element.compareTo(currentNode.data);
            if (compareResult == 0) {
                return false;
            } else if (compareResult < 0) {
                if (currentNode.leftSon == null) {
                    currentNode.leftSon = new TreeNode(element);
                    return true;
                } else {
                    currentNode = currentNode.leftSon;
                }
            } else {
                if (currentNode.rightSon == null) {
                    currentNode.rightSon = new TreeNode(element);
                    return true;
                }
                else {
                    currentNode = currentNode.rightSon;
                }
            }
        }
    }

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

    public int size() {
        return size;
    }

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
