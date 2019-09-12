package StecksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistory {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        ArrayDeque<String> browserHistory = new ArrayDeque<>();
        ArrayDeque<String> forwardHistory = new ArrayDeque<>();
        while (!"Home".equals(input)) {
            String current = "";
            if ("back".equals(input)) {
                if (browserHistory.size() <= 1) {
                    System.out.println("no previous URLs");
                } else {
                    forwardHistory.push(browserHistory.pop());
                    current = browserHistory.peek();
                    System.out.println(current);

                }
            } else if ("forward".equals(input)) {
                if(forwardHistory.isEmpty()) {
                    System.out.println("no next URLs");
                } else {
                    current = forwardHistory.pop();
                    browserHistory.push(current);
                    System.out.println(current);
                }

            } else {
                browserHistory.push(input);
                System.out.println(browserHistory.peek());
                forwardHistory.clear();
            }
            input = sc.nextLine();
        }
    }
}
