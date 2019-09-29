package Classes.TeamworkProjects;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int countOfTeams = Integer.parseInt(sc.nextLine());
        Map<String, Teams> myTeams = new LinkedHashMap<>();

        for (int i = 0; i < countOfTeams; i++) {
            boolean createdTeam = false;
            String[] data = sc.nextLine().split("-");
            String result = "";
            if (!myTeams.containsKey(data[0])) {
                for (Teams value : myTeams.values()) {
                    if (value.getTeamName().equals(data[1])) {
                        result = String.format("Team %s was already created!", data[1]);
                        createdTeam = true;
                        break;
                    }
                }
                if (!createdTeam) {
                    Teams newTeam = new Teams(data[0], data[1]);
                    myTeams.put(data[0], newTeam);
                    result = String.format("Team %s has been created by %s!",
                            newTeam.getTeamName(), newTeam.getCreatorName());
                }

            } else {
                result = String.format("Team %s was already created!", data[1]);
            }
            if (!result.isEmpty()) {
                System.out.println(result);
            }
        }

        String input = sc.nextLine();
        while (!"end of assignment".equals(input)) {
            String[] data = input.split("->");
            String name = data[0];
            String team = data[1];
            String result = "";
            boolean memberExists = false;
            boolean teamExists = false;


            for (Map.Entry<String, Teams> kvp : myTeams.entrySet()) {
                if (kvp.getValue().getMembers().contains(name)) {
                    memberExists = true;
                    result = String.format("Member %s cannot join team %s!", name, team);
                    break;
                }
            }
            if (!memberExists) {

                for (Map.Entry<String, Teams> kvp : myTeams.entrySet()) {
                    if (kvp.getValue().getTeamName().equals(team)) {
                        teamExists = true;
                        if (!kvp.getKey().equals(name)) {
                            kvp.getValue().getMembers().add(name);
                        } else {
                            result = String.format("Member %s cannot join team %s!", name, team);
                        }
                    }
                }
                if (!teamExists) {
                    result = String.format("Team %s does not exist!", team);
                }
            }

            if (!result.isEmpty()) {
                System.out.println(result);
            }
            input = sc.nextLine();
        }
        myTeams.entrySet().stream().sorted((kvp1, kvp2) -> {
            int sort = Integer.compare(kvp2.getValue().getMembers().size(), kvp1.getValue().getMembers().size());
            if (sort == 0) {
                sort = kvp1.getValue().getTeamName().compareTo(kvp2.getValue().getTeamName());
            }
            return sort;
        }).filter(kvp -> kvp.getValue().getMembers().size() > 0)
                .forEach(kvp -> {

                    System.out.printf("%s%n- %s%n", kvp.getValue().getTeamName(), kvp.getKey());
                    kvp.getValue().getMembers().forEach(member -> System.out.printf("-- %s%n", member));
                });
        System.out.println("Teams to disband:");
        myTeams.values().stream()
                .filter(value -> value.getMembers().size() == 0).sorted(Comparator.comparing(Teams::getTeamName))
                .forEach(value -> System.out.printf("%s%n", value.getTeamName()));
    }
}
