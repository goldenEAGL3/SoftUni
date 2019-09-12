package FunctionalProgramming;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListOfPredicates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = Integer.parseInt(sc.nextLine());

        List<Integer> numbers = IntStream
                .rangeClosed(1, number)
                .boxed().collect(Collectors.toList());

        List<Integer> numberSequence = Arrays.stream(sc.nextLine()
                .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        Predicate<Integer> divisibleNumber = a -> {
            for (int b : numberSequence) {
                if (a % b != 0) {
                    return false;
                }
            }
            return true;
        };
        numbers.stream().filter(divisibleNumber).forEach(element -> System.out.printf("%d ", element));


    }
}
