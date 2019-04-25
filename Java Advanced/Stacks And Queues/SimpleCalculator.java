package StecksAndQueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> calculator = Arrays.stream(sc.nextLine()
                .split("\\s"))
                .collect(Collectors.toCollection(ArrayDeque::new));

        int sum = 0;
        while (calculator.size() > 0) {
            String current = calculator.pop();
            if ("+".equals(current)) {
                sum += Integer.parseInt(calculator.pop());
            } else if ("-".equals(current)) {
                sum -= Integer.parseInt(calculator.pop());
            } else {
                sum = Integer.parseInt(current);
            }
        }
        System.out.println(sum);

    }
}
