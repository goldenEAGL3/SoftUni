package MultidimensionalArrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrixRotation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String rotation = sc.nextLine();
        int index = 7;
        String degrees = "";

        while (Character.isDigit(rotation.charAt(index))) {
            degrees += rotation.charAt(index++);
        }
        String input = sc.nextLine();
        List<String> text = new ArrayList<>();
        int textLength = 0;

        while (!"END".equals(input)) {
            text.add(input);
            if (input.length() > textLength) {
                textLength = input.length();
            }
            input = sc.nextLine();
        }

        char[][] matrix = new char[text.size()][textLength];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (j >= text.get(i).length() && text.get(i).length() < matrix[i].length) {
                    matrix[i][j] = ' ';
                } else {
                    matrix[i][j] = text.get(i).charAt(j);
                }

            }
        }
        rotateMatrix(matrix, degrees, textLength);
    }

    private static void rotateMatrix(char[][] matrix, String rotationAngle, int textLength) {
        int rotationAngleSimple = rotationAngleSimplified(rotationAngle);

        switch (rotationAngleSimple) {
            case 90:
                for (int i = 0 ; i < textLength; i++) {
                    for (int j = matrix.length-1; j >= 0; j--) {
                        System.out.print(matrix[j][i]);
                    }
                    System.out.println();
                }
                break;

            case 180:
                for (int i = matrix.length-1 ; i >= 0 ; i--) {
                    for (int j = textLength-1; j >= 0; j--) {
                        System.out.print(matrix[i][j]);
                    }
                    System.out.println();
                }
                break;

            case 270:
                for (int i = textLength-1 ; i >= 0 ; i--) {
                    for (int j = 0; j < matrix.length; j++) {
                        System.out.print(matrix[j][i]);
                    }
                    System.out.println();
                }
                break;
            case 0:
            case 360:
                printMatrix(matrix);
                break;
        }
    }

    private static int rotationAngleSimplified(String degrees) {
        int rotate = Integer.parseInt(degrees);

        if (rotate > 360) {

            int times = rotate/ 360;
            return rotate - 360 * times;
        }
        return rotate;
    }

    private static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
