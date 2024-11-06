package org.example.sparse;

import java.util.HashMap;
import java.util.Map;

public class SparseMatrixMultiplication {

    public static int[][] multiplySparseMatrix(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;

        int[][] result = new int[rowsA][colsB];

        // Se crea una estructura que mapea las posiciones de los valores no nulos
        Map<String, Integer> sparseMatrixA = new HashMap<>();
        Map<String, Integer> sparseMatrixB = new HashMap<>();

        // Almacenar los valores no nulos de A
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                if (A[i][j] != 0) {
                    sparseMatrixA.put(i + "," + j, A[i][j]);
                }
            }
        }

        // Almacenar los valores no nulos de B
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                if (B[i][j] != 0) {
                    sparseMatrixB.put(i + "," + j, B[i][j]);
                }
            }
        }

        // Realizar la multiplicación de las matrices
        for (Map.Entry<String, Integer> entryA : sparseMatrixA.entrySet()) {
            String[] indicesA = entryA.getKey().split(",");
            int rowA = Integer.parseInt(indicesA[0]);
            int colA = Integer.parseInt(indicesA[1]);
            int valueA = entryA.getValue();

            // Multiplicar con los elementos de la matriz B que tengan el mismo índice de columna
            for (Map.Entry<String, Integer> entryB : sparseMatrixB.entrySet()) {
                String[] indicesB = entryB.getKey().split(",");
                int rowB = Integer.parseInt(indicesB[0]);
                int colB = Integer.parseInt(indicesB[1]);
                int valueB = entryB.getValue();

                // A[i][j] * B[j][k] contribuye a result[i][k]
                if (colA == rowB) {
                    result[rowA][colB] += valueA * valueB;
                }
            }
        }

        return result;
    }

    // Método para imprimir una matriz
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
