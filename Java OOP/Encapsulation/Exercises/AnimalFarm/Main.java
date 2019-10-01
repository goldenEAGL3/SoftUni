package Encapsulation.AnimalFarm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        int years = Integer.parseInt(sc.nextLine());
        try {
            Chicken chicken = new Chicken(name, years);
            System.out.println(chicken.toString());
        } catch (IllegalArgumentException message) {
            System.out.println(message.getMessage());
        }
    }
}
