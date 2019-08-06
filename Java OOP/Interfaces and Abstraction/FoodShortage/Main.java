import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfPeople = sc.nextInt();
        sc.nextLine();

        List<Buyer> people = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i++) {
            String line = sc.nextLine();
            String[] data = line.split("\\s+");

            Buyer person = null;
            if (data.length == 3) {
                person = new Rebel(
                        data[0],
                        Integer.parseInt(data[1]),
                        data[2]);

            } else {
                person = new Citizen(
                        data[0],
                        Integer.parseInt(data[1]),
                        data[2],
                        data[3]);

            }
            people.add(person);

        }
        String personBuysFood = sc.nextLine();
        while (!"End".equals(personBuysFood)) {
            for (Buyer person : people) {
                if(person.getName().equals(personBuysFood)) {
                    person.buyFood();
                }
            }

            personBuysFood = sc.nextLine();
        }

        System.out.println(people.stream().map(Buyer::getFood).mapToInt(Integer::intValue).sum());

    }
}
