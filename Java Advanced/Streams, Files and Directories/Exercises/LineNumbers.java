package StreamsFilesAndDirectories;

import java.io.*;
import java.util.Scanner;

public class LineNumbers {
    public static void main(String[] args) throws IOException {
        String basePath = "C:\\Java\\Java\\Java Advanced\\" +
                "Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources";
        String pathIn = basePath + "\\inputLineNumbers.txt";
        String pathOut = basePath + "\\myOutput.txt";

        Scanner scanner = null;
        PrintWriter writer = null;

        try {
            scanner = new Scanner(new FileInputStream(pathIn));
            writer = new PrintWriter(new FileWriter(pathOut));

            int countLines = 1;
            while(scanner.hasNext()) {
                String line = scanner.nextLine();
                writer.write(String.format("%d. %s%n", countLines, line));

                countLines++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(scanner != null) {
                scanner.close();
            }
            if(writer != null) {
                writer.close();
            }
        }
    }
}
