package Exams.SampleExam;

import java.util.Scanner;

public class Sneaking {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int numOfRows = Integer.parseInt(sc.nextLine());
        String[][] gameField = fillMatrix(numOfRows);
        String playerMovement = sc.nextLine();

        int[] playerLocation = findPlayerLocation(gameField);

        boolean heroIsAlive = true;
        boolean bossIsKilled = false;
        int counter = 0;
        while (true) {
            enemyMovement(gameField);
            heroIsAlive = checkIfHeroIsAlive(gameField, playerLocation[0], playerLocation[1]);
            if (!heroIsAlive) {
                break;
            }
            heroMovement(gameField, playerMovement, counter, playerLocation);
            bossIsKilled = checkIfNikoladzeIsKilled(gameField, playerLocation[0]);
            if ( bossIsKilled) {
                break;
            }
            counter++;
        }

        if (bossIsKilled) {
            System.out.println("Nikoladze killed!");
            printMatrix(gameField);
        } else {
            System.out.printf("Sam died at %d, %d%n", playerLocation[0], playerLocation[1]);
            printMatrix(gameField);
        }

    }

    private static void printMatrix(String[][] gameField) {
        for (String[] strings : gameField) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    private static boolean checkIfNikoladzeIsKilled(String[][] gameField, int rowOfPlayer) {
        for (int i = 0; i < gameField[rowOfPlayer].length; i++) {
            if(gameField[rowOfPlayer][i].equals("N")) {
                gameField[rowOfPlayer][i] = "X";
                return true;
            }
        }
        return false;
    }

    private static void heroMovement(String[][] gameField, String playerMovement, int counter, int[] playerLocation) {
        String move = "" + playerMovement.charAt(counter);
        int rowOfPlayer = playerLocation[0];
        int colOfPlayer = playerLocation[1];
        gameField[rowOfPlayer][colOfPlayer] = ".";
        switch (move) {
            case "U":
                rowOfPlayer -= 1;
                break;

            case "R":
                colOfPlayer += 1;
                break;

            case "D":
                rowOfPlayer += 1;
                break;

            case "L":
                colOfPlayer -= 1;
                break;
        }
        gameField[rowOfPlayer][colOfPlayer] = "S";
        playerLocation[0] = rowOfPlayer;
        playerLocation[1] = colOfPlayer;
    }

    private static int[] findPlayerLocation(String[][] gameField) {
        boolean playerFound = false;
        int[] playerLocation = new int[2];
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j].equals("S")) {
                    playerFound = true;
                    playerLocation[0] = i;
                    playerLocation[1] = j;
                    break;
                }
            }
            if (playerFound) {
                break;
            }
        }
        return playerLocation;
    }

    private static boolean checkIfHeroIsAlive(String[][] gameField, int rowOfPlayer, int colOfPlayer) {
        for (int i = colOfPlayer + 1; i < gameField[rowOfPlayer].length; i++) {
            if (gameField[rowOfPlayer][i].equals("d")) {
                gameField[rowOfPlayer][colOfPlayer] = "X";
                return false;
            }
        }
        for (int i = 0; i < colOfPlayer; i++) {
            if (gameField[rowOfPlayer][i].equals("b")) {
                gameField[rowOfPlayer][colOfPlayer] = "X";
                return false;
            }
        }
        return true;
    }

    private static void enemyMovement(String[][] gameField) {
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                if (gameField[i][j].equals("b")) {
                    if (j + 1 > gameField[i].length - 1) {
                        gameField[i][j] = "d";
                        break;
                    } else {
                        gameField[i][j] = ".";
                        gameField[i][j + 1] = "b";
                        break;
                    }
                } else if (gameField[i][j].equals("d")) {
                    if (j - 1 < 0) {
                        gameField[i][j] = "b";
                        break;
                    } else {
                        gameField[i][j] = ".";
                        gameField[i][j - 1] = "d";
                        break;
                    }
                }
            }
        }
    }

    private static String[][] fillMatrix(int numOfRows) {
        String[][] matrix = new String[numOfRows][];
        for (int i = 0; i < matrix.length; i++) {
            String[] arr = sc.nextLine().split("");
            matrix[i] = arr;
        }
        return matrix;
    }
}
