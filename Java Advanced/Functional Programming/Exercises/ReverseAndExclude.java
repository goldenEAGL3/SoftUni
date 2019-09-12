package FunctionalProgramming;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class ReverseAndExclude {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = Arrays.stream(sc.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = sc.nextInt();

        Function<int[], List<Integer>> filter = array -> Arrays.stream(array).filter(number -> number % n != 0)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> integerList = filter.apply(arr);

        Collections.reverse(integerList);

        Consumer<List<Integer>> printer = myList -> myList.forEach(number -> System.out.printf("%d ", number));
        printer.accept(integerList);
    }
}
