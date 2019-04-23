package April18th2018;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimalSanctuary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String regex = "^n:(?<name>[^;]+);t:(?<animal>[^;]+);c--(?<country>[A-Za-z\\s]+)$";
        Pattern pattern = Pattern.compile(regex);
        int totalWeight = 0;

        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                for (int i1 = 0; i1 < input.length(); i1++) {

                    if (Character.isDigit(input.charAt(i1))) {
                        totalWeight += Integer.parseInt("" + input.charAt(i1));
                    }
                }
                StringBuilder sbName = returnCleanString(matcher.group("name"));
                StringBuilder sbAnimal = returnCleanString(matcher.group("animal"));
                StringBuilder sbCountry = returnCleanString(matcher.group("country"));
                System.out.printf("%s is a %s from %s%n", sbName, sbAnimal, sbCountry);
            }
        }
        System.out.printf("Total weight of animals: %dKG", totalWeight);
    }

    private static StringBuilder returnCleanString(String input) {
        StringBuilder sb = new StringBuilder();
        String container = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
        for (int i = 0; i < input.length(); i++) {
            if (container.contains("" + input.charAt(i))) {
                sb.append(input.charAt(i));
            }
        }
        return sb;
    }
}
