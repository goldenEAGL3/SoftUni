package Classes.RawData;

public class Cargo {
    private String cargoType;
    private int cargoWeigth;

    public Cargo(int cargoWeigth, String cargoType) {
        this.cargoType = cargoType;
        this.cargoWeigth = cargoWeigth;
    }

    public String getCargoType() {
        return this.cargoType;
    }

    public int getCargoWeigth() {
        return this.cargoWeigth;
    }

}
