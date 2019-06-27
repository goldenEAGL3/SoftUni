package Abstraction.PriceCalculator;

public enum DiscountType {
    VIP(20),
    SecondVisit(10),
    None(0);

    int discount;

    DiscountType(int discount) {
        this.discount = discount;
    }

    
    public double getDiscount() {
        return this.discount / 100.0;
    }
}
