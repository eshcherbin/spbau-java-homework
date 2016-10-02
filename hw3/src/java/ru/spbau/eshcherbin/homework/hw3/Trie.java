package ru.spbau.eshcherbin.homework.hw3;

import java.io.*;
import java.util.HashMap;

/**
 * A data structure used to store a set of strings effectively in a tree
 */
public class Trie implements StreamSerializable {

    /**
     * The tree root;
     */
    private Node root;
    /**
     * Number of strings stored
     */
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    /**
     * Adds element to the Trie.
     * @return true if element wasn't already present in the Trie, false otherwise
     * @throws IllegalArgumentException if element is null
     */
    public boolean add(String element) {
        if (element == null) {
            throw new IllegalArgumentException("null argument in Trie.add");
        }
        Node currentNode = root;
        for (int i = 0; i < element.length(); i++) {
            if (!currentNode.transitions.containsKey(element.charAt(i))) {
                currentNode.transitions.put(element.charAt(i), new Node());
            }
            currentNode = currentNode.transitions.get(element.charAt(i));
        }
        if (!currentNode.isTerminal) {
            // walk along element one more to update it's parents
            currentNode.isTerminal = true;
            root.numberOfTerminalsInSubtree++;
            currentNode = root;
            for (int i = 0; i < element.length(); i++) {
                currentNode = currentNode.transitions.get(element.charAt(i));
                currentNode.numberOfTerminalsInSubtree++;
            }
            size++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return Whether element is stored
     * @throws IllegalArgumentException if element is null
     */
    public boolean contains(String element) {
        if (element == null) {
            throw new IllegalArgumentException("null argument in Trie.contains");
        }
        Node currentNode = root;
        for (int i = 0; currentNode != null && i < element.length(); i++) {
            currentNode = currentNode.transitions.get(element.charAt(i));
        }
        return currentNode != null && currentNode.isTerminal;
    }

    /**
     * Removes given String from the Trie
     * @return true if element was was present in the Trie before removing, false otherwise
     * @throws IllegalArgumentException if element is null
     */
    public boolean remove(String element) {
        if (element == null) {
            throw new IllegalArgumentException("null argument in Trie.remove");
        }
        Node currentNode = root;
        for (int i = 0; currentNode != null && i < element.length(); i++) {
            currentNode = currentNode.transitions.get(element.charAt(i));
        }
        if (currentNode != null && currentNode.isTerminal) {
            // walk along element one more to update it's parents
            currentNode.isTerminal = false;
            root.numberOfTerminalsInSubtree--;
            currentNode = root;
            for (int i = 0; i < element.length(); i++) {
                currentNode = currentNode.transitions.get(element.charAt(i));
                currentNode.numberOfTerminalsInSubtree--;
            }
            size--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return Number of elements stored
     */
    public int size() {
        return size;
    }

    /**
     * @return How many elements start with given prefix
     * @throws IllegalArgumentException if prefix is null
     */
    public int howManyStartsWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("null argument in Trie.howManyStartsWithPrefix");
        }
        Node currentNode = root;
        for (int i = 0; currentNode != null && i < prefix.length(); i++) {
            currentNode = currentNode.transitions.get(prefix.charAt(i));
        }
        return currentNode == null ? 0 : currentNode.numberOfTerminalsInSubtree;
    }

    /**
     * Writes the trie to given OutputStream
     */
    @Override
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeInt(size);
        objectOutputStream.writeObject(root);
    }

    /**
     * Reads the trie from given OutputStream
     */
    @Override
    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        size = objectInputStream.readInt();
        root = (Node) objectInputStream.readObject();
    }

    /**
     * A class that represents a tree vertex
     */
    private class Node implements Serializable{
        private boolean isTerminal;
        private int numberOfTerminalsInSubtree;
        private HashMap<Character, Node> transitions;

        public Node() {
            isTerminal = false;
            numberOfTerminalsInSubtree = 0;
            transitions = new HashMap<>();
        }

        private void writeObject(java.io.ObjectOutputStream out) throws IOException {
            out.writeBoolean(isTerminal);
            out.writeInt(numberOfTerminalsInSubtree);
            out.writeObject(transitions);
        }

        private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
            isTerminal = in.readBoolean();
            numberOfTerminalsInSubtree = in.readInt();
            transitions = (HashMap<Character, Node>) in.readObject();
        }
    }
}
