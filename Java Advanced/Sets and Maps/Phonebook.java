package SetsAndMaps;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Phonebook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, String> phonebook = new LinkedHashMap<>();

        String input = sc.nextLine();
        while(!"search".equals(input)) {
            String[] data = input.split("-");
            String name = data[0];
            String phone = data[1];
            phonebook.putIfAbsent(name, "0");
            phonebook.put(name, phone);
            input = sc.nextLine();
        }
        input = sc.nextLine();
        while(!"stop".equals(input)) {
            if(phonebook.containsKey(input)) {
                System.out.printf("%s -> %s%n", input ,phonebook.get(input));
            } else {
                System.out.printf("Contact %s does not exist.%n", input);
            }
            input = sc.nextLine();
        }
    }
}
