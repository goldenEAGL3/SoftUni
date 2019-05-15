package StreamsFilesAndDirectories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        String basePath = "C:\\Java\\Java\\Java Advanced\\Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";
        FileInputStream in = null;
        FileOutputStream out = null;
        String pathIn = basePath + "\\input.txt";
        String pathOut = basePath + "\\myOutput.txt";
        String punctuation = ".,?!";
        try {
            in = new FileInputStream(pathIn);
            out = new FileOutputStream(pathOut);

            int byteData = in.read();
            while (byteData >= 0) {
                if (!punctuation.contains("" + (char) byteData)) {
                    out.write(byteData);
                }

                byteData = in.read();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

            //e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();

            }


        }
    }
}
