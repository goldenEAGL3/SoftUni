package StecksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class SimpleTextEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder text = new StringBuilder();
        ArrayDeque<StringBuilder> myText = new ArrayDeque<>();
        myText.push(new StringBuilder(text));
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String[] input = sc.nextLine().split("\\s+");
            String command = input[0];

            switch (command) {
                case "1":
                    text.append(input[1]);
                    myText.push(new StringBuilder(text));
                    break;

                case "2":

                    int count = Integer.parseInt(input[1]);
                    text.replace(text.length() - count, text.length(), "");
                    myText.push(new StringBuilder(text));
                    break;

                case "3":
                    int index = Integer.parseInt(input[1]);
                    System.out.println(text.charAt(index - 1));
                    break;

                case "4":
                    if (myText.size() > 1) {
                        myText.pop();
                        text = new StringBuilder(myText.peek());
                    }

                    break;

            }

        }
    }
}
