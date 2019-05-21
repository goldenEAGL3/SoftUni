package FunctionalProgramming;

        import java.util.Arrays;
        import java.util.Scanner;
        import java.util.function.Consumer;

public class ConsumerPrint {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split("\\s+");
        Consumer<String> print = person -> System.out.printf("Sir %s%n", person);

        Arrays.stream(split).forEach(print);
    }
}
