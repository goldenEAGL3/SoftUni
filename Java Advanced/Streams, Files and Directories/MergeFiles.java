package StreamsFilesAndDirectories;

import javax.annotation.processing.Filer;
import java.io.*;

public class MergeFiles {
    public static void main(String[] args) throws IOException {
        String basePath = "C:\\Java\\Java\\Java Advanced\\" +
                "Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources";
        String pathFile1 = basePath + "\\inputOne.txt";
        String pathFile2 = basePath + "\\inputTwo.txt";
        String pathOut = basePath + "\\myOutput.txt";

        BufferedReader reader = null;
        PrintWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(pathFile1));
            writer = new PrintWriter(new FileWriter(pathOut));
            String line = reader.readLine();
            while(line != null) {
                writer.println(line);
                line = reader.readLine();
            }
            reader = new BufferedReader(new FileReader(pathFile2));
            line = reader.readLine();
            while(line != null) {
                writer.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader !=  null) {
                reader.close();
            }
            if(writer != null) {
                writer.close();
            }
        }
    }
}
