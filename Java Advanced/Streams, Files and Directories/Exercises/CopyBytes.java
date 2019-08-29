package StreamsFilesAndDirectories;

import java.io.*;

public class CopyBytes {
    public static void main(String[] args) {
        String basePath = "C:\\Java\\Java\\Java Advanced\\Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Lab-Resources";

        FileInputStream reader = null;
        FileOutputStream writer = null;
        String pathIn = basePath + "\\input.txt";
        String pathOut = basePath + "\\myOutput.txt";
        try {
            reader = new FileInputStream(pathIn);
            writer = new FileOutputStream(pathOut);

            int byteData = reader.read();
            while (byteData >= 0) {
                if (byteData == ' ' || byteData == '\n') {
                    writer.write(byteData);
                } else {
                    String number = "" + byteData;
                    for (int i = 0; i < number.length(); i++) {
                        writer.write(number.charAt(i));
                    }

                }
                byteData = reader.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
