package ru.spbau.eshcherbin.homework.hw4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyTreeSet<T extends Comparable> {

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
            if (currentNode.data == element) {
                return false;
            }
            if (currentNode.)
        }
        return true;
    }

    private class TreeNode {
        private T data;
        private TreeNode leftSon;
        private TreeNode rightSon;

        public TreeNode(T data) {
            this.data = data;
            leftSon = null;
            rightSon = null;
        }
    }
}
