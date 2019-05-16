package StreamsFilesAndDirectories;

import java.io.*;
import java.nio.Buffer;

public class SumLines {
    public static void main(String[] args) throws IOException {
        String basePath = "C:\\Java\\Java\\Java Advanced\\" +
                "Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources";
        String pathIn = basePath + "\\input.txt";
        String pathOut = basePath + "\\myOutput.txt";

        BufferedReader reader = null;
        PrintWriter writer = null;
        String vowels = "aeoui";
        String punctuation = "?!.,";

        try {
            reader = new BufferedReader(new FileReader(pathIn));
            writer = new PrintWriter(pathOut);

            int countVowels = 0;
            int countConsonant = 0;
            int countPunctuation = 0;
            String line = reader.readLine();
            while(line != null) {
                for (char c : line.toCharArray()) {
                    if(vowels.contains("" + c)) {
                        countVowels++;
                    } else if(punctuation.contains("" + c)) {
                        countPunctuation++;
                    } else if(c != ' ') {
                        countConsonant++;
                    }
                }

                line = reader.readLine();

            }
            writer.println(String.format("Vowels: %d", countVowels));
            writer.println(String.format("Consonants: %d", countConsonant));
            writer.println(String.format("Punctuation: %d", countPunctuation));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                reader.close();
            }
            if(writer != null) {
                writer.close();
            }
        }
    }
}
