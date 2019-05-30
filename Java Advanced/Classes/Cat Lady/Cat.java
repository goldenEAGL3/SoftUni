package Classes.CatLady;

public class Cat extends CatBreed {

    private String name;

    public Cat(String breed, String name) {
        this.name = name;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void getInfo() {
        double parameter = 0;
        if(this.earSize != 0.0) {
            parameter = this.earSize;
        } else if(this.furLength != 0.0) {
            parameter = this.furLength;
        } else if(this.decibelOfMeows != 0.0) {
            parameter = this.decibelOfMeows;
        }
        System.out.printf("%s %s %.2f%n", this.breed, this.name, parameter);
    }
}
