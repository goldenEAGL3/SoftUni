package MidExam.April16th;

import java.util.Scanner;

public class EasterCozonacs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double budget = Double.parseDouble(sc.nextLine());
        double price1KgFlour = Double.parseDouble(sc.nextLine());

        double pricePackEggs = price1KgFlour * 0.75;
        double priceLiterMilk = price1KgFlour * 1.25;
        int countCozonacs = 0;
        int countColoredEggs = 0;
        double expenses = price1KgFlour + pricePackEggs + priceLiterMilk * 0.25;

        while(true) {

            if(budget - expenses >= 0) {
                budget -= expenses;
                countCozonacs++;
                countColoredEggs += 3;
                if(countCozonacs % 3 == 0) {
                    countColoredEggs -= (countCozonacs - 2);
                }
            } else {

                break;
            }

        }
        System.out.printf("You made %d cozonacs! Now you have %d eggs and %.2fBGN left.", countCozonacs, countColoredEggs, budget);
    }
}
