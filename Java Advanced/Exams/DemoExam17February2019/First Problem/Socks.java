package Exams.DemoExam17February2019;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Socks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayDeque<Integer> leftSocks = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(leftSocks::push);
        ArrayDeque<Integer> rightSocks = new ArrayDeque<>();
        Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(rightSocks::add);

        ArrayDeque<Integer> pairsOfSocks = new ArrayDeque<>();
        while (!leftSocks.isEmpty() && !rightSocks.isEmpty()) {
            int leftSock = leftSocks.peek();
            int rightSock = rightSocks.peek();

            sockPairing(leftSocks, rightSocks, pairsOfSocks, leftSock, rightSock);
        }
        System.out.println(pairsOfSocks.stream().max(Integer::compareTo).get());
        pairsOfSocks.forEach(sock -> System.out.print(sock + " "));
    }

    private static void sockPairing(ArrayDeque<Integer> leftSocks, ArrayDeque<Integer> rightSocks, ArrayDeque<Integer> pairsOfSocks, int leftSock, int rightSock) {
        if (leftSock > rightSock) {
            int pairSock = leftSock + rightSock;
            pairsOfSocks.add(pairSock);
            leftSocks.pop();
            rightSocks.poll();
        } else if (leftSock < rightSock) {
            leftSocks.pop();
        } else {
            rightSocks.poll();
            leftSock++;
            leftSocks.pop();
            leftSocks.push(leftSock);
        }
    }
}
