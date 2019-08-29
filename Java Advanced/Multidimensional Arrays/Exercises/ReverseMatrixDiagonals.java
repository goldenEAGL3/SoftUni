package MultidimensionalArrays;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseMatrixDiagonals {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int[] matrixSize = Arrays.stream(sc.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[][] matrix = readMatrix(matrixSize);

        int i = matrix.length - 1;
        int j = matrix[0].length - 1;
        int iterations = i + j + 1;
        for (int z = 0; z < iterations; z++) {
            System.out.print(matrix[i][j] + " ");
            int currentJ = j;
            int currentI = i;
            while (currentI - 1 >= 0 && currentJ + 1 < matrix[i].length) {
                System.out.print(matrix[--currentI][++currentJ] + " ");
            }
            System.out.println();
            j--;
            if(j<0) {
                j = 0;
                i--;
            }
        }

    }

    private static int[][] readMatrix(int[] matrixSize) {
        int[][] matrix = new int[matrixSize[0]][matrixSize[1]];
        for (int i = 0; i < matrix.length; i++) {
            int[] arr = Arrays.stream(sc.nextLine()
                    .split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = arr;
        }
        return matrix;
    }
}
