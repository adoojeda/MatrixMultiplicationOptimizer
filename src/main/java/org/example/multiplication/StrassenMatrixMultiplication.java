package org.example.multiplication;

import org.example.sparse.SparseMatrix;

public class StrassenMatrixMultiplication {

    public static SparseMatrix multiply(SparseMatrix A, SparseMatrix B) {
        int n = A.size();
        if (A.getCols() != B.getRows()) {
            throw new IllegalArgumentException("The number of columns in A must be equal than B.");
        }

        if (n <= 2) {
            return BasicMatrixMultiplication.multiply(A, B);
        }

        int half = n / 2;

        SparseMatrix A11 = new SparseMatrix(half, half);
        SparseMatrix A12 = new SparseMatrix(half, half);
        SparseMatrix A21 = new SparseMatrix(half, half);
        SparseMatrix A22 = new SparseMatrix(half, half);

        SparseMatrix B11 = new SparseMatrix(half, half);
        SparseMatrix B12 = new SparseMatrix(half, half);
        SparseMatrix B21 = new SparseMatrix(half, half);
        SparseMatrix B22 = new SparseMatrix(half, half);

        divideMatrix(A, A11, A12, A21, A22);
        divideMatrix(B, B11, B12, B21, B22);

        SparseMatrix M1 = multiply(A11, subtract(B12, B22));
        SparseMatrix M2 = multiply(add(A11, A12), B22);
        SparseMatrix M3 = multiply(add(A21, A22), B11);
        SparseMatrix M4 = multiply(A22, subtract(B21, B11));
        SparseMatrix M5 = multiply(add(A11, A22), add(B11, B22));
        SparseMatrix M6 = multiply(subtract(A12, A22), add(B21, B22));
        SparseMatrix M7 = multiply(subtract(A11, A21), add(B11, B12));

        SparseMatrix C11 = add(subtract(add(M5, M4), M2), M6);
        SparseMatrix C12 = add(M1, M2);
        SparseMatrix C21 = add(M3, M4);
        SparseMatrix C22 = add(subtract(add(M1, M5), M3), M7);

        SparseMatrix C = new SparseMatrix(n, n);
        combineMatrix(C, C11, C12, C21, C22);

        return C;
    }

    private static void divideMatrix(SparseMatrix A, SparseMatrix A11, SparseMatrix A12, SparseMatrix A21, SparseMatrix A22) {
        int half = A.size() / 2;
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                A11.set(i, j, A.get(i, j));
                A12.set(i, j + half, A.get(i, j + half));
                A21.set(i + half, j, A.get(i + half, j));
                A22.set(i + half, j + half, A.get(i + half, j + half));
            }
        }
    }

    private static void combineMatrix(SparseMatrix C, SparseMatrix C11, SparseMatrix C12, SparseMatrix C21, SparseMatrix C22) {
        int half = C.size() / 2;
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                C.set(i, j, C11.get(i, j));
                C.set(i, j + half, C12.get(i, j));
                C.set(i + half, j, C21.get(i, j));
                C.set(i + half, j + half, C22.get(i, j));
            }
        }
    }

    private static SparseMatrix add(SparseMatrix A, SparseMatrix B) {
        int n = A.size();
        SparseMatrix result = new SparseMatrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result.set(i, j, A.get(i, j) + B.get(i, j));
            }
        }
        return result;
    }

    private static SparseMatrix subtract(SparseMatrix A, SparseMatrix B) {
        int n = A.size();
        SparseMatrix result = new SparseMatrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result.set(i, j, A.get(i, j) - B.get(i, j));
            }
        }
        return result;
    }
}
