package rabbits;

public class Rabbit {
    private String name;
    private String species;
    private boolean available;

    public Rabbit(String name, String species) {
        this.name = name;
        this.species = species;
        this.available = true;
    }

    public String getName() {
        return this.name;
    }

    public String getSpecies() {
        return this.species;
    }

    public boolean isAvailable() {
        return this.available;
    }


    public void setAvailable() {
        this.available = !this.available;
    }

    @Override
    public String toString() {
        return String.format("Rabbit (%s): %s", this.getSpecies(), this.getName());
    }
}
