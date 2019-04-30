package StecksAndQueues;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Scanner;

public class BalancedParentheses {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        ArrayDeque<Character> symbols = new ArrayDeque<>();
        boolean isBalanced = true;
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);

            if(current == '{' || current == '[' || current == '(') {
                symbols.push(current);
            } else {
                if(symbols.isEmpty()) {
                    isBalanced = false;
                    break;
                }
                char openingBracket = symbols.pop();
                if(current == '}' && openingBracket != '{') {
                    isBalanced = false;
                    break;
                } else if(current == ']' && openingBracket != '[') {
                    isBalanced = false;
                    break;
                } else if(current == ')' && openingBracket != '(') {
                    isBalanced = false;
                    break;
                }
            }
        }
        if(isBalanced) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
