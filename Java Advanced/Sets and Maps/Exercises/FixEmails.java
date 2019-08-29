package SetsAndMaps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FixEmails {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, String> fixedEmails = new LinkedHashMap<>();

        String name = sc.nextLine();
        while (!"stop".equals(name)) {
            String email = sc.nextLine();
            String[] emailEnding = email.split("\\.");
            switch (emailEnding[emailEnding.length - 1].toLowerCase()) {
                case "us":
                case "uk":
                case "com":
                    break;
                default:
                    fixedEmails.put(name, email);

            }
            name = sc.nextLine();

        }
        for (Map.Entry<String, String> kvp : fixedEmails.entrySet()) {
            System.out.printf("%s -> %s%n", kvp.getKey(), kvp.getValue());

        }
    }
}
