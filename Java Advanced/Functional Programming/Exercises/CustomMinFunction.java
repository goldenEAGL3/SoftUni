package FunctionalProgramming;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class CustomMinFunction {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Function<int[], Integer> smallestNumber = x -> Arrays.stream(x).min().getAsInt();
        int[] arr = Arrays.stream(sc.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println(smallestNumber.apply(arr));
    }
}
