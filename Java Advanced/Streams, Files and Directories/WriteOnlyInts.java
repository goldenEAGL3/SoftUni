package StreamsFilesAndDirectories;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteOnlyInts {
    public static void main(String[] args) throws IOException {
        String basePath = "C:\\Java\\Java\\Java Advanced\\Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";
        Scanner scanner = null;
        FileWriter writer = null;
        String pathIn = basePath + "\\input.txt";
        String pathOut = basePath + "\\myOutput.txt";

        try {
            scanner = new Scanner(new FileInputStream(pathIn));
            writer = new FileWriter(pathOut);
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    writer.write(String.valueOf(scanner.nextInt()));
                    writer.write(String.format("%n")); // BufferedWriter has writer.newLine() method! PrintWriter has similar method!
                }
                scanner.next();
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }

}
