package April14th.SecondGroup;

import java.util.*;

public class PracticeSessions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        Map<String, List<String>> myRoads = new LinkedHashMap<>();
        while (!"END".equals(input)) {
            String[] data = input.split("->");
            String road = data[1];
            String racer;
            switch (data[0]) {

                case "Add":
                    racer = data[2].trim();
                    myRoads.putIfAbsent(road, new ArrayList<>());
                    myRoads.get(road).add(racer);
                    break;

                case "Move":
                    racer = data[2].trim();
                    String nextRoad = data[3];
                    if (myRoads.get(road).contains(racer)) {
                        myRoads.get(road).remove(racer);
                        myRoads.get(nextRoad).add(racer);
                    }
                    break;

                case "Close":
                    myRoads.remove(road);
                    break;

            }
            input = sc.nextLine();
        }
        System.out.println("Practice sessions:");
        myRoads.entrySet()
                .stream()
                .sorted((racer1, racer2) -> {
                    int sort = Integer.compare(racer2.getValue().size(), racer1.getValue().size());
                    if (sort == 0) {
                        sort = racer1.getKey().compareTo(racer2.getKey());
                    }
                    return sort;
                }).forEach(racer -> {
            System.out.printf("%s%n", racer.getKey());
            for (String s : racer.getValue()) {
                System.out.printf("++%s%n", s);
            }
        });


    }
}
