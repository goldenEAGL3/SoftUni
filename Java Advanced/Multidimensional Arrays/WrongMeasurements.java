package MultidimensionalArrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WrongMeasurements {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int rows = Integer.parseInt(sc.nextLine());

        String[][] myMatrix = readMatrix(rows);
        String[] data = sc.nextLine().split("\\s+");
        correctMatrix(myMatrix, data);
        printMatrix(myMatrix);
    }

    private static String[][] readMatrix(int rows) {
        String[][] matrix = new String[rows][];
        for (int i = 0; i < matrix.length; i++) {
            String[] arr = sc.nextLine().split("\\s+");
            matrix[i] = new String[arr.length];
            for (int j = 0; j < arr.length; j++) {
                matrix[i][j] = arr[j];
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

    private static void correctMatrix(String[][] matrix, String[] data) {
        int i = Integer.parseInt(data[0]);
        int j = Integer.parseInt(data[1]);
        String valueToBeReplaced = matrix[i][j];
        int left, right, up, down;
        List<Integer> listOfToBeReplaced = new ArrayList<>();

        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[i].length; cols++) {
                if (matrix[rows][cols].equals(valueToBeReplaced)) {
                    int sum = 0;
                    if (cols - 1 >= 0) {
                        left = cols - 1;
                        if (isDigit(matrix, rows, left) && !matrix[rows][left].equals(valueToBeReplaced)) {
                            sum += Integer.parseInt(matrix[rows][left]);
                        }
                    }
                    if (cols + 1 < matrix[rows].length) {
                        right = cols + 1;
                        if (isDigit(matrix, rows, right) && !matrix[rows][right].equals(valueToBeReplaced)) {
                            sum += Integer.parseInt(matrix[rows][right]);
                        }
                    }
                    if (rows - 1 >= 0) {
                        up = rows - 1;
                        if (isDigit(matrix, up, cols) && !matrix[up][cols].equals(valueToBeReplaced)) {
                            sum += Integer.parseInt(matrix[up][cols]);
                        }
                    }
                    if (rows + 1 < matrix.length) {
                        down = rows + 1;
                        if (isDigit(matrix, down, cols) && !matrix[down][cols].equals(valueToBeReplaced)) {
                            sum += Integer.parseInt(matrix[down][cols]);
                        }
                    }
                    listOfToBeReplaced.add(sum);
                }
            }
        }
        int index = 0;
        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[rows].length; cols++) {
                if (matrix[rows][cols].equals(valueToBeReplaced)) {
                    String number = "" + listOfToBeReplaced.get(index++);
                    matrix[rows][cols] = number;

                }

            }
        }

    }

    private static boolean isDigit(String[][] matrix, int rows, int cols) {
        String input = matrix[rows][cols];
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
