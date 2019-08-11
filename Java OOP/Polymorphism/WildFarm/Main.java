package Polymorphism.WildFarm;

import Polymorphism.WildFarm.Animals.*;
import Polymorphism.WildFarm.Food.Food;
import Polymorphism.WildFarm.Food.Meat;
import Polymorphism.WildFarm.Food.Vegetable;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] animalInput = sc.nextLine().split("\\s+");
        int counter = 0;
        List<Animal> animals = new ArrayList<>();
        List<Food> foods = new ArrayList<>();
        while (!"End".equals(animalInput[0])) {
            String[] foodInput = sc.nextLine().split("\\s+");

            Animal animal = null;

            switch (animalInput[0]) {
                case "Mouse":
                    animal = new Mouse(animalInput[0], animalInput[1], Double.parseDouble(animalInput[2]), animalInput[3]);
                    break;

                case "Zebra":
                    animal = new Zebra(animalInput[0], animalInput[1], Double.parseDouble(animalInput[2]), animalInput[3]);
                    break;

                case "Cat":
                    animal = new Cat(animalInput[0], animalInput[1], Double.parseDouble(animalInput[2]), animalInput[3], animalInput[4]);
                    break;

                case "Tiger":
                    animal = new Tiger(animalInput[0], animalInput[1], Double.parseDouble(animalInput[2]), animalInput[3]);
                    break;
            }

            Food food = null;
            switch (foodInput[0]) {
                case "Vegetable":
                    food = new Vegetable(Integer.parseInt(foodInput[1]));
                    break;

                case "Meat":
                    food = new Meat(Integer.parseInt(foodInput[1]));
                    break;
            }
            animals.add(animal);
            foods.add(food);
            animalInput = sc.nextLine().split("\\s+");
        }

        for (int i = 0; i < animals.size(); i++) {
            animals.get(i).makeSound();
            try {
                animals.get(i).eat(foods.get(i));
            } catch (IllegalArgumentException message) {
                System.out.println(message.getMessage());
            }
        }

        for (int i = 0; i < animals.size(); i++) {
            System.out.println(animals.get(i).toString());
        }
    }
}
