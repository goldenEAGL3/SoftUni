package Exam26October2019;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class DatingApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<Integer> males = new ArrayDeque<>();
        ArrayDeque<Integer> females = new ArrayDeque<>();

        Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .forEach(males::push);

        Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .forEach(females::offer);

        int countOfMatches = 0;
        while (!males.isEmpty() && !females.isEmpty()) {

            if (checkForNegative(females) || checkForNegative(males)) {
                continue;
            }

            if (checkSpecialCase(females) || checkSpecialCase(males)) {
                continue;
            }

            if (checkForNegative(females) || checkForNegative(males)) {
                continue;
            }

            if (females.isEmpty() || males.isEmpty()) {
                break;
            }

            int female = females.poll();
            int male = males.pop();
            if (female == male) {
                countOfMatches++;
            } else {
                male -= 2;
                males = modifyMales(males, male);
            }
        }

        System.out.printf("Matches: %d%n", countOfMatches);
        if (males.isEmpty()) {
            System.out.println("Males left: none");
        } else {
            System.out.printf("Males left: %s%n", String.join(", ", males.toString().substring(1, males.toString().length() - 1)));
        }

        if (females.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            System.out.printf("Females left: %s%n", String.join(", ", females.toString().substring(1, females.toString().length() - 1)));
        }
    }

    private static boolean checkSpecialCase(ArrayDeque<Integer> deque) {
        boolean specialCase = false;
        if (!deque.isEmpty()) {
            if (deque.peek() % 25 == 0) {
                specialCase = true;
                deque.poll();
                if (!deque.isEmpty()) {
                    deque.poll();
                }
            }
        }
        return specialCase;

    }

    private static boolean checkForNegative(ArrayDeque<Integer> deque) {
        if (!deque.isEmpty()) {
            if (deque.peek() <= 0) {
                deque.poll();
                return true;
            }
        }

        return false;
    }

    private static ArrayDeque<Integer> modifyMales(ArrayDeque<Integer> males, int maleValue) {
        ArrayDeque<Integer> newMales = new ArrayDeque<>();
        for (Integer male : males) {
            newMales.offer(male);
        }
        newMales.push(maleValue);
        return newMales;
    }
}
