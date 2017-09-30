package lab2;

public class SecondTask extends Thread {
    private int[][] matrixG = new int[Work.N][Work.N];
    private int[][] matrixL = new int[Work.N][Work.N];

    @Override
    public void run() {
        randomMatrix(matrixG);
        randomMatrix(matrixL);

        int result = searchMin(multMatrix(matrixG, matrixL));
        System.out.println("\n" + result);
    }

    private void randomMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = (int) (Math.random() * 100) + 1;
            }
        }
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

    private int searchMin(int[][] matrix) {
        int value = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] < value)
                    value = matrix[i][j];
            }
        }

        return value;
    }
}
