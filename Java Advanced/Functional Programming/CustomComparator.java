package FunctionalProgramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CustomComparator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = Arrays.stream(sc.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Comparator<Integer> ascendingOrderEvenFirst = (a,b) -> {
            if(a % 2 == 0 && b % 2 != 0) {
                return -1;
            } else if(b % 2 == 0 && a % 2 != 0) {
                return 1;
            }
            return a.compareTo(b);
        };

        Arrays.stream(arr)
                .boxed()
                .sorted(ascendingOrderEvenFirst)
                .forEach(number -> System.out.print(number + " "));

    }
}
