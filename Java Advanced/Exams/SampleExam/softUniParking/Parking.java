package Exams.SampleExam.softUniParking;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Parking {
    private Map<String, Car> cars = new LinkedHashMap<>();
    private int capacity;

    public Parking(int capacity) {
        this.capacity = capacity;
        this.cars = new LinkedHashMap<>();
    }

    public String addCar(Car car) {
        String result;
        if (this.cars.containsKey(car.getRegistrationNumber())) {
            result = "Car with that registration number, already exists!";
        } else if (this.cars.size() >= capacity) {
            result = "Parking is full!";
        } else {
            this.cars.putIfAbsent(car.getRegistrationNumber(), car);
            result = String.format("Successfully added new car %s %s", car.getMake(), car.getRegistrationNumber());
        }
        return result;
    }

    public String removeCar(String registrationNumber) {
        String result;
        if (this.cars.containsKey(registrationNumber)) {
            this.cars.remove(registrationNumber);
            result = String.format("Successfully removed %s", registrationNumber);
        } else {
            result = "Car with that registration number, doesn't exists!";
        }
        return result;
    }

    public Car getCar(String registrationNumber) {
        return this.cars.get(registrationNumber);
    }

    public int getCount() {
        return this.cars.size();
    }

    public void removeSetOfRegistrationNumber(List<String> registrationNumbers) {
        for (String registrationNumber : registrationNumbers) {
            this.removeCar(registrationNumber);
        }
    }

}
