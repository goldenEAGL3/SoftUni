package Encapsulation.ClassBox;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc  =new Scanner(System.in);

        double lenght =Double.parseDouble(sc.nextLine());
        double width = Double.parseDouble(sc.nextLine());
        double height =Double.parseDouble(sc.nextLine());

        try {
            Box box = new Box(lenght, width, height);
            System.out.println(box.toString());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
