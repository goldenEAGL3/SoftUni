package Classes.CarSalesman;

public class Engine {
    private String model;
    private int power;
    private String displacement;
    private String efficiency;

    private static final String DISPLACEMENT_DEF_VALUE = "n/a";
    private static final String EFFICIENCY_DEF_VALUE = "n/a";

    public Engine() {

    }

    public Engine(String model, int power, String displacement, String efficiency) {
        this(model, power);
        this.displacement = displacement;
        this.efficiency = efficiency;
    }



    public Engine(String model, int power) {
        this.model = model;
        this.power = power;
        this.displacement = Engine.DISPLACEMENT_DEF_VALUE;
        this.efficiency = Engine.EFFICIENCY_DEF_VALUE;

    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public String getModel() {
        return model;
    }

    public int getPower() {
        return power;
    }

    public String getDisplacement() {
        return displacement;
    }

    public String getEfficiency() {
        return efficiency;
    }


}
