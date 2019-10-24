package Retake16April2019;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class TrojanInvasion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberOfWarriorWaves = sc.nextInt();
        sc.nextLine();

        ArrayDeque<Integer> plates = new ArrayDeque<>();
        ArrayDeque<Integer> warriors = new ArrayDeque<>();

        Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .forEach(plates::offer);


        for (int i = 1; i <= numberOfWarriorWaves; i++) {
            Arrays.stream(sc.nextLine().split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .forEach(warriors::push);

            if (i % 3 == 0) {
                int newPlate = Integer.parseInt(sc.nextLine());
                plates.offer(newPlate);
            }

            while (!plates.isEmpty() && !warriors.isEmpty()) {

                int plateToAttackHealth = plates.poll();

                while (plateToAttackHealth > 0 && !warriors.isEmpty()) {

                    int warriorHealth = warriors.pop();

                    plateToAttackHealth -= warriorHealth;

                    if (plateToAttackHealth < 0) {
                        warriorHealth = Math.abs(plateToAttackHealth);
                        warriors.push(warriorHealth);
                    }
                }

                if (warriors.isEmpty() && plateToAttackHealth > 0) {
                    plates = modifyPlates(plates, plateToAttackHealth);
                }
            }
            if (plates.isEmpty()) {
                break;
            }
        }

        if (plates.isEmpty()) {
            System.out.printf("The Trojans successfully destroyed the Spartan defense.%n" +
                    "Warriors left: ");
            System.out.print(warriors.toString().substring(1, warriors.toString().length() - 1));
        } else {
            System.out.printf("The Spartans successfully repulsed the Trojan attack.%n" +
                    "Plates left: ");
            System.out.print(plates.toString().substring(1, plates.toString().length() - 1));
        }
    }

    private static ArrayDeque<Integer> modifyPlates(ArrayDeque<Integer> plates, int plateToAttackHealth) {
        ArrayDeque<Integer> newPlates = new ArrayDeque<>();
        newPlates.offer(plateToAttackHealth);

        for (Integer oldPlates : plates) {
            newPlates.offer(oldPlates);
        }
        return newPlates;
    }


}
