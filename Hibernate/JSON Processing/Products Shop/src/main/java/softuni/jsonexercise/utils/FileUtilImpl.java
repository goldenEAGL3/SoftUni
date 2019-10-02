package softuni.jsonexercise.utils;

import softuni.jsonexercise.utils.interfaces.FileUtil;

import java.io.*;

public class FileUtilImpl implements FileUtil {

    @Override
    public String getContent(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
