package models.players;

import models.cards.interfaces.Card;
import models.players.interfaces.Player;
import repositories.CardRepositoryImpl;
import repositories.interfaces.CardRepository;

import static common.ConstantMessages.DEFAULT_REPORT_SEPARATOR;
import static common.ConstantMessages.PLAYER_REPORT_INFO;

public abstract class BasePlayer implements Player {
    private static final int DEFAULT_HEALTH_MIN_VALUE = 0;
    private static final int DEFAULT_DAMAGE_POINTS_MIN_VALUE = 0;

    private String username;
    private int health;
    private CardRepository cardRepository;
    private boolean isDead;

    protected BasePlayer(CardRepository cardRepository, String username, int health) {
        this.cardRepository = cardRepository;
        this.setUsername(username);
        this.setHealth(health);
        this.setDead(false);
    }

    @Override
    public CardRepository getCardRepository() {
        return this.cardRepository;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int healthPoints) {
        if (healthPoints < DEFAULT_HEALTH_MIN_VALUE) {
            throw new IllegalArgumentException("Player's health bonus cannot be less than zero. ");
        }
        this.health = healthPoints;
    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public void takeDamage(int damagePoints) {
        if (damagePoints < DEFAULT_DAMAGE_POINTS_MIN_VALUE) {
            throw new IllegalArgumentException("Damage points cannot be less than zero.");
        }
        this.health -= damagePoints;

        if (this.health <= DEFAULT_HEALTH_MIN_VALUE) {
            this.health = DEFAULT_HEALTH_MIN_VALUE;
            this.setDead(true);
        }
    }

    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Player's username cannot be null or an empty string. ");
        }
        this.username = username;
    }

    private void setDead(boolean dead) {
        this.isDead = dead;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(PLAYER_REPORT_INFO,
                this.getUsername(),
                this.getHealth(),
                this.getCardRepository().getCount()))
                .append(System.lineSeparator());

        for (Card card : this.getCardRepository().getCards()) {
            sb.append(card.toString()).append(System.lineSeparator());
        }

        sb.append(DEFAULT_REPORT_SEPARATOR);

        return sb.toString().trim();
    }
}
