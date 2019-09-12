package FunctionalProgramming;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PartyReservationFilterMode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> guests = Arrays.stream(sc.nextLine()
                .split("\\s+"))
                .collect(Collectors.toCollection(ArrayList::new));


        Map<String, Predicate<String>> predicateMap = new HashMap<>();
        String input = sc.nextLine();

        while (!"Print".equals(input)) {
            String[] data = input.substring(input.indexOf(";") + 1).split(";");
            Predicate<String> predicate = null;
            String token = data[0] + data[1];
            switch (data[0]) {
                case "Starts with":
                    predicate = str -> str.startsWith(data[1]);
                    break;
                case "Ends with":
                    predicate = str -> str.endsWith(data[1]);
                    break;
                case "Length":
                    predicate = str -> str.length() == Integer.parseInt(data[1]);
                    break;
                case "Contains":
                    predicate = str -> str.contains(data[1]);
                    break;
            }

            if (input.contains("Add")) {
                predicateMap.putIfAbsent(token,predicate);
            } else if (input.contains("Remove")) {
              predicateMap.remove(token);
            }
            input = sc.nextLine();
        }

        for (String guest : guests) {
            boolean isValidGuest = true;
            for (Map.Entry<String, Predicate<String>> stringPredicate : predicateMap.entrySet()) {
                if(stringPredicate.getValue().test(guest)) {
                    isValidGuest = false;
                    break;
                }
            }
            if(isValidGuest) {
                System.out.print(guest + " ");
            }
        }
    }
}
