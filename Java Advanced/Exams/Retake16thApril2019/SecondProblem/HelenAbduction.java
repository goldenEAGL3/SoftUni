package Retake16April2019;

import java.util.Scanner;

public class HelenAbduction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int energy = sc.nextInt();
        int countOfRows = sc.nextInt();
        sc.nextLine();

        String[][] matrix = readMatrix(sc, countOfRows);
        int[] parisPositions = findPlayer(matrix);

        int parisRow = parisPositions[0];
        int parisCol = parisPositions[1];
        boolean helenIsAbducted = false;
        while (true) {
            String[] input = sc.nextLine().split("\\s+");
            int enemyRow = Integer.parseInt(input[1]);
            int enemyCol = Integer.parseInt(input[2]);


            spawnEnemy(enemyRow, enemyCol, matrix);
            String command = input[0];
            matrix[parisRow][parisCol] = "-";
            switch (command) {
                case "up":
                    if (isPositionValid(parisRow - 1, parisCol, matrix)) {
                        parisRow -= 1;
                    }
                    break;

                case "down":
                    if (isPositionValid(parisRow + 1, parisCol, matrix)) {
                        parisRow += 1;
                    }
                    break;

                case "left":
                    if (isPositionValid(parisRow, parisCol - 1, matrix)) {
                        parisCol -= 1;
                    }
                    break;

                case "right":
                    if (isPositionValid(parisRow, parisCol + 1, matrix)) {
                        parisCol += 1;
                    }
                    break;
            }
            energy -= 1;
            if (enemyOnNextPosition(parisRow, parisCol, matrix)) {
                energy -= 2;
                if (isParisDead(energy, matrix, parisRow, parisCol)) {
                    break;
                }
            }

            if (helenIsOnNextPosition(parisRow, parisCol, matrix)) {
                helenIsAbducted = true;
                matrix[parisRow][parisCol] = "-";
                break;
            }

            if (isParisDead(energy, matrix, parisRow, parisCol)) {
                break;
            }
        }
        if (helenIsAbducted) {
            System.out.println("Paris has successfully abducted Helen! Energy left: " + energy);
        } else {
            System.out.println(String.format("Paris died at %d;%d.", parisRow, parisCol));
        }
        printMatrix(matrix);
    }

    private static boolean isParisDead(int energy, String[][] matrix, int parisRow, int parisCol) {
        if (energy <= 0) {
            matrix[parisRow][parisCol] = "X";
            return true;
        }
        return false;
    }

    private static boolean helenIsOnNextPosition(int parisRow, int parisCol, String[][] matrix) {
        return matrix[parisRow][parisCol].equals("H");
    }

    private static boolean enemyOnNextPosition(int parisRow, int parisCol, String[][] matrix) {
        return matrix[parisRow][parisCol].equals("S");
    }

    private static boolean isPositionValid(int row, int col, String[][] matrix) {
        return (row >= 0 && row < matrix.length) && (col >= 0 && col < matrix[row].length);
    }

    private static void spawnEnemy(int enemyRow, int enemyCol, String[][] matrix) {
        matrix[enemyRow][enemyCol] = "S";
    }

    private static int[] findPlayer(String[][] matrix) {
        int[] rowCol = new int[2];
        boolean found = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("P")) {
                    rowCol[0] = i;
                    rowCol[1] = j;
                    found = true;
                    break;
                }

            }
            if (found) {
                break;
            }
        }
        return rowCol;
    }

    private static String[][] readMatrix(Scanner sc, int countOfRows) {
        String[][] matrix = new String[countOfRows][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = sc.nextLine().split("");
        }
        return matrix;
    }

    private static void printMatrix(String[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
