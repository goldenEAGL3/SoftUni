package Classes.CarInfo2;

public class CarInfo {
    private String make;
    private String model;
    private int horsePower;

    public static String unknownModel = "unknown";
    public static int unknownHorsePower = -1;

    public CarInfo(String make, String model, int horsePower) {
        this(make, model);
        this.horsePower = horsePower;
    }

    public CarInfo(String make, String model) {
        this(make);
        this.model = model;
    }

    public CarInfo(String make) {
        this.make = make;
        this.model = unknownModel;
        this.horsePower = unknownHorsePower;
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return this.horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getInfo() {
        return String.format("The car is: %s %s - %d HP.", this.make, this.model, this.horsePower);
    }
}
