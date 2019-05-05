package MultidimensionalArrays;

import java.util.*;

public class ValidQueen {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        char[][] myMatrix = readMatrix();
        int[] coordinatesOfRealQueen;
        coordinatesOfRealQueen = realQueen(myMatrix);
        System.out.println(coordinatesOfRealQueen[0] + " " + coordinatesOfRealQueen[1]);
    }

    private static char[][] readMatrix() {
        char[][] matrix = new char[8][8];
        for (int i = 0; i < matrix.length; i++) {
            String[] arr = sc.nextLine().split("\\s+");
            for (int z = 0; z < matrix[i].length; z++) {
                matrix[i][z] = arr[z].charAt(0);
            }
        }
        return matrix;
    }

    private static int[] realQueen(char[][] matrix) {
        int[] coordinatesOfTheQueen = new int[2];
        List<String> myQueens = new ArrayList<>();
        List<String> forbiddenPositions = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 'q') {
                    String help = "" + i + j;
                    myQueens.add(help);
                    forbiddenPositionsOfQueens(myQueens, index, forbiddenPositions);
                    index++;
                }
            }
        }
        for (int i = 0; i < myQueens.size(); i++) {
            String data = myQueens.get(i);
            if(forbiddenPositions.contains(data)) {
                myQueens.remove(i);
                i--;
            }
        }
        String theQueen = myQueens.get(0);
        coordinatesOfTheQueen[0] = Integer.parseInt("" + theQueen.charAt(0));
        coordinatesOfTheQueen[1] = Integer.parseInt("" + theQueen.charAt(1));

        return coordinatesOfTheQueen;
    }

    private static void forbiddenPositionsOfQueens(List<String> myQueens, int index, List<String> forbiddenPositions) {
        int i = 0, j = 0;
        for (int coordinates = index; coordinates < myQueens.size(); coordinates++) {
            String coordinatesConcatenated = myQueens.get(coordinates);
            i = Integer.parseInt("" + coordinatesConcatenated.charAt(0));
            j = Integer.parseInt("" + coordinatesConcatenated.charAt(1));
        }
        forbiddenX(forbiddenPositions, i, j);
        forbiddenY(forbiddenPositions, i, j);
        forbiddenAntiDiagonal(forbiddenPositions, i, j);
        forbiddenDiagonal(forbiddenPositions, i, j);

    }

    private static void forbiddenDiagonal(List<String> forbiddenPositions, int i, int j) {
        int x = i;
        int y = j;
        while(x != 0) {
            x--;
            y--;
            String coordinates = "" + x + y;
            if(!forbiddenPositions.contains(coordinates)) {
                forbiddenPositions.add(coordinates);
            }

        }

        while(j != 8) {
            i++;
            j++;
            String coordinates = "" + i + j;
            if(!forbiddenPositions.contains(coordinates)) {
                forbiddenPositions.add(coordinates);
            }
        }
    }

    private static void forbiddenAntiDiagonal(List<String> forbiddenPositions, int i, int j) {
        int x = i;
        int y = j;
        while(x != 0) {
            x--;
            y++;
            String coordinates = "" + x + y;
            if(!forbiddenPositions.contains(coordinates)) {
                forbiddenPositions.add(coordinates);
            }

        }

        while(j != 0) {
            i++;
            j--;
            String coordinates = "" + i + j;
            if(!forbiddenPositions.contains(coordinates)) {
                forbiddenPositions.add(coordinates);
            }
        }
    }

    private static void forbiddenX(List<String> forbiddenPositions, int i, int j) {
        int startJ = j;
        for (int x = j; x < 8; x++) {
            String coordinates = "" + i + x;
            if (!forbiddenPositions.contains(coordinates) && x != j) {
                forbiddenPositions.add(coordinates);
            }
            if (x == j - 1) {
                break;
            }
            if (x == 7 && startJ != 0) {
                x = -1;
            }
        }
    }

    private static void forbiddenY(List<String> forbiddenPositions, int i, int j) {
        int startI = i;
        for (int x = i; x < 8; x++) {
            String coordinates = "" + x + j;
            if (!forbiddenPositions.contains(coordinates) && x != i) {
                forbiddenPositions.add(coordinates);
            }
            if (x == i - 1) {
                break;
            }
            if (x == 7 && startI != 0) {
                x = -1;
            }
        }
    }
}
