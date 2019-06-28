package Abstraction.TrafficLight;

public class TrafficLight {
    private LightState state;

    public TrafficLight(LightState state) {
        this.state = state;
    }

    public void update() {
        switch (this.state) {
            case RED:
                this.state = LightState.GREEN;
                break;
            case YELLOW:
                this.state = LightState.RED;
                break;
            case GREEN:
                this.state = LightState.YELLOW;
                break;
        }
    }

    @Override
    public String toString() {
        return this.state.toString();
    }
}
