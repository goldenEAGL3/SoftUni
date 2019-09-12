package StecksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class DecimalToBinary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int number = Integer.parseInt(sc.nextLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        if(number == 0) {
            System.out.println(0);
        } else {
            while(number != 0) {
                stack.push(number % 2);
                number /= 2;
            }

            while(stack.size() > 0) {
                System.out.print(stack.pop());
            }
        }

    }
}
