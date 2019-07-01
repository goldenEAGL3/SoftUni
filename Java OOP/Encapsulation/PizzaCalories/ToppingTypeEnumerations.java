package Encapsulation.PizzaCalories;

public enum ToppingTypeEnumerations {
    Meat(1.2),
    Veggies(0.8),
    Cheese(1.1),
    Sauce(0.9);

    double modifier;

    ToppingTypeEnumerations(double modifier) {
        this.modifier = modifier;
    }

    public double getModifier() {
        return this.modifier;
    }
}
