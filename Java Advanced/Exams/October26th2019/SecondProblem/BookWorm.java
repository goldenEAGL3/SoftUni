package Exam26October2019;

        import java.util.Scanner;

public class BookWorm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder word = new StringBuilder(sc.nextLine());
        int countOfRows = sc.nextInt();
        sc.nextLine();

        String[][] matrix = readMatrix(sc, countOfRows);
        int[] playerPosition = findPlayer(matrix);
        System.out.println();
        int row = playerPosition[0];
        int col = playerPosition[1];
        String[] input = sc.nextLine().split("\\s+");

        while (!input[0].equals("end")) {
            String command = input[0];

            switch (command) {
                case "up":
                    if (isPositionValid(row-1, col, matrix)) {
                        matrix[row][col] = "-";
                        row -= 1;
                        String letter = getLetter(row, col, matrix);
                        if (!letter.equals("-")) {
                            word.append(letter);
                        }
                    } else {
                        word.replace(word.length() -1, word.length(), "");
                    }

                    break;

                case "down":
                    if (isPositionValid(row+1, col, matrix)) {
                        matrix[row][col] = "-";
                        row += 1;
                        String letter = getLetter(row, col, matrix);
                        if (!letter.equals("-")) {
                            word.append(letter);
                        }
                    } else {
                        word.replace(word.length() -1, word.length(), "");
                    }

                    break;

                case "left":
                    if (isPositionValid(row, col-1, matrix)) {
                        matrix[row][col] = "-";
                        col -= 1;
                        String letter = getLetter(row, col, matrix);
                        if (!letter.equals("-")) {
                            word.append(letter);
                        }
                    } else {
                        word.replace(word.length() -1, word.length(), "");
                    }
                    break;

                case "right":
                    if (isPositionValid(row, col+1, matrix)) {
                        matrix[row][col] = "-";
                        col += 1;
                        String letter = getLetter(row, col, matrix);
                        if (!letter.equals("-")) {
                            word.append(letter);
                        }
                    } else {
                        word.replace(word.length() -1, word.length(), "");
                    }
                    break;
            }
            matrix[row][col] = "P";
            input = sc.nextLine().split("\\s+");
        }
        System.out.println(word.toString());
        printMatrix(matrix);
    }

    private static String getLetter(int row, int col, String[][] matrix) {
        String letter = "-";
        if (!matrix[row][col].equals("-")) {
            letter = matrix[row][col];
            matrix[row][col] = "-";
        }
        return letter;
    }

    private static boolean isPositionValid(int row, int col, String[][] matrix) {
        return (row >= 0 && row < matrix.length) && (col >= 0 && col < matrix[row].length);
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
