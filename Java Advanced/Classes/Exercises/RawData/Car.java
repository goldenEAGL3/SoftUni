package Classes.RawData;

public class Car {
    private String model;
    private Engine engine;
    private Cargo cargo;
    private Tires tire1;
    private Tires tire2;
    private Tires tire3;
    private Tires tire4;

    public Car(String model, Engine engine, Cargo cargo, Tires tire1, Tires tire2, Tires tire3, Tires tire4) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tire1 = tire1;
        this.tire2 = tire2;
        this.tire3 = tire3;
        this.tire4 = tire4;
    }

    public String getModel() {
        return this.model;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public Cargo getCargo() {
        return this.cargo;
    }

    public Tires getTire1() {
        return this.tire1;
    }

    public Tires getTire2() {
        return this.tire2;
    }

    public Tires getTire3() {
        return this.tire3;
    }

    public Tires getTire4() {
        return this.tire4;
    }

}
