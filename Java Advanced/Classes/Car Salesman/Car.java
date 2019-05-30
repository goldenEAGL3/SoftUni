package Classes.CarSalesman;

public class Car {
    private String model;
    private Engine engine;
    private String color;
    private String weight;

    private static final String COLOR_DEF_VALUE = "n/a";
    private static final String WEIGTH_DEF_VALUE = "n/a";


    public Car() {

    }

    public Car(String model, Engine engine, String weight, String color) {
        this(model, engine);
        this.color = color;
        this.weight = weight;
    }

    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
        this.color = COLOR_DEF_VALUE;
        this.weight = WEIGTH_DEF_VALUE;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public String getColor() {
        return color;
    }

    public String getWeight() {
        return weight;
    }


    public String getInfo() {
        return String.format("  %s:%n" +
                        "  %s:%n" +
                        "    Power: %d%n" +
                        "    Displacement: %s%n" +
                        "    Efficiency: %s%n" +
                        "  Weight: %s%n" +
                        "  Color: %s",
                this.model,
                this.engine.getModel(),
                this.engine.getPower(),
                this.engine.getDisplacement(),
                this.engine.getEfficiency(),
                this.weight,
                this.color);
    }
}
