package ru.spbau.eshcherbin.homework.hw2;

/**
 * A square matrix of integers
 */
public class Matrix {
    /**
     * Two-dimensional array to store the elements
     */
    private int[][] matrix;

    /**
     * Creates a new Matrix based on a two-dimensional array
     */
    public Matrix(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null || matrix[i].length != matrix.length) {
                throw new IllegalArgumentException();
            }
        }
        this.matrix = matrix.clone();
        for (int i = 0; i < matrix.length; i++) {
            this.matrix[i] = matrix[i].clone();
        }
    }

    /**
     * Creates a zero square Matrix of given size
     */
    public Matrix(int size) {
        if (size == 0) { // size should be positive
            throw new IllegalArgumentException();
        }
        matrix = new int[size][size];
    }

    public int getSize() {
        return matrix.length;
    }

    public int getAt(int i, int j) {
        return matrix[i][j];
    }

    public void setAt(int i, int j, int value) {
        matrix[i][j] = value;
    }
}
