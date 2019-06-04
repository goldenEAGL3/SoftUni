package Classes.RawData;

public class Tires {
    private int tireAge;
    private double tirePressure;

    public Tires(double tirePressure, int tireAge) {
        this.tireAge = tireAge;
        this.tirePressure = tirePressure;
    }

    public int getTireAge() {
        return this.tireAge;
    }

    public double getTirePressure() {
        return this.tirePressure;
    }

}
