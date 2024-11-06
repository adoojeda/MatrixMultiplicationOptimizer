package org.example.basic;

import java.util.Arrays;

public class TestMatrixMultiplication {

    public static void main(String[] args) {
        int size = 4;
        int[][] A = generateRandomMatrix(size);
        int[][] B = generateRandomMatrix(size);

        System.out.println("Basic Multiplication:");
        int[][] result = MatrixMultiplication.multiply(A, B);

        System.out.println("Matrix A:");
        printMatrix(A);
        System.out.println("Matrix B:");
        printMatrix(B);
        System.out.println("Result:");
        printMatrix(result);
    }

    public static int[][] generateRandomMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
