package SetsAndMaps;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class PeriodicTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        TreeSet<String> elements = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            String[] element = sc.nextLine().split("\\s+");

            elements.addAll(Arrays.asList(element));

        }
        for (String element : elements) {
            System.out.print(element + " ");
        }
    }
}
