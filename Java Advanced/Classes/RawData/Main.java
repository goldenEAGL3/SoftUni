package Classes.RawData;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfCars = Integer.parseInt(sc.nextLine());
        LinkedHashSet<Car> cars = new LinkedHashSet<>();
        for (int i = 0; i < numberOfCars; i++) {
            String[] data = sc.nextLine().split("\\s+");
            Engine engine = new Engine(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            Cargo cargo = new Cargo(Integer.parseInt(data[3]), data[4]);
            Tires tire1 = new Tires(Double.parseDouble(data[5]), Integer.parseInt(data[6]));
            Tires tire2 = new Tires(Double.parseDouble(data[7]), Integer.parseInt(data[8]));
            Tires tire3 = new Tires(Double.parseDouble(data[9]), Integer.parseInt(data[10]));
            Tires tire4 = new Tires(Double.parseDouble(data[11]), Integer.parseInt(data[12]));

            Car newCar = new Car(data[0], engine, cargo, tire1, tire2, tire3, tire4);
            cars.add(newCar);
        }

        String input = sc.nextLine();
        switch (input) {
            case "fragile":
                cars.stream().filter(car -> car.getCargo().getCargoType().equals("fragile") &&
                        (car.getTire1().getTirePressure() < 1.0
                                || car.getTire2().getTirePressure() < 1.0
                                || car.getTire3().getTirePressure() < 1.0
                                || car.getTire4().getTirePressure() < 1.0)).forEach(car -> System.out.printf("%s%n", car.getModel()));
                break;

            case "flamable":
                cars.stream().filter(car -> car.getCargo().getCargoType().equals("flamable") &&
                        car.getEngine().getEnginePower() > 250).forEach(car -> System.out.printf("%s%n", car.getModel()));
                break;

        }
    }
}
