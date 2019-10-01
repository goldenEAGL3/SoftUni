package Encapsulation.PizzaCalories;

public class Topping {
    private String toppingType;
    private double weight;
    private ToppingTypeEnumerations toppingTypeEnumerations;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        boolean contains = false;
        for (ToppingTypeEnumerations value : ToppingTypeEnumerations.values()) {
            if (value.name().equals(toppingType)) {
                toppingTypeEnumerations = value;
                contains = true;
            }
        }
        if(contains) {
            this.toppingType = toppingType;
        } else {
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", toppingType));
        }
    }

    private void setWeight(double weight) {
        if(weight < 1 || weight > 50) {
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].", toppingType));
        }
        this.weight = weight;
    }

    public double calculateCalories(){
        return 2
                * weight
                * this.toppingTypeEnumerations.getModifier();
    }
}
