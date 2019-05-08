package SetsAndMaps;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CountRealNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] arr = Arrays.stream(sc.nextLine()
                .split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();
        Map<Double, Integer> numbers = new LinkedHashMap<>();

        for (double v : arr) {
            numbers.putIfAbsent(v, 0);
            numbers.put(v, numbers.get(v) + 1);
        }

        for (Map.Entry<Double, Integer> kvp : numbers.entrySet()) {
            System.out.printf("%.1f -> %d%n",kvp.getKey(), kvp.getValue());
        }

    }
}
