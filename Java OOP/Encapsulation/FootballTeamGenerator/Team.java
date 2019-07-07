package Encapsulation.FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;


public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new ArrayList<>();
    }

    public void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String playerName) {
        int index = -1;
        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(i).getName().equals(playerName)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new IllegalArgumentException(String.format("Encapsulation.FootballTeamGenerator.Player %s is not in %s team.", playerName, this.name));
        }
        this.players.remove(index);
    }

    public double getRating() {
        return Math.round(this.players.stream()
                .mapToDouble(Player::overallSkillLevel)
                .average().orElse(0));

    }
}
