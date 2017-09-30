package lab2;

public class FirstTask extends Thread {

    private int[] vectorA = new int[Work.N];
    private int[] vectorB = new int[Work.N];
    private int[] vectorC = new int[Work.N];

    private int[][] matrixA = new int[Work.N][Work.N];
    private int[][] matrixD = new int[Work.N][Work.N];

    private int[] resultVector = new int[Work.N];

    @Override
    public void run() {
        inputData();

        int[] firstUnitVector = multVectors(vectorA, vectorB);
        int[] secondUnitVector = multVectors(vectorC, multIntegerWithVector(vectorB, multMatrix(matrixA, matrixD)));

        resultVector = addVectors(firstUnitVector, secondUnitVector);
        displayResult(resultVector);
    }

    private void displayResult(int[] resultVector) {
        for (int aResultVector : resultVector) {
            System.out.print(aResultVector + " ");
        }
    }

    private int[] addVectors(int[] firstUnitVector, int[] secondUnitVector) {
        int[] tmp = new int[firstUnitVector.length];
        for (int i = 0; i < firstUnitVector.length; i++) {
            tmp[i] = firstUnitVector[i] + secondUnitVector[i];
        }
        return tmp;
    }

    private void inputData() {
        randomVector(vectorA);
        randomVector(vectorB);
        randomVector(vectorC);
        randomMatrix(matrixA);
        randomMatrix(matrixD);
    }

    private void randomVector(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = (int) (Math.random() * 10);
        }
    }

    private void randomMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
    }

    private int[] multVectors(int[] firstVector, int[] secondVector) {
        int[] resultSum = new int[firstVector.length];

        for (int i = 0; i < firstVector.length; i++) {
            resultSum[i] = firstVector[i] * secondVector[i];
        }
        return resultSum;
    }

    private int multMatrix(int[][] firstMatrix, int[][] secondMatrix) {
        int resultSum = 1;
        int tmpMult;

        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix.length; j++) {
                tmpMult = firstMatrix[i][j] * secondMatrix[i][j];
                resultSum += tmpMult;
            }
        }

        return resultSum;
    }

    private int multInteger(int first, int second) {
        return first * second;
    }

    private int[] multIntegerWithVector(int[] vector, int value) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = vector[i] * value;
        }

        return vector;
    }

}
