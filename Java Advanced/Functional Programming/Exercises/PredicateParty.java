package FunctionalProgramming;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateParty {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = Arrays.stream(sc.nextLine()
                .split("\\s+"))
                .collect(Collectors.toCollection(ArrayList::new));


        String input = sc.nextLine();
        while (!"Party!".equals(input)) {
            String[] data = input.substring(input.indexOf(' ')+1).split("\\s+");
            Predicate<String> predicate = null;

            switch (data[0]) {
                case "StartsWith":
                    predicate = str -> str.startsWith(data[1]);
                    break;

                case "EndsWith":
                    predicate = str -> str.endsWith(data[1]);
                    break;

                case "Length":
                    predicate = str -> str.length() == Integer.parseInt(data[1]);
                    break;
            }

            if (input.contains("Remove")) {
                names = names.stream().filter(predicate.negate()).collect(Collectors.toCollection(ArrayList::new));
            } else if (input.contains("Double")) {

                for (int i = 0; i < names.size(); i++) {
                    if(predicate.test(names.get(i))) {
                        names.add(i+1, names.get(i));
                        i++;
                    }
                }

            }

            input = sc.nextLine();
        }
        if(!names.isEmpty()) {
            System.out.print(String.join(", ", names) + " are going to the party!");
        } else {
            System.out.print("Nobody is going to the party!");
        }


    }

}

