package Classes.SpeedRacing;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfCars = Integer.parseInt(sc.nextLine());
        LinkedHashMap<String, Car> drivingCars = new LinkedHashMap<>();

        IntStream.rangeClosed(1, numberOfCars)
                .boxed().map(number -> sc.nextLine()
                .split("\\s+"))
                .map(carData -> new Car(carData[0], Double.parseDouble(carData[1]), Double.parseDouble(carData[2])))
                .forEach(Car -> drivingCars.put(Car.getModel(), Car));

        String command = sc.nextLine();
        while (!"End".equals(command)) {
            String[] data = command.split("\\s+");
            String model = data[1];
            double distance = Double.parseDouble(data[2]);
            if (drivingCars.containsKey(model)) {
                if (drivingCars.get(model).canMove(distance)) {
                    double neededFuel = distance * drivingCars.get(model).getFuelCostFor1Kilometer();
                    drivingCars.get(model).setFuelAmount(neededFuel);
                    drivingCars.get(model).setDistance(distance);
                } else {
                    System.out.println("Insufficient fuel for the drive");
                }
            }
            command = sc.nextLine();
        }

        drivingCars.forEach((key, value) -> System.out.println(value.getInfo()));
    }
}
