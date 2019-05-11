package SetsAndMaps;

import java.lang.reflect.Array;
import java.util.*;

public class CitiesContinents {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, LinkedHashMap<String, List<String>>> citiesByContinents = new LinkedHashMap<>();

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().split("\\s+");
            String continent = data[0];
            String country = data[1];
            String city = data[2];

            citiesByContinents.putIfAbsent(continent, new LinkedHashMap<>());
            citiesByContinents.get(continent).putIfAbsent(country, new ArrayList<>());
            citiesByContinents.get(continent).get(country).add(city);
        }
        for (Map.Entry<String, LinkedHashMap<String, List<String>>> outerLoop : citiesByContinents.entrySet()) {
            System.out.printf("%s:%n", outerLoop.getKey());
            for (Map.Entry<String, List<String>> middleLoop : outerLoop.getValue().entrySet()) {
                System.out.printf("  %s -> ", middleLoop.getKey());
                System.out.print(String.join(", ", middleLoop.getValue()));
                System.out.println();
            }

        }
    }
}
