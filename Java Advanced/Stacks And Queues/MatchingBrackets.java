package StecksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class MatchingBrackets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if(ch == '(') {
                stack.push(i);
            } else if(ch == ')') {
                String content = input.substring(stack.pop(), i+1);
                System.out.println(content);
            }
        }
    }
}
