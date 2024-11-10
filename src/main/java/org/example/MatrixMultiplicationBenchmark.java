package org.example;
import org.example.sparse.SparseMatrix;
import org.example.multiplication.BasicMatrixMultiplication;
import org.example.multiplication.LoopUnrollingMatrixMultiplication;
import org.example.multiplication.StrassenMatrixMultiplication;

public class MatrixMultiplicationBenchmark {

    public static void main(String[] args) {
        int[] sizes = {128, 256, 512, 1024};
        double[] sparsityLevels = {0.25, 0.5, 0.75, 0.9};

        for (int size : sizes) {
            for (double sparsity : sparsityLevels) {
                SparseMatrix A = generateSparseMatrix(size, sparsity);
                SparseMatrix B = generateSparseMatrix(size, sparsity);

                System.out.println("\nMatrix size: " + size + "x" + size + " with sparsity level: " + sparsity);

                testMatrixMultiplication("Basic", A, B);
                testMatrixMultiplication("LoopUnrolling", A, B);
                testMatrixMultiplication("Strassen", A, B);
            }
        }
    }

    private static void testMatrixMultiplication(String type, SparseMatrix A, SparseMatrix B) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.nanoTime();

        SparseMatrix result;
        switch (type) {
            case "Basic":
                result = BasicMatrixMultiplication.multiply(A, B);
                break;
            case "LoopUnrolling":
                result = LoopUnrollingMatrixMultiplication.multiply(A, B);
                break;
            case "Strassen":
                result = StrassenMatrixMultiplication.multiply(A, B);
                break;
            default:
                throw new IllegalArgumentException("Invalid multiplication type.");
        }

        long endTime = System.nanoTime();
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        long executionTimeMs = (endTime - startTime) / 1_000_000;
        double memoryUsageMb = (memoryAfter - memoryBefore) / (1024.0 * 1024.0);

        System.out.println(type + " - Execution time: " + executionTimeMs + " ms");
        System.out.println(type + " - Memory usage: " + String.format("%.2f", memoryUsageMb) + " MB");
    }

    private static SparseMatrix generateSparseMatrix(int size, double sparsity) {
        SparseMatrix matrix = new SparseMatrix(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Math.random() > sparsity) {
                    matrix.set(i, j, Math.random() * 10);
                }
            }
        }
        return matrix;
    }
}
