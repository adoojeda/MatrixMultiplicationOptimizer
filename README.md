# Matrix Multiplication Optimizer

This project focuses on optimizing matrix multiplication by implementing different matrix multiplication algorithms and benchmarking to evaluate their performance. Specifically, it includes classical matrix multiplication, **Loop Unrolling** optimization, and the **Strassen** algorithm for matrix multiplication.

## Project Structure

The project structure is as follows:

1. **`src/main/java/org/example/sparse/`**:
   - **`SparseMatrix.java`**: This class implements basic operations on sparse matrices. Sparse matrices are stored efficiently by only representing non-zero elements, saving memory space.

2. **`src/main/java/org/example/multiplication/`**:
   - **`BasicMatrixMultiplication.java`**: Implements the basic matrix multiplication, which is the traditional method for multiplying two matrices.
   - **`LoopUnrollingMatrixMultiplication.java`**: Implements matrix multiplication using the **Loop Unrolling** optimization technique, which reduces the number of iteration cycles by unrolling the loops.
   - **`StrassenMatrixMultiplication.java`**: Implements the **Strassen algorithm**, which is a more efficient matrix multiplication algorithm that reduces the computational complexity compared to the traditional algorithm.

3. **`src/main/java/org/example/MatrixMultiplicationBenchmark.java`**:
   - This file contains the classes and methods required to run **benchmarking** on the implemented algorithms. It compares the performance of different matrix multiplication techniques and generates statistics on their efficiency.
