package viceCity.models;

import viceCity.models.guns.Gun;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.Player;

import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        for (Gun model : mainPlayer.getGunRepository().getModels()) {
            for (Player civilPlayer : civilPlayers) {
                if (!model.canFire()) {
                    break;
                }
                while (civilPlayer.isAlive() && model.canFire()) {
                    int lifePointsTaken = model.fire();
                    civilPlayer.takeLifePoints(lifePointsTaken);
                }
            }

        }

        for (Player civilPlayer : civilPlayers) {
            if (civilPlayer.isAlive()) {
                for (Gun model : civilPlayer.getGunRepository().getModels()) {
                    if (!mainPlayer.isAlive()) {
                        return;
                    }
                    while (model.canFire() && mainPlayer.isAlive()) {
                        int lifePointsTaken = model.fire();
                        mainPlayer.takeLifePoints(lifePointsTaken);
                    }
                }
            }
        }
    }
}
