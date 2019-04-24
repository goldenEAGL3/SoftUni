package MidExam.April16th;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EasterShopping {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> myShops = Arrays.stream(sc.nextLine()
                .split("\\s"))
                .collect(Collectors.toList());

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().split("\\s");
            String command = input[0];
            String shop;
            switch (command) {
                case "Include":
                    shop = input[1];
                    myShops.add(shop);
                    break;

                case "Visit":
                    String command2 = input[1];
                    int numberShops = Integer.parseInt(input[2]);
                    visitShops(myShops, command2, numberShops);
                    break;

                case "Prefer":
                    preferShops(myShops, input);

                    break;

                case "Place":
                    placeShops(myShops, input);
                    break;

            }
        }
        System.out.println("Shops left:");
        System.out.print(String.join(" ", myShops));
    }

    private static void placeShops(List<String> myShops, String[] input) {
        String shop;
        int index = Integer.parseInt(input[2]);
        shop = input[1];
        if (index +1 >= 0 && index + 1 < myShops.size()) {
            myShops.add(index + 1, shop);
        }
    }

    private static void preferShops(List<String> myShops, String[] input) {
        int index1 = Integer.parseInt(input[1]);
        int index2 = Integer.parseInt(input[2]);
        if (index1 >= 0 && index1 < myShops.size() && index2 >= 0 && index2 < myShops.size()) {
            String temp = myShops.get(index1);
            myShops.set(index1, myShops.get(index2));
            myShops.set(index2, temp);
        }
    }

    private static void visitShops(List<String> myShops, String command2, int numberShops) {
        if (numberShops <= myShops.size()) {
            switch (command2) {
                case "first":
                    myShops.removeAll(myShops.subList(0, numberShops));
                    break;

                case "last":
                    myShops.removeAll(myShops.subList(myShops.size() - numberShops, myShops.size()));
                    break;
            }
        }
    }
}
