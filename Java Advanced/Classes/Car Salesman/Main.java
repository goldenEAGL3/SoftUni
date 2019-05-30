package Classes.CarSalesman;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfEngines = Integer.parseInt(sc.nextLine());
        HashMap<String, Engine> allEngines = new HashMap<>();
        for (int i = 0; i < numberOfEngines; i++) {
            String[] input = sc.nextLine().split("\\s+");
            Engine engine = new Engine();
            switch (input.length) {
                case 4:
                    engine = new Engine(input[0], Integer.parseInt(input[1]), input[2], input[3]);
                    break;
                case 3:
                    engine = new Engine(input[0], Integer.parseInt(input[1]));
                    if (input[2].matches("[0-9]+")) {
                        engine.setDisplacement(input[2]);
                    } else {
                        engine.setEfficiency(input[2]);
                    }
                    break;
                case 2:
                    engine = new Engine(input[0], Integer.parseInt(input[1]));
                    break;
            }
            allEngines.put(input[0], engine);
        }

        int numberOfCars = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numberOfCars; i++) {
            String[] input = sc.nextLine().split("\\s+");
            Car car = new Car();

            switch (input.length) {

                case 4:
                    car = new Car(input[0], allEngines.get(input[1]), input[2], input[3]);
                    break;

                case 3:
                    car = new Car(input[0], allEngines.get(input[1]));
                    if (input[2].matches("[0-9]+")) {
                        car.setWeight(input[2]);
                    } else {
                        car.setColor(input[2]);
                    }
                    break;

                case 2:
                    car = new Car(input[0], allEngines.get(input[1]));
                    break;
            }

            System.out.println(car.getInfo());

        }
    }
}
