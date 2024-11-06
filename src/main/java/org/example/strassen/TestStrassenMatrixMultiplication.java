package org.example.strassen;

import java.util.Arrays;

public class TestStrassenMatrixMultiplication {

    public static void main(String[] args) {
        int size = 4;  // Tamaño de la matriz, puedes cambiarlo
        int[][] A = generateRandomMatrix(size);
        int[][] B = generateRandomMatrix(size);

        // Multiplicación con Strassen
        System.out.println("\nMultiplicación con Strassen:");
        int[][] strassenResult = StrassenMatrixMultiplication.strassenMultiply(A, B);

        // Mostrar resultados
        System.out.println("\nMatrix A:");
        printMatrix(A);
        System.out.println("\nMatrix B:");
        printMatrix(B);
        System.out.println("\nResult:");
        printMatrix(strassenResult);
    }

    // Generar una matriz aleatoria
    public static int[][] generateRandomMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
        return matrix;
    }

    // Imprimir la matriz
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

}
