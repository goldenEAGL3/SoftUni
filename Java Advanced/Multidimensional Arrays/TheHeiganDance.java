package MultidimensionalArrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheHeiganDance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] heiganDance = new int[15][15];
        int[] rowColOfPlayer = new int[2];
        rowColOfPlayer[0] = 7;
        rowColOfPlayer[1] = 7;
        int rowOfPlayer = rowColOfPlayer[0];
        int colOfPlayer = rowColOfPlayer[1];
        double healthPoints = 18500;
        double heiganHealthPoints = 3000000;
        boolean bossIsKilled = false;
        boolean playerIsKilled = false;
        double damageDealtEveryTurn = Double.parseDouble(sc.nextLine());
        int countCloud = 0;
        String killedBy = "";

        while (!bossIsKilled) {
            heiganHealthPoints -= damageDealtEveryTurn;
            if (heiganHealthPoints <= 0) {
                bossIsKilled = true;
            }
            if (countCloud > 0) {
                healthPoints -= 3500;
                countCloud--;
                if (healthPoints <= 0) {
                    playerIsKilled = true;
                    killedBy = "Plague Cloud";
                    break;
                }
            }
            if (!bossIsKilled) {
                String[] spell = sc.nextLine().split("\\s+");
                String typeOfSpell = spell[0];
                int row = Integer.parseInt(spell[1]);
                int col = Integer.parseInt(spell[2]);
                damagedCells(row, col, heiganDance);

                if (heiganDance[rowOfPlayer][colOfPlayer] == 1) {
                    playerMovement(heiganDance, rowColOfPlayer);
                    if (rowOfPlayer == rowColOfPlayer[0] && colOfPlayer == rowColOfPlayer[1]) {

                        switch (typeOfSpell) {
                            case "Cloud":
                                healthPoints -= 3500;
                                countCloud++;
                                break;

                            case "Eruption":
                                healthPoints -= 6000;
                                break;
                        }
                    } else {
                        rowOfPlayer = rowColOfPlayer[0];
                        colOfPlayer = rowColOfPlayer[1];
                    }

                }
                if (healthPoints <= 0) {
                    playerIsKilled = true;
                    killedBy = spell[0];
                    break;
                }
                removeDamagedCells(row, col, heiganDance);

            }

        }
        if (bossIsKilled) {
            System.out.println("Heigan: Defeated!");
        } else {
            System.out.printf("Heigan: %.2f%n", heiganHealthPoints);
        }

        if (playerIsKilled) {
            if ("Cloud".equals(killedBy)) {
                killedBy = "Plague Cloud";
            }
            System.out.printf("Player: Killed by %s%n", killedBy);
        } else {
            System.out.printf("Player: %.0f%n", healthPoints);
        }
        System.out.printf("Final position: %d, %d", rowOfPlayer, colOfPlayer);
    }


    private static int[][] damagedCells(int row, int col, int[][] matrix) {

        int startRow = row - 1;
        int startCol = col - 1;
        int endRow = startRow + 3;
        int endCol = startCol + 3;
        for (startRow = row - 1; startRow < endRow; startRow++) {
            for (startCol = col - 1; startCol < endCol; startCol++) {
                if (startRow >= 0 && startRow < matrix.length && startCol >= 0 && startCol < matrix[startRow].length) {
                    matrix[startRow][startCol] = 1;
                }

            }
        }
        return matrix;
    }

    private static int[][] removeDamagedCells(int row, int col, int[][] matrix) {

        int startRow = row - 1;
        int startCol = col - 1;
        int endRow = startRow + 3;
        int endCol = startCol + 3;
        for (startRow = row - 1; startRow < endRow; startRow++) {
            for (startCol = col - 1; startCol < endCol; startCol++) {
                if (startRow >= 0 && startRow < matrix.length && startCol >= 0 && startCol < matrix[startRow].length) {
                    matrix[startRow][startCol] = 0;
                }

            }
        }
        return matrix;
    }

    private static int[] playerMovement(int[][] matrix, int[] rowColPlayer) {
//        int rowOfPlayer = rowColPlayer[0];
//        int colOfPlayer = rowColPlayer[1];

        if (rowColPlayer[0] - 1 >= 0 && matrix[rowColPlayer[0] - 1][rowColPlayer[1]] == 0) {
            rowColPlayer[0] -= 1;
            return rowColPlayer;
        } else if (rowColPlayer[1] + 1 < matrix[rowColPlayer[1]].length && matrix[rowColPlayer[0]][rowColPlayer[1] + 1] == 0) {
            rowColPlayer[1] += 1;
            return rowColPlayer;
        } else if (rowColPlayer[0] + 1 < matrix.length && matrix[rowColPlayer[0] + 1][rowColPlayer[1]] == 0) {
            rowColPlayer[0] += 1;
            return rowColPlayer;
        } else if (rowColPlayer[1] - 1 >= 0 && matrix[rowColPlayer[0]][rowColPlayer[1] - 1] == 0) {
            rowColPlayer[1] -= 1;
            return rowColPlayer;
        }
        return rowColPlayer;
    }

}
