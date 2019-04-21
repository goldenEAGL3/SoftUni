package December20th2018;

import java.util.*;
import java.util.regex.Pattern;

public class VaporWinterSale {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(", ");
        Map<String, Map<Double, List<String>>> myGames = new LinkedHashMap<>();
        for (String value : input) {
            if (!value.contains(":")) {
                String[] data = value.split("-");
                String gameName = data[0];
                double gamePrice = Double.parseDouble(data[1]);
                myGames.putIfAbsent(gameName, new LinkedHashMap<>());
                myGames.get(gameName).put(gamePrice, new ArrayList<>());

            } else {
                String[] data = value.split(":");
                String gameName = data[0];
                if (myGames.containsKey(gameName)) {
                    String gameDLC = data[1];
                    double gamePrice = 0;
                    for (Map.Entry<Double, List<String>> kvp : myGames.get(gameName).entrySet()) {
                        gamePrice = kvp.getKey();
                    }
                    List<String> helpList = new ArrayList<>(myGames.get(gameName).get(gamePrice));
                    myGames.get(gameName).remove(gamePrice);
                    gamePrice *= 1.20;
                    myGames.get(gameName).put(gamePrice, new ArrayList<>());
                    for (String s : helpList) {
                        myGames.get(gameName).get(gamePrice).add(s);
                    }
                    myGames.get(gameName).get(gamePrice).add(gameDLC);
                }

            }
        }

        for (Map.Entry<String, Map<Double, List<String>>> outerLoop : myGames.entrySet()) {

            for (Map.Entry<Double, List<String>> innerLoop : outerLoop.getValue().entrySet()) {
                double key = innerLoop.getKey();
                double newKey;
                if (innerLoop.getValue().size() == 0) {
                    newKey = key * 0.8;
                } else {
                    newKey = key * 0.5;
                }
                List<String> helpList = new ArrayList<>(innerLoop.getValue());
                outerLoop.getValue().remove(key);
                outerLoop.getValue().put(newKey, new ArrayList<>());
                for (String s : helpList) {
                    outerLoop.getValue().get(newKey).add(s);
                }

            }
        }
        myGames.entrySet()
                .stream()
                .filter(a -> !a.getValue().values().isEmpty())
                .sorted((a, b) -> {
                    double keyA = 0;
                    double keyB = 0;
                    for (Double aDouble : a.getValue().keySet()) {
                        keyA = aDouble;
                    }
                    for (Double bDouble : b.getValue().keySet()) {
                        keyB = bDouble;
                    }
                    return Double.compare(keyA, keyB);
                }).forEach(c -> {

            for (Map.Entry<Double, List<String>> kvp : c.getValue().entrySet()) {
                int count = 0;
                for (String s : kvp.getValue()) {

                    if (kvp.getValue().size() > 1) {
                        count++;
                        int numMultiply = kvp.getValue().size();
                        double price = 0;
                        if (count != 1) {
                            numMultiply--;
                        }
                        for (int i1 = 1; i1 < numMultiply; i1++) {
                            price = kvp.getKey() * 0.8;
                        }
                        if (count != kvp.getValue().size()) {
                            System.out.printf("%s - %s - %.2f%n", c.getKey(), s, price);
                        }
                    }
                    if(count == 0 || count == kvp.getValue().size()) {
                    System.out.printf("%s - %s - %.2f%n", c.getKey(), s, kvp.getKey());
                    }
                }
            }
        });
        myGames.entrySet()
                .stream()
                .filter(a -> {
                    int lenght = 0;
                    for (Map.Entry<Double, List<String>> kvp : a.getValue().entrySet()) {
                        lenght = kvp.getValue().size();
                    }
                    return lenght == 0;
                })
                .sorted((c, d) -> {
                    double keyA = 0;
                    double keyB = 0;
                    for (Double aDouble : c.getValue().keySet()) {
                        keyA = aDouble;
                    }
                    for (Double bDouble : d.getValue().keySet()) {
                        keyB = bDouble;
                    }
                    return Double.compare(keyB, keyA);
                }).forEach(e -> {

            for (Double keys : e.getValue().keySet()) {
                System.out.printf("%s - %.2f%n", e.getKey(), keys);
            }
        });
    }
}
