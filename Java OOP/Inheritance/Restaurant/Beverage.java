package restaurant;

import java.math.BigDecimal;

public class Beverage extends Product {

    private double milliliters;

    public Beverage(String name, BigDecimal price, double millimeters) {
        super(name, price);
        this.milliliters = millimeters;
    }

    public double getMilliliters() {
        return this.milliliters;
    }


}
