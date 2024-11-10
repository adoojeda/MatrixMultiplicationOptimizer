package org.example.sparse;

import java.util.HashMap;
import java.util.Map;

public class SparseMatrix {
    private int rows;
    private int cols;
    private Map<String, Double> values;

    public SparseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.values = new HashMap<>();
    }

    public int size() {
        return rows;
    }

    public void set(int row, int col, double value) {
        if (value != 0) {
            String key = row + "," + col;
            values.put(key, value);
        }
    }

    public double get(int row, int col) {
        String key = row + "," + col;
        return values.getOrDefault(key, 0.0);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double[][] toDenseMatrix() {
        double[][] denseMatrix = new double[rows][cols];
        for (Map.Entry<String, Double> entry : values.entrySet()) {
            String[] indices = entry.getKey().split(",");
            int row = Integer.parseInt(indices[0]);
            int col = Integer.parseInt(indices[1]);
            denseMatrix[row][col] = entry.getValue();
        }
        return denseMatrix;
    }
}
