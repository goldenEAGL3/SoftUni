package StreamsFilesAndDirectories;

import java.io.*;

public class CopyPicture {
    public static void main(String[] args) throws IOException {
        String basePath = "C:\\Java\\Java\\Java Advanced\\" +
                "Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources";
        String pathIn = basePath + "\\image.jpeg";
        String pathOut = basePath + "\\copy-of-image.jpeg";
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
             inputStream = new FileInputStream(pathIn);
             outputStream = new FileOutputStream(pathOut);
             byte[] buffer = inputStream.readAllBytes();
             outputStream.write(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null) {
                inputStream.close();
            }
            if(outputStream != null) {
                outputStream.close();
            }
        }
    }
}
