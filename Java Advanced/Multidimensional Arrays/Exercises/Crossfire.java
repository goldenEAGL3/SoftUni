package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class Crossfire {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] data = sc.nextLine().split("\\s+");
        String[][] myMatrix = fillMatrix(data);

        String input = sc.nextLine();
        while (!"Nuke it from orbit".equals(input)) {
            int[] crossLikeData = Arrays.stream(input
                    .split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int row = crossLikeData[0];
            int col = crossLikeData[1];
            int length = crossLikeData[2];
            crossfire(myMatrix, row, col, length);
            input = sc.nextLine();
        }
        printMatrix(myMatrix);
    }

    private static String[][] fillMatrix(String[] data) {
        int rows = Integer.parseInt(data[0]);
        int cols = Integer.parseInt(data[1]);

        String[][] matrix = new String[rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int result = i * matrix[i].length + j + 1;
                matrix[i][j] = "" + result;
            }
        }

        return matrix;
    }

    private static String[][] crossfire(String[][] matrix, int row, int col, int length) {
        if(row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length || length < 0) {
            return matrix;
        }
        int end = matrix[row].length;
        int start = 0;
        if (col - length >= 0) {
            start = col - length;
        }
        if (col + length + 1 < matrix[row].length) {
            end = col + length + 1;
        }

        for (int i = start; i < end; i++) {
            if (!matrix[row][i].equals("")) {
                matrix[row][i] = "";
            }
        }

        end = matrix.length;
        start = 0;

        if (row - length >= 0) {
            start = row - length;
        }
        if (row + length + 1 < matrix.length) {
            end = row + length + 1;
        }
        for (int i = start; i < end; i++) {
            if (!matrix[i][col].equals("")) {
                matrix[i][col] = "";
            }
        }
        rearrangeMatrix(matrix);
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

    private static String[][] rearrangeMatrix(String[][] myMatrix) {
        for (int i = 0; i < myMatrix.length; i++) {
            for (int j = 0; j < myMatrix[i].length; j++) {
                if (myMatrix[i][j].equals("")) {
                    String temp = "";
                    int a = j;
                    while (myMatrix[i][a].equals("")) {
                        if (a + 1 < myMatrix[i].length) {
                            a++;
                            if (!myMatrix[i][a].equals("")) {
                                temp = myMatrix[i][a];

                            }
                        } else {
                            break;
                        }
                    }
                    myMatrix[i][a] = "";
                    myMatrix[i][j] = temp;
                }
            }
        }
        for (int i = 0; i < myMatrix.length; i++) {
            String[] arr = myMatrix[i];
            int count = 0;
            for (String s : arr) {
                if (!s.equals("")) {
                    count++;
                    break;
                }
            }
            if (count == 0) {
                if (i + 1 < myMatrix.length) {
                    int nextRow = i + 1;
                    myMatrix[i] = myMatrix[nextRow];
                    myMatrix[nextRow] = arr;
                }
            }
        }
        return myMatrix;
    }
}
