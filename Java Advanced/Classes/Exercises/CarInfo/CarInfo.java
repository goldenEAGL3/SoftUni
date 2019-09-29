package Classes.CarInfo;

public class CarInfo {
    private String make;
    private String model;
    private int horsePower;

    public CarInfo(String make, String model, int horsePower) {
        this.make = make;
        this.model = model;
        this.horsePower = horsePower;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = this.make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getInfo() {
        return String.format("The car is: %s %s - %d HP.", this.make, this.model, this.horsePower);
    }
}
