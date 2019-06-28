package Abstraction.TrafficLight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader  reader = new BufferedReader(new InputStreamReader(System.in));

        String[] initialStates = reader.readLine().split("\\s+");
        int numberOfChanges = Integer.parseInt(reader.readLine());

        List<TrafficLight> lights = new ArrayList<>();

        createTrafficLight(initialStates, lights);
        printTrafficLightState(numberOfChanges, lights);

    }

    private static void printTrafficLightState(int numberOfChanges, List<TrafficLight> lights) {
        for (int i = 0; i < numberOfChanges; i++) {
            for (TrafficLight light : lights) {
                light.update();
                System.out.print(light.toString() + " ");
            }
            System.out.println();
        }
    }

    private static void createTrafficLight(String[] initialStates, List<TrafficLight> lights) {
        for (String initialState : initialStates) {
            LightState state = LightState.valueOf(initialState);
            TrafficLight trafficLight = new TrafficLight(state);
            lights.add(trafficLight);
        }
    }
}
