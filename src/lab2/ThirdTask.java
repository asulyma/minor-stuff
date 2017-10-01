package lab2;

import java.util.Arrays;

public class ThirdTask extends Thread {
    private int[][] matrixS = new int[Work.N][Work.N];
    private int[][] matrixA = new int[Work.N][Work.N];
    private int[][] matrixB = new int[Work.N][Work.N];

    @Override
    public void run() {
        inputData();

        int[][] tempMatrix = addingMatrix(sortMatrix(matrixS), multMatrix(matrixA, matrixB));
        int result = searchMax(tempMatrix);
        System.out.println(result);
    }

    private void inputData() {
        randomMatrix(matrixS);
        randomMatrix(matrixA);
        randomMatrix(matrixB);
    }

    private void randomMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
    }

    private int[][] sortMatrix(int[][] matrix) {

        int[] flat = new int[Work.N * Work.N];

        int ctr = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                flat[ctr++] = matrix[row][col];
            }
        }

        Arrays.sort(flat);

        ctr = 0;
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 4; col++) {
                matrix[row][col] = flat[ctr++];
            }
        }


        return matrix;
    }

    private int[][] multMatrix(int[][] firstMatrix, int[][] secondMatrix) {
        int[][] resultMatrix = new int[Work.N][Work.N];
        int tmpMult;

        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix.length; j++) {
                tmpMult = firstMatrix[i][j] * secondMatrix[i][j];
                resultMatrix[i][j] = tmpMult;
            }
        }

        return resultMatrix;
    }

    private int[][] addingMatrix(int[][] firstMatrix, int[][] secondMatrix) {
        int[][] resultMatrix = new int[Work.N][Work.N];

        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix.length; j++) {
                resultMatrix[i][j] = firstMatrix[i][j] + secondMatrix[i][j];

            }
        }
        return resultMatrix;
    }

    private int searchMax(int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] > max)
                    max = matrix[i][j];
            }
        }
        return max;
    }
}
