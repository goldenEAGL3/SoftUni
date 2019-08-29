package MultidimensionalArrays;

import java.sql.SQLOutput;
import java.util.Scanner;

public class MatrixShuffling {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String[] data = sc.nextLine().split("\\s+");
        String[][] myMatrix = readMatrix(data);

        String[] input = sc.nextLine().split("\\s+");
        while (!"END".equals(input[0])) {
            int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
            try {
                row1 = Integer.parseInt(input[1]);
                col1 = Integer.parseInt(input[2]);
                row2 = Integer.parseInt(input[3]);
                col2 = Integer.parseInt(input[4]);

                if (input.length > 5) {
                    System.out.println("Invalid input!");
                } else {
                    swapRowCol(myMatrix, row1, col1, row2, col2);
                    printMatrix(myMatrix);
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
            input = sc.nextLine().split("\\s+");
        }

    }

    private static String[][] readMatrix(String[] data) {
        int rows = Integer.parseInt(data[0]);

        String[][] matrix = new String[rows][];
        for (int i = 0; i < matrix.length; i++) {
            String[] arr = sc.nextLine().split("\\s+");
            matrix[i] = arr;
        }
        return matrix;
    }

    private static String[][] swapRowCol(String[][] matrix, int row1, int col1, int row2, int col2) {
        String temp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row2][col2];
        matrix[row2][col2] = temp;
        return matrix;
    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
