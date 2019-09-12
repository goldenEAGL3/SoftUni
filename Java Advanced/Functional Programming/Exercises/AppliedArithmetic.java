package FunctionalProgramming;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class AppliedArithmetic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = Arrays.stream(sc.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        String command = sc.nextLine();
        BiFunction<int[], String, int[]> opeartionsWithArrayNumbers = (array, input) -> {
            switch (input) {
                case "add":
                    return Arrays.stream(array).map(number -> ++number).toArray();

                case "subtract":
                    return Arrays.stream(array).map(number -> --number).toArray();

                case "multiply":
                    return Arrays.stream(array).map(number -> number *= 2).toArray();

            }
            return array;
        };
        Consumer<Integer> print = x -> System.out.printf("%d ", x);

        while (!"end".equals(command)) {
            if ("print".equals(command)) {
                Arrays.stream(arr).forEach(print::accept);
                System.out.println();
            } else {
                arr = opeartionsWithArrayNumbers.apply(arr, command);
            }
            command = sc.nextLine();
        }
    }
}
