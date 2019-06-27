package Abstraction.PriceCalculator;

public enum Season {
    Autumn(1),
    Spring(2),
    Winter(3),
    Summer(4);

    int value;

    Season(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
