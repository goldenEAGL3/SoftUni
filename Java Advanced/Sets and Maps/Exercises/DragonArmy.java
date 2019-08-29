package SetsAndMaps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DragonArmy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LinkedHashMap<String, TreeMap<String, LinkedHashMap<String, Integer>>> dragonArmy = new LinkedHashMap<>();
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().split("\\s+");
            int health = 0;
            int armor = 0;
            int damage = 0;
            int defaultHealth = 250;
            int defaultArmor = 10;
            int defaultDamage = 45;

            if (input[2].equals("null")) {
                damage = defaultDamage;
            } else {
                damage = Integer.parseInt(input[2]);
            }

            if (input[3].equals("null")) {
                health = defaultHealth;
            } else {
                health = Integer.parseInt(input[3]);
            }

            if (input[4].equals("null")) {
                armor = defaultArmor;
            } else {
                armor = Integer.parseInt(input[4]);
            }
            String color = input[0];
            String dragonName = input[1];

            dragonArmy.putIfAbsent(color, new TreeMap<>());
            dragonArmy.get(color).putIfAbsent(dragonName, new LinkedHashMap<>() {{
                put("damage", 0);
                put("health", 0);
                put("armor", 0);
            }});
            dragonArmy.get(color).get(dragonName).put("damage", damage);
            dragonArmy.get(color).get(dragonName).put("health", health);
            dragonArmy.get(color).get(dragonName).put("armor", armor);

        }
        for (Map.Entry<String, TreeMap<String, LinkedHashMap<String, Integer>>> dragonType : dragonArmy.entrySet()) {
            double damage = 0, armor = 0, health = 0;
            for (Map.Entry<String, LinkedHashMap<String, Integer>> dragonName : dragonType.getValue().entrySet()) {
                for (Map.Entry<String, Integer> stats : dragonName.getValue().entrySet()) {
                    switch ((stats.getKey())) {
                        case "damage":
                            damage += stats.getValue();
                            break;

                        case "health":
                            health += stats.getValue();
                            break;

                        case "armor":
                            armor += stats.getValue();
                            break;
                    }
                }
            }
            damage /= dragonType.getValue().size();
            health /= dragonType.getValue().size();
            armor /= dragonType.getValue().size();
            System.out.printf("%s::(%.2f/%.2f/%.2f)%n", dragonType.getKey(), damage, health, armor);

            for (Map.Entry<String, LinkedHashMap<String, Integer>> dragonName : dragonType.getValue().entrySet()) {
                for (Map.Entry<String, Integer> stats : dragonName.getValue().entrySet()) {
                    switch ((stats.getKey())) {
                        case "damage":
                            damage = stats.getValue();
                            break;

                        case "health":
                            health = stats.getValue();
                            break;

                        case "armor":
                            armor = stats.getValue();
                            break;
                    }
                }
                System.out.printf("-%s -> damage: %.0f, health: %.0f, armor: %.0f%n", dragonName.getKey(), damage, health, armor);
            }
        }
    }
}
