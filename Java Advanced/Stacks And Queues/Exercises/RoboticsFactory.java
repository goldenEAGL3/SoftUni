package StecksAndQueues;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Scanner;

public class RoboticsFactory {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] robotData = sc.nextLine().split(";");

        String[] robotName = new String[robotData.length];
        int[] robotTime = new int[robotData.length];
        int[] robotProcessTime = new int[robotData.length];

        for (int i = 0; i < robotData.length; i++) {
            robotName[i] = robotData[i].split("-")[0];
            robotTime[i] = Integer.parseInt(robotData[i].split("-")[1]);
        }

        String[] startingTime = sc.nextLine().split(":");
        int hours = Integer.parseInt(startingTime[0]);
        int minutes = Integer.parseInt(startingTime[1]);
        int secs = Integer.parseInt(startingTime[2]);

        int time = hours * 3600 + minutes * 60 + secs;
        ArrayDeque<String> products = new ArrayDeque<>();

        String input = sc.nextLine();
        while (!"End".equals(input)) {
            products.offer(input);
            input = sc.nextLine();
        }

        while(!products.isEmpty()) {
            boolean isAssigned = false;
            time++;
            String firstProduct = products.poll();
            for (int i = 0; i < robotData.length; i++) {
                if(robotProcessTime[i] == 0 && !isAssigned) {
                    robotProcessTime[i] = robotTime[i];
                    printTask(robotName[i], time, firstProduct);
                    isAssigned = true;
                }
                if(robotProcessTime[i] > 0) {
                    robotProcessTime[i]--;
                }
            }
            if(!isAssigned) {
                products.offer(firstProduct);
            }
        }

    }

    private static void printTask(String s, int time, String firstProduct) {
        int hours = time / 3600 % 24;
        int minutes = time % 3600 / 60;
        int secs = time % 60;

        System.out.printf("%s - %s [%02d:%02d:%02d]%n", s, firstProduct, hours, minutes, secs);
    }
}
