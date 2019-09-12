package FunctionalProgramming;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class FindEvenOrOdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] split = sc.nextLine().split("\\s+");
        String evenOrOdd = sc.nextLine();
        int lowerBound = Integer.parseInt(split[0]);
        int upperBound = Integer.parseInt(split[1]);


        Predicate<Integer> byType = x -> x % 2 == 0;

        Predicate<Integer> filterOdd = x -> x % 2 != 0;
        if("odd".equals(evenOrOdd)) {
            byType = filterOdd;
        }
        IntStream.rangeClosed(lowerBound, upperBound)
                .boxed()
                .filter(byType)
                .forEach(x -> System.out.printf("%d ", x));
    }
}
