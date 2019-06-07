package Exams.February24th2019;

import java.util.Scanner;

public class TronRacers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sizeOfSquareMatrix = Integer.parseInt(sc.nextLine());
        int[] firstPlayerPositions = new int[2];
        int[] secPlayerPositions = new int[2];
        String[][] track = fillMatrix(sizeOfSquareMatrix, sc, firstPlayerPositions, secPlayerPositions);
        boolean someoneIsDead = false;

        while (!someoneIsDead) {
            String[] commands = sc.nextLine().split("\\s+");
            movement(commands[0], track, firstPlayerPositions);
            someoneIsDead = isSomeoneDead(track, firstPlayerPositions, secPlayerPositions);
            if (someoneIsDead) {
                break;
            }
            movement(commands[1], track, secPlayerPositions);
            someoneIsDead = isSomeoneDead(track, firstPlayerPositions, secPlayerPositions);
        }
        printMatrix(track);
    }

    private static boolean isSomeoneDead(String[][] track, int[] firstPlayerPosition, int[] secPlayerPosition) {
        if (track[firstPlayerPosition[0]][firstPlayerPosition[1]].equals("x")) {
            return true;
        } else if (track[secPlayerPosition[0]][secPlayerPosition[1]].equals("x")) {
            return true;
        }
        return false;
    }

    private static void movement(String commandForPlayer, String[][] track, int[] playerPositions) {
        int rowOfPlayer = playerPositions[0];
        int colOfPlayer = playerPositions[1];
        String symbol = track[rowOfPlayer][colOfPlayer];
        switch (commandForPlayer) {
            case "up":
                if (rowOfPlayer - 1 >= 0) {
                    rowOfPlayer -= 1;
                } else {
                    rowOfPlayer = track.length - 1;
                }
                break;

            case "down":
                if (rowOfPlayer + 1 < track.length) {
                    rowOfPlayer += 1;
                } else {
                    rowOfPlayer = 0;
                }
                break;

            case "left":
                if (colOfPlayer - 1 >= 0) {
                    colOfPlayer -= 1;
                } else {
                    colOfPlayer = track.length - 1;
                }
                break;

            case "right":
                if (colOfPlayer + 1 < track.length) {
                    colOfPlayer += 1;
                } else {
                    colOfPlayer = 0;
                }
                break;
        }
        if (track[rowOfPlayer][colOfPlayer].equals("*")) {
            track[rowOfPlayer][colOfPlayer] = symbol;
        } else {
            track[rowOfPlayer][colOfPlayer] = "x";
        }
        playerPositions[0] = rowOfPlayer;
        playerPositions[1] = colOfPlayer;

    }

    private static String[][] fillMatrix(int size, Scanner sc, int[] firstPlayerPositions, int[] secPlayerPosition) {
        String[][] matrix = new String[size][size];
        boolean foundFirst = false;
        boolean foundSec = false;
        for (int i = 0; i < matrix.length; i++) {
            String[] row = sc.nextLine().split("");
            for (int j = 0; j < row.length; j++) {
                if ("f".equals(row[j])) {
                    firstPlayerPositions[0] = i;
                    firstPlayerPositions[1] = j;
                    foundFirst = true;
                    break;
                } else if ("s".equals(row[j])) {
                    secPlayerPosition[0] = i;
                    secPlayerPosition[1] = j;
                    foundSec = true;
                    break;
                }
                if (foundFirst && foundSec) {
                    break;
                }
            }
            matrix[i] = row;
        }
        return matrix;
    }
    private static void printMatrix(String[][] track) {
        for (String[] strings : track) {
            for (int j = 0; j < track.length; j++) {
                System.out.print(strings[j]);
            }
            System.out.println();
        }
    }


}
