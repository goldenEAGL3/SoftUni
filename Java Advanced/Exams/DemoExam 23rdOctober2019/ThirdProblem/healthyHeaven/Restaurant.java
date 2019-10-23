package healthyHeaven;

import java.util.*;

public class Restaurant {

    private String name;
    private List<Salad> data;

    public Restaurant(String name) {
        this.name = name;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }


    public void add(Salad salad) {
        this.data.add(salad);
    }

    public boolean buy(String name) {
        Salad saladToRemove = null;
        for (Salad salad : data) {
            if (salad.getName().equals(name)) {
                saladToRemove = salad;
                break;
            }
        }
        if (saladToRemove != null) {
            this.data.remove(saladToRemove);
            return true;
        }
        return false;
    }

    public Salad getHealthiestSalad() {
        int minCalories = Integer.MAX_VALUE;
        Salad salad = null;
        for (Salad datum : data) {
            if (datum.getTotalCalories() < minCalories) {
                minCalories = datum.getTotalCalories();
                salad = datum;
            }
        }
        return salad;
    }

    public String generateMenu() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s have %d salads:",
                this.name,
                this.data.size()))
                .append(System.lineSeparator());

        for (Salad kvp : data) {
            sb.append(kvp).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }


}
