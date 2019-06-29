package Abstraction.CardPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CardRanks rank = CardRanks.valueOf(sc.nextLine());
        CardSuit suit  = CardSuit.valueOf(sc.nextLine());

        Card card = new Card(rank, suit);

        System.out.printf("Card name: %s of %s; Card power: %d%n", card.getRank().name(), card.getSuit().name(), card.getPower());

    }
}
