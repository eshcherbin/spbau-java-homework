package ru.spbau.eshcherbin.homework.hw2;

import java.util.ArrayList;
import java.util.Collections;

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

    /**
     * Returns the matrix contents along the clockwise spiral from the center
     * Size should be odd
     * @return ArrayList that contains the elements along the spiral if matrix's size is odd, null otherwise
     */
    public ArrayList<Integer> elementsAlongSpiral() {
        if (matrix.length % 2 == 0) {
            return null;
        }
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int step = 0; step <= matrix.length / 2; step++) {
            for (int i = step; i < matrix.length - step; i++) {
                result.add(matrix[step][i]);
            }
            for (int i = step + 1; i < matrix.length - step; i++) {
                result.add(matrix[i][matrix.length - step - 1]);
            }
            for (int i = matrix.length - step - 2; i >= step; i--) {
                result.add(matrix[matrix.length - step - 1][i]);
            }
            for (int i = matrix.length - step - 2; i > step; i--) {
                result.add(matrix[i][step]);
            }
        }
        Collections.reverse(result);
        return result;
    }
}
