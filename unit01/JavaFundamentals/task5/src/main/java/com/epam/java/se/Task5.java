package com.epam.java.se;

/**
 * Created by chris on 20.02.2017.
 */
public class Task5 {

    private final int size;

    private final int[][] matrix;

    public Task5(int size) {
        this.size = size;
        this.matrix = generateMatrix(size);
    }

    private int[][] generateMatrix(int size) {
        final int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || i == size - j - 1){
                    matrix[i][j] = 1;
                }
            }
        }
        return matrix;
    }

    public void printMatrix() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getMatrix() {
        return matrix;
    }

}
