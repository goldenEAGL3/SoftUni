package MultidimensionalArrays;

import java.util.Scanner;

public class FillMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(",\\s+");
        int rows = Integer.parseInt(input[0]);
        String pattern = input[1];
        int[][] myMatrix = new int[rows][rows];
        if("A".equals(pattern)) {
            myMatrix = fillMatrixPatternA(rows);
        } else {
            myMatrix = fillMatrixPatternB(rows);
        }
        printMatrix(myMatrix);

    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static int[][] fillMatrixPatternA(int rows) {
        int[][] matrix = new int[rows][rows];
        int fill = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = fill++;
            }
        }
        return matrix;
    }
    private static int[][] fillMatrixPatternB(int rows) {
        int[][] matrix = new int[rows][rows];
        int fill = 1;
        for (int i = 0; i < matrix.length; i++) {
            if(i % 2 == 0) {
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i] = fill++;
                }
            } else {
                for (int j = matrix.length-1; j >= 0; j--) {
                    matrix[j][i] = fill++;
                }
            }

        }
        return matrix;
    }

}
