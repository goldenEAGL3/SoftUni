package StreamsFilesAndDirectories;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GetFolderSize {
    public static void main(String[] args) throws IOException {
        String basePath = "C:\\Java\\Java\\Java Advanced\\" +
                "Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources";
        String pathIn = basePath + "\\Exercises Resources";
        String pathOut = basePath + "\\myOutput.txt";
        FileWriter writer = null;
        File folder = new File(pathIn);
        try {
            writer = new FileWriter(pathOut);
            if(folder.exists()) {
                if(folder.isDirectory()) {
                    File[] files = folder.listFiles();
                    int sum = 0;
                    for (File file : files) {
                        sum+= file.length();

                    }
                    writer.write(String.format("Folder size: %d", sum));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null) {
                writer.close();
            }
        }


    }
}
