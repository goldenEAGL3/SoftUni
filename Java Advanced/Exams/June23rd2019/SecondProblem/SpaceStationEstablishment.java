import java.util.Scanner;

public class SpaceStationEstablishment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sizeOfGalaxy = Integer.parseInt(sc.nextLine());

        String[][] galaxy = fillMatrix(sizeOfGalaxy, sc);
        int[] playerPositions = findSymbol(galaxy, "S");
        boolean wentIntoTheVoid = false;
        int sumOfStarValues = 0;
        while (!wentIntoTheVoid && sumOfStarValues < 50) {

            String direction = sc.nextLine();
            int playerRow = playerPositions[0];
            int playerCol = playerPositions[1];
            galaxy[playerRow][playerCol] = "-";
            switch (direction) {
                case "up":
                    playerRow -= 1;
                    break;

                case "down":
                    playerRow += 1;
                    break;

                case "left":
                    playerCol -= 1;
                    break;

                case "right":
                    playerCol += 1;
                    break;
            }
            if (validatePlayerPosition(playerRow, playerCol, galaxy)) {
                if (checkForStars(playerRow, playerCol, galaxy)) {
                    int value = Integer.parseInt(galaxy[playerRow][playerCol]);
                    sumOfStarValues += value;
                    move(playerRow, playerCol, galaxy, playerPositions);
                } else if (checkForBlackHole(playerRow, playerCol, galaxy)) {
                    teleport(playerRow, playerCol, galaxy, playerPositions);
                } else {
                    move(playerRow, playerCol, galaxy, playerPositions);
                }

            } else {
                wentIntoTheVoid = true;
            }
        }

        if (wentIntoTheVoid) {
            System.out.println("Bad news, the spaceship went to the void.");
        } else {
            System.out.println("Good news! Stephen succeeded in collecting enough star power!");
        }

        System.out.printf("Star power collected: %d%n", sumOfStarValues);
        printMatrix(galaxy);


    }

    private static void changePlayerPosition(int row, int col, int[] playerPosition) {
        playerPosition[0] = row;
        playerPosition[1] = col;
    }

    private static void printMatrix(String[][] galaxy) {
        for (String[] strings : galaxy) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    private static void teleport(int playerRow, int playerCol, String[][] galaxy, int[] playerPosition) {
        for (int i = 0; i < galaxy.length; i++) {
            for (int j = 0; j < galaxy[i].length; j++) {
                if (galaxy[i][j].equals("O") && i != playerRow && j != playerCol) {
                    galaxy[playerRow][playerCol] = "-";
                    playerRow = i;
                    playerCol = j;
                    galaxy[playerRow][playerCol] = "S";
                }
            }
        }
        changePlayerPosition(playerRow, playerCol, playerPosition);
    }


    private static boolean checkForBlackHole(int playerRow, int playerCol, String[][] galaxy) {
        return galaxy[playerRow][playerCol].contains("O");
    }

    private static boolean checkForStars(int playerRow, int playerCol, String[][] galaxy) {
        return galaxy[playerRow][playerCol].matches("\\d");
    }

    private static void move(int playerRow, int playerCol, String[][] galaxy, int[] playerPosition) {
        galaxy[playerRow][playerCol] = "S";
        changePlayerPosition(playerRow, playerCol, playerPosition);
    }

    private static boolean validatePlayerPosition(int playerRow, int playerCol, String[][] galaxy) {
        return playerRow >= 0 && playerRow < galaxy.length && playerCol >= 0 && playerCol < galaxy[playerRow].length;
    }

    private static String[][] fillMatrix(int rows, Scanner sc) {
        String[][] matrix = new String[rows][rows];
        for (int i = 0; i < matrix.length; i++) {
            String[] data = sc.nextLine().split("");
            matrix[i] = data;
        }
        return matrix;
    }

    private static int[] findSymbol(String[][] galaxy, String desiredSymbol) {

        int[] symbolPosition = new int[2];
        boolean symbolIsFound = false;
        for (int i = 0; i < galaxy.length; i++) {
            for (int j = 0; j < galaxy[i].length; j++) {
                if (galaxy[i][j].equals(desiredSymbol)) {
                    symbolIsFound = true;
                    symbolPosition[0] = i;
                    symbolPosition[1] = j;
                    break;
                }
                if (symbolIsFound) {
                    break;
                }
            }
        }
        return symbolPosition;
    }

}
