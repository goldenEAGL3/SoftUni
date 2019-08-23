package repositories;

import models.cards.interfaces.Card;
import repositories.interfaces.CardRepository;

import java.util.*;
import java.util.stream.Collectors;

public class CardRepositoryImpl implements CardRepository {
    private Map<String, Card> cards;

    public CardRepositoryImpl() {
        this.cards = new LinkedHashMap<>();
    }

    @Override
    public int getCount() {
        return this.cards.size();
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards.values().stream().collect(Collectors.toList()));
    }

    @Override
    public void add(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null!");
        }
        if (this.cards.containsKey(card.getName())) {
            String message = String.format("Card %s already exists!", card.getName());
            throw new IllegalArgumentException(message);
        }

        this.cards.put(card.getName(), card);
    }

    @Override
    public boolean remove(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null!");
        }
        if (!this.cards.containsKey(card.getName())) {
            return false;
        }
        this.cards.remove(card.getName());
        return true;
    }

    @Override
    public Card find(String name) {
        return this.cards.get(name);
    }
}
