package Classes.SpeedRacing;

public class Car {
    private String model;
    private double fuelAmount;
    private double fuelCostFor1Kilometer;
    private double distance;


    public Car(String model, double fuelAmount, double fuelCostFor1Kilometer) {
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.fuelCostFor1Kilometer = fuelCostFor1Kilometer;
        this.distance = 0;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public String getModel() {
        return model;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount -= fuelAmount;
    }

    public double getFuelCostFor1Kilometer() {
        return fuelCostFor1Kilometer;
    }

    public void setFuelCostFor1Kilometer(double fuelCostFor1Kilometer) {
        this.fuelCostFor1Kilometer = fuelCostFor1Kilometer;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance += distance;
    }


    public boolean canMove(double distance) {
        double neededFuel = distance * this.fuelCostFor1Kilometer;
        if (this.fuelAmount - neededFuel >= 0) {

            return true;
        }
        return false;
    }

    public String getInfo() {
        return String.format("%s %.2f %.0f", this.model, this.fuelAmount, this.distance);
    }


}
