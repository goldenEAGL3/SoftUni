package Encapsulation.FootballTeamGenerator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        Map<String, Team> teams = new LinkedHashMap<>();
        while (!"END".equals(command)) {
            String[] data = command.split(";");
            try {
                switch (data[0]) {
                    case "Encapsulation.FootballTeamGenerator.Team":
                        Team team = new Team(data[1]);
                        teams.putIfAbsent(data[1], team);
                        break;

                    case "Add":
                        if (!teams.containsKey(data[1])) {
                            throw new IllegalArgumentException(String.format("Encapsulation.FootballTeamGenerator.Team %s does not exist.", data[1]));
                        } else {
                            Player player = new Player(data[2], Integer.parseInt(data[3]),
                                    Integer.parseInt(data[4]),
                                    Integer.parseInt(data[5]),
                                    Integer.parseInt(data[6]),
                                    Integer.parseInt(data[7]));
                            teams.get(data[1]).addPlayer(player);
                        }
                        break;

                    case "Remove":
                        teams.get(data[1]).removePlayer(data[2]);
                        break;

                    case "Rating":
                        if (!teams.containsKey(data[1])) {
                            throw new IllegalArgumentException(String.format("Encapsulation.FootballTeamGenerator.Team %s does not exist.", data[1]));
                        } else {
                            double rating = teams.get(data[1]).getRating();
                            System.out.println(String.format("%s - %.0f", data[1], rating));
                        }
                        break;
                }

            } catch (IllegalArgumentException message) {
                System.out.println(message.getMessage());
            }
            command = sc.nextLine();

        }

    }
}
