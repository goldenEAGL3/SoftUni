package Abstraction.PriceCalculator;

public class PriceCalculator {

    public static double calculate(ReservationDetails details){
        double price = details.getPricePerDay() * details.getNumberOfDays();
        price *= details.getType().getValue();
        price *= 1 - details.getDiscount().getDiscount();
        return price;
    }
}
