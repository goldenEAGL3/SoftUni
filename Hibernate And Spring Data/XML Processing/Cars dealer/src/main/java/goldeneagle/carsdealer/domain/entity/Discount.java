package goldeneagle.carsdealer.domain.entity;

public enum Discount {
    A(0),
    B(5),
    C(10),
    D(15),
    E(20),
    F(30),
    G(40),
    H(50);

    private int numVal;

    Discount(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return this.numVal;
    }



}
