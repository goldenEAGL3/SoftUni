package MultidimensionalArrays;

import java.util.Scanner;

public class MatrixOfPalindromes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] data = sc.nextLine().split("\\s+");
        int rows = Integer.parseInt(data[0]);
        int cols = Integer.parseInt(data[1]);

        String[][] matrix = new String[rows][cols];
        matrix = fillMatrix(rows, cols);
        printMatrix(matrix);
    }
    private static String[][] fillMatrix(int rows, int cols) {
        String[][] matrix = new String[rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                char firstAndLast, middle;
                if(i+97 > 122) {
                    firstAndLast = (char)(i+97-122+96);
                } else {
                    firstAndLast = (char)(i+97);
                }
                if(i+j+97 > 122) {
                    middle = (char)(i+j+97-122+96);
                } else {
                    middle = (char)(j+i+97);
                }
                matrix[i][j] = "" + firstAndLast + middle + firstAndLast;

            }
        }
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
