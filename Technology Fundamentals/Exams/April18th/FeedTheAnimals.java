package April18th2018;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FeedTheAnimals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, Integer> myAnimals = new LinkedHashMap<>();
        Map<String, Integer> myAreas = new LinkedHashMap<>();

        while (!"Last Info".equals(input)) {
            String[] data = input.split(":");
            String command = data[0];
            String animalName = data[1];
            switch (command) {
                case "Add":
                    int dailyFoodLimit = Integer.parseInt(data[2]);
                    String area = data[3];

                    if (!myAreas.containsKey(area)) {
                        myAreas.put(area, 1);
                    } else {
                        if (!myAnimals.containsKey(animalName)) {
                            myAreas.put(area, myAreas.get(area) + 1);
                        }
                    }
                    myAnimals.putIfAbsent(animalName, 0);
                    myAnimals.put(animalName, myAnimals.get(animalName) + dailyFoodLimit);
                    break;

                case "Feed":
                    int food = Integer.parseInt(data[2]);
                    area = data[3];
                    if (myAnimals.containsKey(animalName)) {
                        int oldFood = myAnimals.get(animalName);
                        if (oldFood - food <= 0) {
                            myAnimals.remove(animalName);
                            myAreas.put(area, myAreas.get(area) - 1);
                            System.out.printf("%s was successfully fed%n", animalName);
                        } else {
                            myAnimals.put(animalName, myAnimals.get(animalName) - food);
                        }
                    }
                    break;
            }
            input = sc.nextLine();
        }
        System.out.println("Animals:");

        myAnimals.entrySet()
                .stream()
                .sorted((animal1, animal2) -> {
                    int sort = Integer.compare(animal2.getValue(), animal1.getValue());
                    if (sort == 0) {
                        sort = animal1.getKey().compareTo(animal2.getKey());
                    }
                    return sort;
                }).forEach(animal -> {
            System.out.printf("%s -> %dg%n", animal.getKey(), animal.getValue());
        });
        System.out.println("Areas with hungry animals:");

        myAreas.entrySet()
                .stream()
                .filter(a -> a.getValue() > 0)
                .sorted((a1, a2) -> a2.getValue() - a1.getValue())
                .forEach(area -> System.out.printf("%s : %d%n", area.getKey(), area.getValue()));
    }
}
