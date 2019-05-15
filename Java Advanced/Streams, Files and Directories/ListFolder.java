package StreamsFilesAndDirectories;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ListFolder {
    public static void main(String[] args) {
        String basePath = "C:\\Java\\Java\\Java Advanced\\Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";
        String pathIn = basePath + "\\Files-and-Streams";
        String pathOut = basePath + "\\myOutput.txt";

        File folderToRead = new File(pathIn);
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(pathOut));
            File[] files = folderToRead.listFiles();
            for (File file : files) {
                if(!file.isDirectory()) {
            writer.printf("%s: [%d]%n", file.getName(), file.length());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null) {
                writer.close();
            }
        }

    }
}
