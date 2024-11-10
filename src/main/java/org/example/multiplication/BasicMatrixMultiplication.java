package org.example.multiplication;

import org.example.sparse.SparseMatrix;

public class BasicMatrixMultiplication {

    public static SparseMatrix multiply(SparseMatrix A, SparseMatrix B) {
        int n = A.size();
        if (A.getCols() != B.getRows()) {
            throw new IllegalArgumentException("The number of columns in A must be equal than B.");
        }

        SparseMatrix C = new SparseMatrix(n, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sum = 0.0;
                for (int k = 0; k < n; k++) {
                    sum += A.get(i, k) * B.get(k, j);
                }
                if (sum != 0) {
                    C.set(i, j, sum);
                }
            }
        }
        return C;
    }
}
