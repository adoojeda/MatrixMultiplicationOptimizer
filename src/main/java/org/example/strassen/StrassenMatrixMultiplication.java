package org.example.strassen;

public class StrassenMatrixMultiplication {

    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;

        if (n == 1) {
            int[][] result = new int[1][1];
            result[0][0] = A[0][0] * B[0][0];
            return result;
        }

        int[][] A11 = new int[n / 2][n / 2];
        int[][] A12 = new int[n / 2][n / 2];
        int[][] A21 = new int[n / 2][n / 2];
        int[][] A22 = new int[n / 2][n / 2];

        int[][] B11 = new int[n / 2][n / 2];
        int[][] B12 = new int[n / 2][n / 2];
        int[][] B21 = new int[n / 2][n / 2];
        int[][] B22 = new int[n / 2][n / 2];


        split(A, A11, A12, A21, A22);
        split(B, B11, B12, B21, B22);


        int[][] P1 = strassenMultiply(A11, subtract(B12, B22));
        int[][] P2 = strassenMultiply(add(A11, A12), B22);
        int[][] P3 = strassenMultiply(add(A21, A22), B11);
        int[][] P4 = strassenMultiply(A22, subtract(B21, B11));
        int[][] P5 = strassenMultiply(add(A11, A22), add(B11, B22));
        int[][] P6 = strassenMultiply(subtract(A12, A22), add(B21, B22));
        int[][] P7 = strassenMultiply(subtract(A11, A21), add(B11, B12));


        int[][] C11 = add(subtract(add(P5, P4), P2), P6);
        int[][] C12 = add(P1, P2);
        int[][] C21 = add(P3, P4);
        int[][] C22 = subtract(subtract(add(P5, P1), P3), P7);


        int[][] result = new int[n][n];
        join(C11, C12, C21, C22, result);

        return result;
    }


    public static void split(int[][] A, int[][] A11, int[][] A12, int[][] A21, int[][] A22) {
        int n = A.length / 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + n];
                A21[i][j] = A[i + n][j];
                A22[i][j] = A[i + n][j + n];
            }
        }
    }


    public static void join(int[][] C11, int[][] C12, int[][] C21, int[][] C22, int[][] C) {
        int n = C11.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = C11[i][j];
                C[i][j + n] = C12[i][j];
                C[i + n][j] = C21[i][j];
                C[i + n][j + n] = C22[i][j];
            }
        }
    }


    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }


    public static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }
}
