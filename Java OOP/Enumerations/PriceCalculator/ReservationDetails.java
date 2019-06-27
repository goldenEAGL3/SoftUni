package Abstraction.PriceCalculator;

public class ReservationDetails {
    private double pricePerDay;
    private int numberOfDays;
    private Season type;
    private DiscountType discount;

    public ReservationDetails(double pricePerDay, int numberOfDays, Season season, DiscountType discount) {
        this.pricePerDay = pricePerDay;
        this.numberOfDays = numberOfDays;
        this.type = season;
        this.discount = discount;
    }

    public double getPricePerDay() {
        return this.pricePerDay;
    }

    public int getNumberOfDays() {
        return this.numberOfDays;
    }

    public Season getType() {
        return this.type;
    }

    public DiscountType getDiscount() {
        return this.discount;
    }
}
