package FunctionalProgramming;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindTheSmallestElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> arr = Arrays.stream(sc.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());



        Function<List<Integer>, Integer> smallestElementIndex = array -> {
            int min = array.stream().min(Integer::compareTo).get();
            return array.lastIndexOf(min);
        };

        System.out.println(smallestElementIndex.apply(arr));


    }
}
