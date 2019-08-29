package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumSum3x3 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String[] data = sc.nextLine().split("\\s+");

        int[][] myMatrix = readMatrix(data);
        int[][] matrixWinner = new int[3][3];
        int[][] matrixCurrent = new int[3][3];
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < myMatrix.length - 2; i++) {
            for (int j = 0; j < myMatrix[i].length - 2; j++) {
                matrixCurrent = matrix3X3(myMatrix, i, j);
                int sum = getMaxSum(matrixCurrent);
                if (sum > maxSum) {
                    maxSum = sum;
                    matrixWinner = matrixCurrent;
                }
            }
        }
        System.out.println("Sum = " + maxSum);
        printMatrix(matrixWinner);
    }

    private static int[][] readMatrix(String[] data) {
        int rows = Integer.parseInt(data[0]);
        int cols = Integer.parseInt(data[1]);

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            int[] arr = Arrays.stream(sc.nextLine()
                    .split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = arr;
        }
        return matrix;
    }

    private static int getMaxSum(int[][] myMatrix) {
        int sum = 0;
        for (int i = 0; i < myMatrix.length; i++) {
            for (int j = 0; j < myMatrix[i].length; j++) {
                sum += myMatrix[i][j];
            }
        }
        return sum;
    }

    private static int[][] matrix3X3(int[][] myMatrix, int i, int j) {
        int[][] subMatrix = new int[3][3];

        for (int z = 0; z < subMatrix.length; z++) {
            for (int k = 0; k < subMatrix.length; k++) {
                subMatrix[z][k] = myMatrix[z + i][k + j];
            }
        }
        return subMatrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
