package Abstraction.PriceCalculator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = (sc.nextLine().split("\\s+"));
        double pricePerDay = Double.parseDouble(arr[0]);
        int numberOfDays = Integer.parseInt(arr[1]);
        Season season = Season.valueOf(arr[2]);
        DiscountType discountType = DiscountType.valueOf(arr[3]);
        ReservationDetails details = new ReservationDetails(pricePerDay, numberOfDays, season, discountType);
        System.out.printf("%.2f", PriceCalculator.calculate(details));

    }
}
