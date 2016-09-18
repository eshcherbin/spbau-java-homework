package ru.spbau.eshcherbin.homework.hw2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MatrixTest {
    @Test
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

    @Test
    public void testSizeConstructor() throws Exception {
        Matrix matrix = new Matrix(10);
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++) {
                assertEquals(matrix.getAt(i, j), 0);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCopyConstructorFail() throws Exception {
        int[][] array = {
                {1, 2, 3},
                {2, 3},
                {3, 5, 8},
        };
        Matrix matrix = new Matrix(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSizeConstructorFail() throws Exception {
        Matrix matrix = new Matrix(0);
    }

    @Test
    public void elementsAlongSpiral() throws Exception {
        int[][] array = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25},
        };
        Matrix matrix = new Matrix(array);
        ArrayList<Integer> spiralElements = matrix.elementsAlongSpiral();
        List<Integer> desiredSpiralElements = Arrays.asList(
                13, 12, 17, 18, 19,
                14, 9, 8, 7, 6,
                11, 16, 21, 22, 23,
                24, 25, 20, 15, 10,
                5, 4, 3, 2, 1
        );
        assertEquals(desiredSpiralElements, spiralElements);
    }

    @Test
    public void sortColumns() throws Exception {
        int[][] array = {
                {5, 3, 1, 4, 2},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25},
        };
        int[][] sortedArray = {
                {1, 2, 3, 4, 5},
                {8, 10, 7, 9, 6},
                {13, 15, 12, 14, 11},
                {18, 20, 17, 19, 16},
                {23, 25, 22, 24, 21},
        };
        Matrix matrix = new Matrix(array);
        matrix.sortColumns();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                assertEquals(matrix.getAt(i, j), sortedArray[i][j]);
            }
        }
    }
}