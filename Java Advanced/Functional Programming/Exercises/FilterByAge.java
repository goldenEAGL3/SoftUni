package FunctionalProgramming;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class FilterByAge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        LinkedHashMap<String, Integer> persons = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().split(",\\s+");
            String name = data[0];
            int age = Integer.parseInt(data[1]);
            persons.put(name, age);
        }

        String condition = sc.nextLine();
        int years = Integer.parseInt(sc.nextLine());
        String whatToPrint = sc.nextLine();

       BiPredicate<Map.Entry<String, Integer>, Integer> filterByAge = (person, limitAge) -> {

           if(condition.equals("older")) {
               return person.getValue() >= limitAge;
           } else if(condition.equals("younger")) {
               return person.getValue() < limitAge;
           }
           return false;
       };

       Consumer<Map.Entry<String, Integer>> print = person -> {
           if("name age".equals(whatToPrint)) {
               System.out.printf("%s - %d%n", person.getKey(), person.getValue());
           } else if("name".equals(whatToPrint)) {
               System.out.printf("%s%n", person.getKey());
           } else if("age".equals(whatToPrint)) {
               System.out.printf("%d%n", person.getValue());
           }
       };
        persons
                .entrySet()
                .stream()
                .filter(a -> filterByAge.test(a, years))
                .forEach(print);

    }
}
