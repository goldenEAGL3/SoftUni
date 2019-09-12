package StecksAndQueues;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Scanner;

public class FibonacciSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        ArrayDeque<Long> fibonacci = new ArrayDeque<>();
        for (int i = 0; i < 2; i++) {
            fibonacci.push(1l);
        }
        int count = 1;
        if (n == 0) {
            System.out.println(1);
        } else {
            while (count != n) {
                long number = fibonacci.pop();
                long newNumber = number + fibonacci.peek();
                fibonacci.push(number);
                fibonacci.push(newNumber);
                count++;

            }
            System.out.println(fibonacci.peek());
        }
    }
}
