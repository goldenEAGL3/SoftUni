package goldeneagle.carsdealer.utils;


import goldeneagle.carsdealer.utils.interfaces.FileUtil;

import java.io.*;


public class FileUtilImpl implements FileUtil {

    private static final String path = "D:\\Java\\Hibernate\\JSON PROCESSING\\ProductShop\\src\\main\\resources\\files\\";

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

    @Override
    public void writeToFile(String content, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(FileUtilImpl.path + fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(content);
        printWriter.close();

    }
}
