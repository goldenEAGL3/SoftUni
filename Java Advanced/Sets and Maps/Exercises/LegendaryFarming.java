package SetsAndMaps;

import java.util.Scanner;
import java.util.TreeMap;

public class LegendaryFarming {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeMap<String, Integer> keyResources = new TreeMap<>();

        keyResources.put("motes", 0);
        keyResources.put("fragments", 0);
        keyResources.put("shards", 0);

        TreeMap<String, Integer> junk = new TreeMap<>();
        String winMaterial = "";


        while ("".equals(winMaterial)) {
            String[] input = sc.nextLine().split("\\s+");
            for (int i = 0; i < input.length; i += 2) {
                int resource = Integer.parseInt(input[i]);
                String material = input[i + 1].toLowerCase();

                switch (material) {
                    case "motes":
                    case "shards":
                    case "fragments":
                        keyResources.put(material, keyResources.get(material) + resource);
                        if (keyResources.get(material) >= 250) {
                            keyResources.put(material, keyResources.get(material) - 250);
                            winMaterial = material;
                        }
                        break;
                    default:
                        junk.putIfAbsent(material, 0);
                        junk.put(material, junk.get(material) + resource);
                        break;
                }
                if (!"".equals(winMaterial)) {
                    break;
                }
            }
        }
        switch (winMaterial) {
            case "motes":
                System.out.println("Dragonwrath obtained!");
                break;

            case "shards":
                System.out.println("Shadowmourne obtained!");
                break;

            case "fragments":
                System.out.println("Valanyr obtained!");
                break;
        }
        keyResources.entrySet()
                .stream()
                .sorted((material1, material2) -> Integer.compare(material2.getValue(), material1.getValue()))
                .forEach(material -> {
                    System.out.printf("%s: %d%n", material.getKey(), material.getValue());
                });

        junk.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));

    }
}
