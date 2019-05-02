package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class SumMatrixElements {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        String[] rowsCols = sc.nextLine().split(",\\s+");
        int rows = Integer.parseInt(rowsCols[0]);
        int cols = Integer.parseInt(rowsCols[1]);

        int[][] myMatrix = readMatrix(rows, cols);
        System.out.printf("%d%n%d%n", rows, cols);
        System.out.println(sumOfAllElements(myMatrix));

    }

    private static int[][] readMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            int[] data = Arrays.stream(sc.nextLine()
                    .split(",\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = data;
        }
        return matrix;
    }
    private static int sumOfAllElements(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum+=matrix[i][j];
            }
        }
        return sum;

    }

}
