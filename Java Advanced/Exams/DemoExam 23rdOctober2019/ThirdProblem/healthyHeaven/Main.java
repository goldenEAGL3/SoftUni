package healthyHeaven;

public class Main {
    public static void main(String[] args) {

        Restaurant restaurant = new Restaurant("Casa Domingo");

        Salad salad = new Salad("Proba");

        Vegetable vegetable = new Vegetable("Cucumber", 10);
        Vegetable vegetable2 = new Vegetable("Potato", 20);

        salad.add(vegetable2);
        salad.add(vegetable);

        System.out.println(salad.getTotalCalories());
        System.out.println(salad);


    }
}
