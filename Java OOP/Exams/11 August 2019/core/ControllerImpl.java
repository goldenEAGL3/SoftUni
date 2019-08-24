package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.*;
import viceCity.models.guns.Gun;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.Player;

import viceCity.repositories.GunRepository;
import viceCity.repositories.interfaces.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private static final String MAIN_PLAYER_FAMILY_NAME = "Vercetti";
    private static final String RIFLE = "Rifle";
    private static final String PISTOL = "Pistol";

    private Player mainPlayer;
    private Map<String, Player> civilPlayers;
    private Repository<Gun> guns;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        mainPlayer = new MainPlayer();
        civilPlayers = new LinkedHashMap<>();
        guns = new GunRepository();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        this.civilPlayers.put(name, player);
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        BaseGun gun = null;
        if (RIFLE.equals(type)) {
            gun = new Rifle(name);
        } else if (PISTOL.equals(type)) {
            gun = new Pistol(name);
        }
        String message = "";

        if (gun == null) {
            return GUN_TYPE_INVALID;
        }

        this.guns.add(gun);
        return String.format(GUN_ADDED, gun.getName(), type);
    }

    @Override
    public String addGunToPlayer(String name) {
        if (this.guns.getModels().isEmpty()) {
            return GUN_QUEUE_IS_EMPTY;
        }

        Gun gun = this.guns.getModels().stream().findFirst().get();

        if (MAIN_PLAYER_FAMILY_NAME.equals(name)) {
            mainPlayer.getGunRepository().add(gun);
            this.guns.remove(gun);
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), mainPlayer.getName());
        }

        if (!civilPlayers.containsKey(name)) {
            return CIVIL_PLAYER_DOES_NOT_EXIST;
        }

        Player player = civilPlayers.get(name);
        player.getGunRepository().add(gun);
        this.guns.remove(gun);
        return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), player.getName());

    }

    @Override
    public String fight() {
        this.neighbourhood.action(mainPlayer, this.civilPlayers.values());
        if (checkIfEverythingIsOkay(mainPlayer, this.civilPlayers.values())) {
            return FIGHT_HOT_HAPPENED;
        }

        int deadCivilPlayers = (int) this.civilPlayers.values().stream().filter(player -> player.getLifePoints() == 0).count();

        List<Gun> gunsToRemove = this.mainPlayer
                .getGunRepository()
                .getModels()
                .stream()
                .filter(weapon -> !weapon.canFire())
                .collect(Collectors.toList());

        for (Gun gun : gunsToRemove) {
            this.mainPlayer.getGunRepository().remove(gun);
        }

        List<String> playersToRemove = this.civilPlayers.entrySet()
                .stream()
                .filter(player -> player.getValue().getLifePoints() == 0).map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));

        for (String s : playersToRemove) {
            this.civilPlayers.remove(s);
        }

        String sb = FIGHT_HAPPENED + System.lineSeparator() +
                String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE + System.lineSeparator() +
                                MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE + System.lineSeparator() +
                                CIVIL_PLAYERS_LEFT_MESSAGE + System.lineSeparator(),
                        mainPlayer.getLifePoints(),
                        deadCivilPlayers,
                        this.civilPlayers.size());
        return sb.trim();
    }

    private boolean checkIfEverythingIsOkay(Player mainPlayer, Collection<Player> values) {
        boolean mainPlayerHarmed = mainPlayer.getLifePoints() < 100;
        int peopleHarmed = (int) values.stream().filter(people -> people.getLifePoints() < 50).count();

        return !mainPlayerHarmed && peopleHarmed == 0;
    }
}
