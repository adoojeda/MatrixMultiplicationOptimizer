import org.example.sparse.SparseMatrixMultiplication;

import java.util.Arrays;

public class TestSparseMatrixMultiplication {

    public static void main(String[] args) {
        int size = 4;  // Puedes cambiar el tamaño de la matriz
        int[][] A = generateRandomSparseMatrix(size);
        int[][] B = generateRandomSparseMatrix(size);

        System.out.println("Multiplicación de matrices dispersas:");
        int[][] result = SparseMatrixMultiplication.multiplySparseMatrix(A, B);

        System.out.println("\nMatriz A:");
        printMatrix(A);
        System.out.println("\nMatriz B:");
        printMatrix(B);
        System.out.println("\nResultado:");
        printMatrix(result);
    }

    // Generar una matriz dispersa aleatoria
    public static int[][] generateRandomSparseMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Aproximadamente el 50% de los valores serán ceros, creando una matriz dispersa
                if (Math.random() < 0.5) {
                    matrix[i][j] = (int) (Math.random() * 10);  // Valores aleatorios entre 0 y 9
                } else {
                    matrix[i][j] = 0;  // Elementos cero para representar dispersidad
                }
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
