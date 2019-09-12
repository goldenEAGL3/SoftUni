package FunctionalProgramming;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

public class PredicateForNames {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        String[] split = sc.nextLine().split("\\s+");

        Predicate<String> lessOrEqualLength = word -> word.length() <= n;
        Arrays.stream(split).filter(lessOrEqualLength).forEach(System.out::println);
    }
}
