package SetsAndMaps;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class CountSymbols {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();
        TreeMap<Character, Integer> occurences = new TreeMap<>();

        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            occurences.putIfAbsent(a, 0);
            occurences.put(a, occurences.get(a) + 1);
        }
        for (Map.Entry<Character, Integer> kvp : occurences.entrySet()) {
            System.out.printf("%c: %d time/s%n", kvp.getKey(), kvp.getValue());
        }
    }
}
