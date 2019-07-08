package Inheritance.animals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        List<Animal> animals = new ArrayList<>();
        while (!"Beast!".equals(input)) {

            String[] data = sc.nextLine().split("\\s+");
            String name = data[0];
            int age = Integer.parseInt(data[1]);
            String gender;
            Animal pet = null;
            try {
                switch (input) {

                    case "Dog":
                        gender = data[2];
                        pet = new Dog(name, age, gender);
                        break;

                    case "Cat":
                        gender = data[2];
                        pet = new Cat(name, age, gender);
                        break;

                    case "Frog":
                        gender = data[2];
                        pet = new Frog(name, age, gender);
                        break;

                    case "Kitten":
                        pet = new Kitten(name, age);
                        break;

                    case "Tomcat":
                        pet = new Tomcat(name, age);
                        break;
                }
                animals.add(pet);
            } catch (IllegalArgumentException message) {
                System.out.println(message.getMessage());
            }
            input = sc.nextLine();
        }

        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }


    }
}
