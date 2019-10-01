package Encapsulation.AnimalFarm;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    private void setName(String name) {
        if (name.length() < 1) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    private void setAge(int age) {
        if (age > 15 || age < 0) {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    public double productPerDay() {
        return this.calculateProductPerDay();
    }

    private double calculateProductPerDay() {
        double eggsPerDay = 0.0;
        if (this.age >= 0 && this.age <= 5) {
            eggsPerDay = 2.0;
        } else if (this.age <= 11) {
            eggsPerDay = 1.0;
        } else {
            eggsPerDay = 0.75;
        }

        return eggsPerDay;
    }

    @Override
    public String toString() {
        return String.format("Encapsulation.AnimalFarm.Chicken %s (age %d) can produce %.0f eggs per day.",
                this.name,
                this.age,
                this.productPerDay());
    }
}
