package SetsAndMaps;

import com.sun.source.tree.Tree;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ProductShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<String, LinkedHashMap<String, Double>> myShops = new TreeMap<>();
        String input = sc.nextLine();
        while (!"Revision".equals(input)) {
            String[] data = input.split(",\\s+");
            String shop = data[0];
            String product = data[1];
            double price = Double.parseDouble(data[2]);
            myShops.putIfAbsent(shop, new LinkedHashMap<>());
            myShops.get(shop).put(product, price);
            input = sc.nextLine();

        }
        for (Map.Entry<String, LinkedHashMap<String, Double>> outerLoop : myShops.entrySet()) {
            System.out.printf("%s->%n", outerLoop.getKey());
            for (Map.Entry<String, Double> innerLoop : outerLoop.getValue().entrySet()) {
                System.out.printf("Product: %s, Price: %.1f%n", innerLoop.getKey(), innerLoop.getValue());
            }
        }
    }
}
