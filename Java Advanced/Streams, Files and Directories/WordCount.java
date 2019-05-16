package StreamsFilesAndDirectories;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class WordCount {
    public static void main(String[] args) throws IOException {
        String basePath = "C:\\Java\\Java\\Java Advanced\\" +
                "Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources";
        String pathToWords = basePath + "\\words.txt";
        String pathToText = basePath + "\\text.txt";
        String pathOut = basePath + "\\results.txt";

        BufferedReader reader = null;
        PrintWriter writer = null;
        LinkedHashMap<String, Integer> words = new LinkedHashMap<>();
        try {
            reader = new BufferedReader(new FileReader(pathToWords));

            String[] wordsToCompare = reader.readLine().split("\\s");
            for (String s : wordsToCompare) {
                words.put(s, 0);
            }
            reader = new BufferedReader(new FileReader(pathToText));
            writer = new PrintWriter(new FileWriter(pathOut));
            String line = reader.readLine();
            while(line != null) {
                String[] newWords = line.split("\\s");
                for (String newWord : newWords) {
                    if (words.containsKey(newWord)) {
                        words.put(newWord, words.get(newWord) + 1);
                    }
                }
                line = reader.readLine();

            }
            words = words.entrySet()
                    .stream()
                    .sorted((a,b) -> Integer.compare(b.getValue(), a.getValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a,b) -> a, LinkedHashMap::new));

            for (Map.Entry<String, Integer> kvp : words.entrySet()) {
                writer.println(String.format("%s - %d", kvp.getKey(), kvp.getValue()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if(writer != null) {
                writer.close();
            }
        }

    }
}
