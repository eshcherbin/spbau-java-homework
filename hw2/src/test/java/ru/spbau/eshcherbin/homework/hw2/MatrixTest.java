package ru.spbau.eshcherbin.homework.hw2;

import static org.junit.Assert.*;

public class MatrixTest {
    @org.junit.Test
    public void testCopyConstructor() throws Exception {
        int[][] array = {
                {1, 2, 3},
                {2, 3, 5},
                {3, 5, 8},
        };
        Matrix matrix = new Matrix(array);
        assertEquals(matrix.getSize(), array.length);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                assertEquals(matrix.getAt(i, j), array[i][j]);
            }
        }
    }

    @org.junit.Test
    public void testSizeConstructor() throws Exception {
        Matrix matrix = new Matrix(10);
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++) {
                assertEquals(matrix.getAt(i, j), 0);
            }
        }
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testCopyConstructorFail() throws Exception {
        int[][] array = {
                {1, 2, 3},
                {2, 3},
                {3, 5, 8},
        };
        Matrix matrix = new Matrix(array);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testSizeConstructorFail() throws Exception {
        Matrix matrix = new Matrix(0);
    }
}