package core;

import core.interfaces.ManagerController;
import models.battleFields.BattleFieldImpl;
import models.battleFields.interfaces.Battlefield;
import models.cards.MagicCard;
import models.cards.TrapCard;
import models.cards.interfaces.Card;
import models.players.Advanced;
import models.players.Beginner;
import models.players.interfaces.Player;
import repositories.CardRepositoryImpl;
import repositories.PlayerRepositoryImpl;
import repositories.interfaces.CardRepository;
import repositories.interfaces.PlayerRepository;

import static common.ConstantMessages.*;


public class ManagerControllerImpl implements ManagerController {
    private static final String BEGINNER_PLAYER_TYPE = "Beginner";
    private static final String ADVANCED_PLAYER_TYPE = "Advanced";
    private static final String MAGIC_CARD_TYPE = "Magic";
    private static final String TRAP_CARD_TYPE = "Trap";


    private PlayerRepository playerRepository;
    private CardRepository cardRepository;
    private Battlefield battlefield;

    public ManagerControllerImpl() {
        playerRepository = new PlayerRepositoryImpl();
        cardRepository = new CardRepositoryImpl();
        battlefield = new BattleFieldImpl();
    }

    @Override
    public String addPlayer(String type, String username) {
        Player player = null;
        if (BEGINNER_PLAYER_TYPE.equals(type)) {
            player = new Beginner(new CardRepositoryImpl(), username);
        } else if (ADVANCED_PLAYER_TYPE.equals(type)) {
            player = new Advanced(new CardRepositoryImpl(), username);
        }

        this.playerRepository.add(player);
        return String.format(SUCCESSFULLY_ADDED_PLAYER, type, username);
    }

    @Override
    public String addCard(String type, String name) {
        Card card = null;
        if (MAGIC_CARD_TYPE.equals(type)) {
            card = new MagicCard(name);
        } else if (TRAP_CARD_TYPE.equals(type)) {
            card = new TrapCard(name);
        }

        this.cardRepository.add(card);
        return String.format(SUCCESSFULLY_ADDED_CARD, type, name);
    }

    @Override
    public String addPlayerCard(String username, String cardName) {
        Player player = this.playerRepository.find(username);
        Card cardToAdd = this.cardRepository.find(cardName);
        player.getCardRepository().add(cardToAdd);
        return String.format(SUCCESSFULLY_ADDED_PLAYER_WITH_CARDS, cardName, username);
    }

    @Override
    public String fight(String attackUser, String enemyUser) {
        Player attackPlayer = this.playerRepository.find(attackUser);
        Player enemyPlayer = this.playerRepository.find(enemyUser);
        this.battlefield.fight(attackPlayer, enemyPlayer);
        return String.format(FIGHT_INFO, attackPlayer.getHealth(), enemyPlayer.getHealth());
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();

        for (Player player : this.playerRepository.getPlayers()) {
            sb.append(player.toString())
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
