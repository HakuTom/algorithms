package com.company;

import java.util.Arrays;

/**
 * Created by dokgo on 18.11.16.
 */
public class Matrix {
    public static int [][] squareMatrixMultiply(int[][] A, int[][] B){
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] = C[i][j] + A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}
