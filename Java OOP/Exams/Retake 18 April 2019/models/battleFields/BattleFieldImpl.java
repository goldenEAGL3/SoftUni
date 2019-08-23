package models.battleFields;

import models.battleFields.interfaces.Battlefield;
import models.cards.interfaces.Card;
import models.players.Beginner;
import models.players.interfaces.Player;

public class BattleFieldImpl implements Battlefield {
    private static final int BEGINNER_HEALTH_INCREASE = 40;
    private static final int BEGINNER_CARD_DAMAGE_INCREASE = 30;

    @Override
    public void fight(Player attackPlayer, Player enemyPlayer) {
        if (attackPlayer.isDead() || enemyPlayer.isDead()) {
            throw new IllegalArgumentException("Player is dead!");
        }

        bonusForBeginners(attackPlayer);
        bonusForBeginners(enemyPlayer);

        preFightBonuses(attackPlayer);
        preFightBonuses(enemyPlayer);

        while (true) {
            int attackPlayerDamage = attackPlayer.getCardRepository().getCards().stream().mapToInt(Card::getDamagePoints).sum();
            int enemyPlayerDamage = enemyPlayer.getCardRepository().getCards().stream().mapToInt(Card::getDamagePoints).sum();
            enemyPlayer.takeDamage(attackPlayerDamage);

            if (enemyPlayer.isDead()) {
                return;
            }

            attackPlayer.takeDamage(enemyPlayerDamage);

            if (attackPlayer.isDead()) {
                return;
            }
        }
    }

    private void preFightBonuses(Player player) {
        int bonusHealthPoints = player.getCardRepository().getCards().stream().mapToInt(Card::getHealthPoints).sum();
        player.setHealth(player.getHealth() + bonusHealthPoints);
    }

    private void bonusForBeginners(Player player) {
        if (player instanceof Beginner) {
            player.setHealth(player.getHealth() + BEGINNER_HEALTH_INCREASE);

            player
                    .getCardRepository()
                    .getCards()
                    .forEach(card -> card.setDamagePoints(card.getDamagePoints() + BEGINNER_CARD_DAMAGE_INCREASE));
        }
    }
}
