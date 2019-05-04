package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class DiagonalDifference {
        static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int squareMatrixRows = Integer.parseInt(sc.nextLine());
        int[][] myMatrix = new int[squareMatrixRows][squareMatrixRows];
        myMatrix = readMatrix(squareMatrixRows);
        System.out.println(diagonalDifference(myMatrix));
    }
    
    private static int[][] readMatrix(int rows) {
        int[][] matrix = new int[rows][rows];
        for (int i = 0; i < matrix.length; i++) {
            int[] arr = Arrays.stream(sc.nextLine()
                    .split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = arr;
        }
        return matrix;
    }
    private static int diagonalDifference(int[][] matrix) {
        int difference = 0;
        int sumMainDiagonal = 0;
        int sumAntiDiagonal = 0;
        for (int i = 0; i < matrix.length; i++) {
            sumMainDiagonal+=matrix[i][i];
        }

        for (int i = 0; i < matrix.length; i++) {
            sumAntiDiagonal+= matrix[matrix.length - 1 - i][i];
        }
        return Math.abs(sumAntiDiagonal - sumMainDiagonal);
    }
    
}
