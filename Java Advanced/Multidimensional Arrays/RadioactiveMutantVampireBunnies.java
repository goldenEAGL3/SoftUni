package MultidimensionalArrays;

import java.util.Scanner;

public class RadioactiveMutantVampireBunnies {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String[] matrixSize = sc.nextLine().split("\\s+");
        int rows = Integer.parseInt(matrixSize[0]);
        int cols = Integer.parseInt(matrixSize[1]);
        String[][] lair = readMatrix(rows, cols);
        String commands = sc.nextLine();

        int rowOfPlayer = 0;
        int colOfPlayer = 0;
        boolean playerFound = false;
        for (int i = 0; i < lair.length; i++) {
            for (int j = 0; j < lair[i].length; j++) {
                if (lair[i][j].equals("P")) {
                    rowOfPlayer = i;
                    colOfPlayer = j;
                    playerFound = true;
                    break;
                }

            }
            if(playerFound) {
                break;
            }
        }
        boolean playerIsAlive = true;
        boolean heGotOut = false;
        while (true) {
            for (int i = 0; i < commands.length(); i++) {
                char action = commands.charAt(i);
                switch (action) {
                    case 'L':
                        if (colOfPlayer - 1 >= 0) {
                            if ("B".equals(lair[rowOfPlayer][colOfPlayer - 1])) {
                                colOfPlayer--;
                                playerIsAlive = false;

                            } else {
                                lair[rowOfPlayer][colOfPlayer--] = ".";
                                lair[rowOfPlayer][colOfPlayer] = "P";

                            }
                        } else {
                            lair[rowOfPlayer][colOfPlayer] = ".";
                            heGotOut = true;
                        }
                        bunnyMovement(lair);
                        bunnySpreading(lair);
                        if(lair[rowOfPlayer][colOfPlayer].equals("B") && playerIsAlive) {
                            playerIsAlive = false;

                        }
                        break;

                    case 'R':
                        if (colOfPlayer + 1 < lair[rowOfPlayer].length) {
                            if ("B".equals(lair[rowOfPlayer][colOfPlayer + 1])) {
                                colOfPlayer++;
                                playerIsAlive = false;

                            } else {
                                lair[rowOfPlayer][colOfPlayer++] = ".";
                                lair[rowOfPlayer][colOfPlayer] = "P";

                            }
                        } else {
                            lair[rowOfPlayer][colOfPlayer] = ".";
                            heGotOut = true;
                        }
                        bunnyMovement(lair);
                        bunnySpreading(lair);
                        if(lair[rowOfPlayer][colOfPlayer].equals("B") && playerIsAlive) {
                            playerIsAlive = false;

                        }
                        break;

                    case 'U':
                        if (rowOfPlayer - 1 >= 0) {
                            if ("B".equals(lair[rowOfPlayer - 1][colOfPlayer])) {
                                rowOfPlayer--;
                                playerIsAlive = false;

                            } else {
                                lair[rowOfPlayer--][colOfPlayer] = ".";
                                lair[rowOfPlayer][colOfPlayer] = "P";

                            }
                        } else {
                            lair[rowOfPlayer][colOfPlayer] = ".";
                            heGotOut = true;
                        }
                        bunnyMovement(lair);
                        bunnySpreading(lair);
                        if(lair[rowOfPlayer][colOfPlayer].equals("B") && playerIsAlive) {
                            playerIsAlive = false;

                        }
                        break;

                    case 'D':
                        if (rowOfPlayer + 1 < lair.length) {
                            if ("B".equals(lair[rowOfPlayer + 1][colOfPlayer])) {
                                rowOfPlayer++;
                                playerIsAlive = false;

                            } else {
                                lair[rowOfPlayer++][colOfPlayer] = ".";
                                lair[rowOfPlayer][colOfPlayer] = "P";

                            }
                        } else {
                            lair[rowOfPlayer][colOfPlayer] = ".";
                            heGotOut = true;
                        }
                        bunnyMovement(lair);
                        bunnySpreading(lair);
                        if(lair[rowOfPlayer][colOfPlayer].equals("B") && playerIsAlive) {
                            playerIsAlive = false;

                        }
                        break;
                }
                if(heGotOut || !playerIsAlive) {
                    break;
                }

            }
            if(heGotOut || !playerIsAlive) {
                break;
            }

        }
        printMatrix(lair);
        if(heGotOut) {
            System.out.printf("won: %d %d", rowOfPlayer, colOfPlayer);
        } else {
            System.out.printf("dead: %d %d", rowOfPlayer, colOfPlayer);
        }

    }

    private static String[][] readMatrix(int rows, int cols) {
        String[][] matrix = new String[rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            String arr = sc.nextLine();
            for (int j = 0; j < arr.length(); j++) {
                matrix[i][j] = "" + arr.charAt(j);
            }

        }
        return matrix;

    }
    private static String[][] bunnyMovement(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if("B".equals(matrix[i][j])) {
                    if(i-1 >= 0 && !matrix[i-1][j].equals("B")) {
                        matrix[i-1][j] = "b";
                    }
                    if(i+1 < matrix.length && !matrix[i+1][j].equals("B")) {
                        matrix[i+1][j] = "b";
                    }
                    if(j-1 >= 0 && !matrix[i][j-1].equals("B")) {
                        matrix[i][j-1] = "b";
                    }
                    if(j+1 < matrix[i].length && !matrix[i][j+1].equals("B")) {
                        matrix[i][j+1] = "b";
                    }
                }
            }
        }
        return matrix;
    }
    private static String[][] bunnySpreading(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j].equals("b")) {
                    matrix[i][j] = matrix[i][j].toUpperCase();
                }
            }
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
