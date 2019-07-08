package Inheritance.animals;

public class Cat extends Animal {
    private final static String SOUND = "Meow meow";

    public Cat(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return Cat.SOUND;
    }
}
