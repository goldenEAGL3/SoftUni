package Classes.RawData;

public class Engine {
    private int engineSpeed;
    private int enginePower;

    public Engine(int engineSpeed, int enginePower) {
        this.engineSpeed = engineSpeed;
        this.enginePower = enginePower;
    }

    public int getEngineSpeed() {
        return this.engineSpeed;
    }

    public int getEnginePower() {
        return this.enginePower;
    }
}
