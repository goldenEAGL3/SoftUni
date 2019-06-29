package Abstraction.CardPower;

public class Card {
    private CardRanks rank;
    private CardSuit suit;
    private int power;

    public Card(CardRanks rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
        this.power = setPower();
    }

    public CardRanks getRank() {
        return this.rank;
    }

    public CardSuit getSuit() {
        return this.suit;
    }


    public int getPower() {
        return this.power;
    }

    private int setPower() {
        return suit.getSuitPower() + rank.getRank();
    }

}
