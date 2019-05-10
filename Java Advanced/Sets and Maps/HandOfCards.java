package SetsAndMaps;

import java.util.*;

public class HandOfCards {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, List<String>> personsAndCards = new LinkedHashMap<>();

        String input = sc.nextLine();
        while(!"JOKER".equals(input)) {
            String[] data = input.split(":\\s+");
            String[] typeOfCards = data[1].split(",\\s+");

            personsAndCards.putIfAbsent(data[0], new ArrayList<>());
            for (String card : typeOfCards) {
                if(!personsAndCards.get(data[0]).contains(card)) {
                    personsAndCards.get(data[0]).add(card);
                }
            }

            input = sc.nextLine();
        }
        for (Map.Entry<String, List<String>> outerLoop : personsAndCards.entrySet()) {
            int totSum = 0;
            for (String card : outerLoop.getValue()) {
                int sum = 0;
                for (int i = 0; i < card.length(); i++) {
                    switch(card.charAt(i)) {
                        case '0':
                        case '1':
                            sum = 10;
                            break;
                        case 'J':
                            sum = 11;
                            break;
                        case 'Q':
                            sum = 12;
                            break;
                        case 'K':
                            sum = 13;
                            break;
                        case 'A':
                            sum = 14;
                            break;
                        case 'S':
                            sum *= 4;
                            break;
                        case 'H':
                            sum *= 3;
                            break;
                        case 'D':
                            sum *= 2;
                            break;
                        case 'C':
                            sum *= 1;
                            break;
                            default:
                                sum = Integer.parseInt("" + card.charAt(i));
                    }

                }
                totSum += sum;
            }
            System.out.printf("%s: %d%n", outerLoop.getKey(), totSum);
        }
    }
}
