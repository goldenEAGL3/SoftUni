package StreamsFilesAndDirectories;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;

public class NestedFolders {
    public static void main(String[] args) {
        String basePath = "C:\\Java\\Java\\Java Advanced\\Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";
        String pathIn = basePath + "\\Files-and-Streams";
        String pathOut = basePath + "\\myOutput.txt";

        File folderToRead = new File(pathIn);
        PrintWriter writer = null;
        int countFolders = 0;
        ArrayDeque<File> foldersToTraverse = new ArrayDeque<>(Arrays.asList(folderToRead));

        try {
            writer = new PrintWriter(new FileWriter(pathOut));
            while(!foldersToTraverse.isEmpty()) {
                File currentFile = foldersToTraverse.poll();
                if(currentFile.isDirectory()) {
                    countFolders++;
                    foldersToTraverse.addAll(Arrays.asList(currentFile.listFiles()));
                    writer.printf("%s%n", currentFile.getName());
                }
            }
            writer.println(countFolders + " folders");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}
