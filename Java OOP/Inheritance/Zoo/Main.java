package Inheritance.Zoo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String name = sc.nextLine();

        Snake snake = new Snake(name);

        Mammal mammal = new Mammal("Gosho");

        Bear bear = new Bear("Kiro");

        Lizard lizard = new Lizard("Moncho");
        System.out.println(lizard.getName());
        System.out.println(snake.getName());
        System.out.println(mammal.getName());
        System.out.println(bear.getName());
    }
}
