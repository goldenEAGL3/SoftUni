package SetsAndMaps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MinerTask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, Integer> minerTask = new LinkedHashMap<>();

        String input = sc.nextLine();
        while(!"stop".equals(input)) {
            int resource = Integer.parseInt(sc.nextLine());
            minerTask.putIfAbsent(input, 0);
            minerTask.put(input, minerTask.get(input) + resource);

            input = sc.nextLine();

        }

        for (Map.Entry<String, Integer> kvp : minerTask.entrySet()) {
            System.out.printf("%s -> %d%n", kvp.getKey(), kvp.getValue());
        }
    }
}
