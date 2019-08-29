package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumSumOf2x2Submatrix {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        String sizes = sc.nextLine();
        int[][] matrix = readMatrix(sizes);
        int[][] lastMatrix = new int[2][2];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i < matrix.length - 1 && j < matrix[i].length-1) {
                    int sum = sumMatrix(matrix, i, j);
                    if (sum > max) {
                        max = sum;
                        lastMatrix = subMatrix(i, j, matrix);
                    }
                }
            }
        }
        printMatrix(lastMatrix);
        System.out.println(max);
    }

    private static int[][] readMatrix(String sizes) {
        int rows = Integer.parseInt(sizes.split(",\\s+")[0]);
        int cols = Integer.parseInt(sizes.split(",\\s+")[1]);
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            int[] arr = Arrays.stream(sc.nextLine()
                    .split(",\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = arr;
        }

        return matrix;
    }

    private static int sumMatrix(int[][] matrix, int rows, int cols) {

        int sum = matrix[rows][cols] + matrix[rows][cols + 1] + matrix[rows + 1][cols] + matrix[rows + 1][cols + 1];

        return sum;
    }

    private static int[][] subMatrix(int i, int j, int[][] matrix) {
        int[][] bestSubMatrix = new int[2][2];
        bestSubMatrix[0][0] = matrix[i][j];
        bestSubMatrix[0][1] = matrix[i][j+1];
        bestSubMatrix[1][0] = matrix[i+1][j];
        bestSubMatrix[1][1] = matrix[i+1][j+1];
        return bestSubMatrix;

    }
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


}
