package Classes.OpinionPoll;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfPersons = Integer.parseInt(sc.nextLine());
        IntStream.rangeClosed(1, numberOfPersons)
                .boxed()
                .map(number -> sc.nextLine().split("\\s+"))
                .map(personData -> new Person(personData[0], Integer.parseInt(personData[1])))
                .filter(person -> person.getAge() > 30)
                .sorted(Comparator.comparing(Person::getName)).forEach(person -> System.out.printf("%s - %d%n", person.getName(), person.getAge()));
    }
}
