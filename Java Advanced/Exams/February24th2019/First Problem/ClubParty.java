package Exams.February24th2019;

import java.util.*;
import java.util.stream.Collectors;

public class ClubParty {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int hallCapacity = Integer.parseInt(sc.nextLine());
        ArrayDeque<String> input = new ArrayDeque<>();
        Arrays.stream(sc.nextLine()
                .split("\\s")).forEach(input::push);

        ArrayDeque<String> halls = new ArrayDeque<>();
        List<Integer> currentHallPopulation = new ArrayList<>();
        while (!input.isEmpty()) {
            String currentElement = input.pop();
            if (Character.isLetter(currentElement.charAt(0))) {
                halls.offer(currentElement);
            } else {
                int numberOfPeople = Integer.parseInt(currentElement);
                if (currentHallPopulation.stream().mapToInt(Integer::intValue).sum() + numberOfPeople <= hallCapacity && !halls.isEmpty()) {
                    currentHallPopulation.add(numberOfPeople);
                } else {
                    printHalls(halls, currentHallPopulation);
                    if(!halls.isEmpty()) {
                        currentHallPopulation.add(numberOfPeople);
                    }
                }
            }
        }
        System.out.println();
    }

    private static void printHalls(ArrayDeque<String> halls, List<Integer> currentHallPopulation) {
        if(!halls.isEmpty()) {
            System.out.printf("%s -> ", halls.poll());
            System.out.println(String.join(", ", currentHallPopulation.toString().substring(1, currentHallPopulation.toString().length() - 1)));
            currentHallPopulation.removeAll(currentHallPopulation);
        }
    }
}
