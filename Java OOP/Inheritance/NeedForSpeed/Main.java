package Inheritance.NeedForSpeed;

public class Main {
    public static void main(String[] args) {

        SportCar sportCar = new SportCar(25, 200);
        Motorcycle motorcycle = new Motorcycle(30, 250);

        sportCar.drive(2);
        sportCar.drive(3);

        motorcycle.drive(3);
    }
}
