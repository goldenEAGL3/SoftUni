package StecksAndQueues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReverseNumbersWithAStack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        ArrayDeque<String> myNumbers = new ArrayDeque<>();
        String[] input = sc.nextLine().split("\\s+");
        for (String s : input) {
            myNumbers.push(s);
        }
        while(!myNumbers.isEmpty()) {
            System.out.print(myNumbers.pop() + " ");
        }

    }
}
