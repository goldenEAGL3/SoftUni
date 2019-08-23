package repositories;

import models.players.interfaces.Player;
import repositories.interfaces.PlayerRepository;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerRepositoryImpl implements PlayerRepository {
    private Map<String, Player> players;

    public PlayerRepositoryImpl() {
        this.players = new LinkedHashMap<>();
    }

    @Override
    public int getCount() {
        return this.players.size();
    }

    @Override
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players.values().stream().collect(Collectors.toList()));
        //Test with this.players.values().stream().collect(Collectors.toList())
    }

    @Override
    public void add(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (this.players.containsKey(player.getUsername())) {
            String message = String.format("Player %s already exists!", player.getUsername());
            throw new IllegalArgumentException(message);
        }
        this.players.put(player.getUsername(), player);

    }

    @Override
    public boolean remove(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (!this.players.containsKey(player.getUsername())) {
            return false;
        }
        this.players.remove(player.getUsername());
        return true;
    }

    @Override
    public Player find(String name) {
        return this.players.get(name);
    }
}
