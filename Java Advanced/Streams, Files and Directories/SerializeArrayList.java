package StreamsFilesAndDirectories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializeArrayList {
    public static void main(String[] args) throws ClassNotFoundException {
        List<Double> list = new ArrayList<>(){{
            add(3.14);
            add(10.14);
            add(20.14);
            add(30.14);
        }};
        String basePath = "C:\\Java\\Java\\Java Advanced\\" +
                "Streams and Files\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources\\" +
                "04. Java-Advanced-Files-and-Streams-Exercises-Resources";

        String pathOut = basePath + "\\list.ser";
        try {
            FileOutputStream outputStream = new FileOutputStream(pathOut);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();

            FileInputStream inputStream = new FileInputStream(pathOut);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<Double> newList = (List<Double>) objectInputStream.readObject();
            for (Double aDouble : newList) {
                System.out.println(aDouble);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
