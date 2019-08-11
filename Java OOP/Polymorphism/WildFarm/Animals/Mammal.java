package Polymorphism.WildFarm.Animals;

import Polymorphism.WildFarm.Food.Food;
import Polymorphism.WildFarm.Food.Vegetable;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {
    private String livingRegion;

    public Mammal(String animalType, String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight);
        this.livingRegion = livingRegion;
    }

    protected String getLivingRegion() {
        return this.livingRegion;
    }

    @Override
    public void eat(Food food) {
        String resultType = this.getClass().getSimpleName().equals("Mouse") ?  "Mice" : "Zebras";
        if(!(food instanceof Vegetable)) {
           throw new IllegalArgumentException(String.format("%s are not eating that type of food!", resultType));
        }
        super.setFoodEaten(super.getFoodEaten() + food.getQuantity());
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return String.format("%s[%s, %s, %s, %d]",
                super.getAnimalType(),
                super.getAnimalName(),
                df.format(super.getAnimalWeight()),
                this.getLivingRegion(),
                super.getFoodEaten());
    }
}
