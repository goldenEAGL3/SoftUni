package rabbits;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cage {

    private String name;
    private int capacity;
    private List<Rabbit> data;

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getSize() {
        return this.data.size();
    }

    public void add(Rabbit rabbit) {
        if(this.getSize() < this.getCapacity()) {
            this.data.add(rabbit);
        }
    }

    public boolean removeRabbit(String name) {
        Rabbit rabbitToRemove = null;
        for (Rabbit rabbit : this.data) {
            if(rabbit.getName().equals(name)) {
                rabbitToRemove = rabbit;
                break;
            }
        }
        return this.data.remove(rabbitToRemove);
    }

    public void removeSpecies(String species) {
        List<Rabbit> rabbitsToRemove = this.data.stream()
                .filter(rabbit -> rabbit.getSpecies().equals(species))
                .collect(Collectors.toList());
        this.data.removeAll(rabbitsToRemove);
    }

    public Rabbit sellRabbit(String name) {
        Rabbit rabbitToReturn = null;
        for (Rabbit rabbit : this.data) {
            if(rabbit.getName().equals(name)) {
                rabbitToReturn = rabbit;
                rabbitToReturn.setAvailable();
            }
        }
        return rabbitToReturn;
    }

    public List<Rabbit> sellRabbitBySpecies(String species) {
        List<Rabbit> rabbits = this.data.stream().filter(rabbit -> rabbit.getSpecies().equals(species))
                .peek(Rabbit::setAvailable)
                .collect(Collectors.toList());
        return rabbits;
    }

    public int count() {
        return this.data.size();
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Rabbits available at %s:", this.getName())).append(System.lineSeparator());
        List<Rabbit> rabbits = this.data.stream().filter(Rabbit::isAvailable)
                .collect(Collectors.toList());

        for (Rabbit rabbit : rabbits) {
            sb.append(rabbit).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }



}
