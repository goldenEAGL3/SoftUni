package SetsAndMaps;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LogsAggregator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        TreeMap<String, TreeMap<String, Integer>> userLogs = new TreeMap<>();


        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().split("\\s+");
            String ipAddress = data[0];
            String user = data[1];
            int duration = Integer.parseInt(data[2]);

            userLogs.putIfAbsent(user, new TreeMap<>());
            userLogs.get(user).putIfAbsent(ipAddress, 0);
            userLogs.get(user).put(ipAddress, userLogs.get(user).get(ipAddress) + duration);
        }

        for (Map.Entry<String, TreeMap<String, Integer>> outerLoop : userLogs.entrySet()) {
            int sumDuration = 0;
            for (Map.Entry<String, Integer> innerLoop : outerLoop.getValue().entrySet()) {
                sumDuration += innerLoop.getValue();
            }
            int count = 1;
            System.out.printf("%s: %d [", outerLoop.getKey(), sumDuration);
            for (Map.Entry<String, Integer> innerLoop : outerLoop.getValue().entrySet()) {
                if (count == outerLoop.getValue().size()) {
                    System.out.printf("%s]%n", innerLoop.getKey());
                } else {
                    System.out.printf("%s, ", innerLoop.getKey());
                }
                count++;
            }
        }
    }
}
