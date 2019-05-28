package Classes.CarInfo2;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int countCars = Integer.parseInt(sc.nextLine());
        IntStream.rangeClosed(1, countCars)
                .boxed()
                .map(n -> sc.nextLine().split("\\s+"))
                .map(data ->  {
                    CarInfo newCar;
                    if(data.length == 1) {
                        newCar = new CarInfo(data[0]);
                    } else {
                        newCar = new CarInfo(data[0], data[1], Integer.parseInt(data[2]));
                    }
                    return newCar;
                }).forEach(car -> System.out.println(car.getInfo()));
    }
}
