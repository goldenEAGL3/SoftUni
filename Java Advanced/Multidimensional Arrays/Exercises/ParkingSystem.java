package MultidimensionalArrays;

import java.util.Scanner;

public class ParkingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] data = sc.nextLine().split("\\s+");
        int rows = Integer.parseInt(data[0]);
        int cols = Integer.parseInt(data[1]);
        int[][] parkingLot = new int[rows][cols];

        String input = sc.nextLine();
        while (!"stop".equals(input)) {
            data = input.split("\\s+");
            int entryRow = Integer.parseInt(data[0]);
            int desiredRow = Integer.parseInt(data[1]);
            int desiredCol = Integer.parseInt(data[2]);

            int lengthCloserToExit = 0;
            int lengthFartherToExit = 0;
            int wholeLength = 0;
            int[] freeSpace = new int[2];
            boolean fullRow = false;
            if (parkingLot[desiredRow][desiredCol] == 1) {
                for (int i = desiredCol - 1; i > 0; i--) {
                    if (parkingLot[desiredRow][i] == 0) {
                        lengthCloserToExit = desiredCol - i;
                        freeSpace[0] = i;
                        break;
                    }
                }

                for (int i = desiredCol + 1; i < parkingLot[desiredRow].length; i++) {
                    if (parkingLot[desiredRow][i] == 0) {
                        lengthFartherToExit = i - desiredCol;
                        freeSpace[1] = i;
                        break;
                    }
                }
                String directionWhenSlotTaken = "";
                if (lengthCloserToExit == 0 && lengthFartherToExit == 0) {
                    fullRow = true;

                } else if (lengthCloserToExit != 0 && lengthFartherToExit == 0 || (lengthCloserToExit != 0 && lengthCloserToExit <= lengthFartherToExit)) {
                    directionWhenSlotTaken = "left";
                    parkingLot[desiredRow][freeSpace[0]] = 1;
                    wholeLength = lengthOfMoving(entryRow, desiredRow, desiredCol, lengthCloserToExit, directionWhenSlotTaken);
                } else {
                    directionWhenSlotTaken = "right";
                    parkingLot[desiredRow][freeSpace[1]] = 1;
                    wholeLength = lengthOfMoving(entryRow, desiredRow, desiredCol, lengthFartherToExit, directionWhenSlotTaken);
                }

            } else {
                parkingLot[desiredRow][desiredCol] = 1;
                wholeLength = desiredCol + 1 + Math.abs(desiredRow - entryRow);

            }
            if (fullRow) {
                System.out.printf("Row %d full%n", desiredRow);
            } else {
                System.out.println(wholeLength);
            }
            input = sc.nextLine();
        }
    }

    private static int lengthOfMoving(int entryRow, int desiredRow, int desiredCol, int length, String direction) {
        int wholeLength = 0;
        switch (direction) {
            case "left":
                if (desiredRow == entryRow) {
                    wholeLength = desiredCol + 1 - length;
                } else {
                    wholeLength = desiredCol + 1 + Math.abs(desiredRow - entryRow) - length;
                }
                break;

            case "right":
                wholeLength = desiredCol + 1 + Math.abs(desiredRow - entryRow) + length;
                break;

        }

        return wholeLength;
    }
}
