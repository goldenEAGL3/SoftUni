package Encapsulation.PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] pizzaData = sc.nextLine().split("\\s+");
        String[] doughData = sc.nextLine().split("\\s+");
        Pizza pizza = new Pizza();
        try {
            pizza = new Pizza(pizzaData[1], Integer.parseInt(pizzaData[2]));
            Dough dough = new Dough(doughData[1], doughData[2], Double.parseDouble(doughData[3]));
            pizza.setDough(dough);
            String command = sc.nextLine();

            while (!"END".equals(command)) {
                String[] data = command.split("\\s+");
                Topping topping = new Topping(data[1], Double.parseDouble(data[2]));
                pizza.addTopping(topping);
                command = sc.nextLine();
            }
            System.out.println(pizza.toString());
        } catch (IllegalArgumentException message) {
            System.out.println(message.getMessage());
        }

    }
}
