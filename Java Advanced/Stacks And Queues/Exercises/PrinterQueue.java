package StecksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class PrinterQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayDeque<String> printerQueue = new ArrayDeque<>();
        String input = sc.nextLine();

        while (!"print".equals(input)) {

            if ("cancel".equals(input)) {
                if (printerQueue.size() == 0) {
                    System.out.println("Printer is on standby");
                } else {
                    System.out.printf("Canceled %s%n", printerQueue.poll());
                }
            } else {
                printerQueue.offer(input);
            }
            input = sc.nextLine();
        }

        while(printerQueue.size() > 0) {
            System.out.println(printerQueue.poll());
        }
    }
}
