package Polymorphism.WildFarm.Animals;

import Polymorphism.WildFarm.Food.Food;

import java.text.DecimalFormat;

public abstract class Animal {
    private String animalType;
    private String animalName;
    private Double animalWeight;
    private Integer foodEaten;

    public Animal(String animalType, String animalName, Double animalWeight) {
        this.animalType = animalType;
        this.animalName = animalName;
        this.animalWeight = animalWeight;
        this.foodEaten = 0;
    }


    protected String getAnimalType() {
        return this.animalType;
    }

    protected String getAnimalName() {
        return this.animalName;
    }

    protected Double getAnimalWeight() {
        return this.animalWeight;
    }

    protected void setFoodEaten(Integer foodEaten) {
        this.foodEaten = foodEaten;
    }

    protected Integer getFoodEaten() {
        return this.foodEaten;
    }


    public abstract void makeSound();

    public abstract void eat(Food food);


}
