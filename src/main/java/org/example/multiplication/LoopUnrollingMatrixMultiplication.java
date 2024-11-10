package org.example.multiplication;

import org.example.sparse.SparseMatrix;

public class LoopUnrollingMatrixMultiplication {

    public static SparseMatrix multiply(SparseMatrix A, SparseMatrix B) {
        int n = A.size();
        if (A.getCols() != B.getRows()) {
            throw new IllegalArgumentException("The number of columns in A must be equal than B.");
        }

        SparseMatrix C = new SparseMatrix(n, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sum = 0.0;
                // Loop unrolling: procesar 4 elementos a la vez (ejemplo)
                for (int k = 0; k < n; k += 4) {
                    sum += A.get(i, k) * B.get(k, j);
                    if (k + 1 < n) sum += A.get(i, k + 1) * B.get(k + 1, j);
                    if (k + 2 < n) sum += A.get(i, k + 2) * B.get(k + 2, j);
                    if (k + 3 < n) sum += A.get(i, k + 3) * B.get(k + 3, j);
                }
                if (sum != 0) {
                    C.set(i, j, sum);
                }
            }
        }
        return C;
    }
}
