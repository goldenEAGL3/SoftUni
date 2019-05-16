package StreamsFilesAndDirectories;

import java.io.*;
import java.util.Scanner;

public class ThirdLine {
    public static void main(String[] args) throws IOException {
        String basePath = "C:\\Java\\Java\\Java Advanced\\Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";
        BufferedReader reader = null;
        PrintWriter writer = null;
        String pathIn = basePath + "\\input.txt";
        String pathOut = basePath + "\\myOutput.txt";
        int row = 1;
        try {
            reader = new BufferedReader(new FileReader(pathIn));
            writer = new PrintWriter(new FileWriter(pathOut));

            String line = reader.readLine();
            while (line != null) {
                if (row % 3 == 0) {
                    writer.println(line);
                }
                line = reader.readLine();
                row++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        }

    }
}
