package StecksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class HotPotato {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s");
        int n = Integer.parseInt(sc.nextLine());

        ArrayDeque<String> names = new ArrayDeque<>();
        for (String s : input) {
            names.offer(s);
        }
        int primeCycle = 0;
        while (names.size() > 1) {
            for (int i = 1; ; i++) {

                boolean flag = false;
                if(i == 1) {
                    flag = true;

                } else {
                    for (int k = 2; k <= i / 2; ++k) {
                        if (i % k == 0) {
                            flag = true;
                            break;
                        }
                    }
                }
                for (int j = 1; j < n; j++) {
                    String currentName = names.poll();
                    names.offer(currentName);
                }
                if (flag) {
                    System.out.printf("Removed %s%n", names.poll());
                } else {
                    System.out.printf("Prime %s%n", names.peek());

                }
                if (names.size() == 1) {
                    break;
                }

            }

        }
        System.out.printf("Last is %s", names.peek());
    }
}
