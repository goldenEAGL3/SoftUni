package StreamsFilesAndDirectories;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SortLines {
    public static void main(String[] args) {
        String basePath = "C:\\Java\\Java\\Java Advanced\\Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";
        String pathIn = basePath + "\\6input.txt";
        String pathOut = basePath + "\\myOutput.txt";

       Path inputPath = Paths.get(pathIn);
       Path outputPath = Paths.get(pathOut);
        try {
            List<String> allFiles = Files.readAllLines(inputPath);
            Collections.sort(allFiles);
            Files.write(outputPath, allFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
