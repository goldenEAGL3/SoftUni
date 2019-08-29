package SetsAndMaps;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class NumbersGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LinkedHashSet<Integer> numbers1 = new LinkedHashSet<>();
        LinkedHashSet<Integer> numbers2 = new LinkedHashSet<>();

        int[] firstPlayerNumbers = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] secPlayerNumbers = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        for (int number : firstPlayerNumbers) {
            numbers1.add(number);
        }

        for (int number : secPlayerNumbers) {
            numbers2.add(number);
        }
        int count = 0;
        boolean playerOneWon = false;
        boolean playerTwoWon = false;
        while(count < 50) {
            int iter1 = numbers1.iterator().next();
            numbers1.remove(iter1);
            int iter2 = numbers2.iterator().next();
            numbers2.remove(iter2);

            if(iter1 > iter2) {
                numbers1.add(iter1);
                numbers1.add(iter2);
            } else if(iter2 > iter1) {
                numbers2.add(iter1);
                numbers2.add(iter2);
            }
            count++;

            if(numbers1.isEmpty()) {
                playerTwoWon = true;
                break;
            } else if(numbers2.isEmpty()) {
                playerOneWon = true;
                break;
            }
        }

        if(playerOneWon || numbers1.size() > numbers2.size()) {
            System.out.println("First player win!");
        } else if( playerTwoWon || numbers1.size() < numbers2.size()) {
            System.out.println("Second player win!");
        } else {
            System.out.println("Draw");
        }


    }
}
