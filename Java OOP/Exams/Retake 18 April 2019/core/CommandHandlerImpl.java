package core;

import core.interfaces.CommandHandler;
import core.interfaces.ManagerController;

import java.util.Arrays;
import java.util.List;

public class CommandHandlerImpl implements CommandHandler {
    ManagerController managerController;

    CommandHandlerImpl() {
        this.managerController = new ManagerControllerImpl();
    }

    @Override
    public String handle(List<String> commands) {
        StringBuilder sb = new StringBuilder();
        for (String inputCommand : commands) {
            String[] data = inputCommand.split("\\s+");
            String[] information = Arrays.stream(data).skip(1).toArray(String[]::new);
            String command = data[0];
            String result = null;
            try {
                switch (command) {
                    case "AddPlayer":
                        result = addPlayer(information);
                        break;

                    case "AddCard":
                        result = addCard(information);
                        break;

                    case "AddPlayerCard":
                        result = addPlayerCard(information);
                        break;

                    case "Fight":
                        result = fight(information);
                        break;

                    case "Report":
                        result = report();
                        break;

                }
            } catch (IllegalArgumentException ex) {
                result = ex.getMessage();
            }
            sb.append(result).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    private String report() {
        return managerController.report();
    }

    private String fight(String[] information) {
        String attackUser = information[0];
        String enemyUser = information[1];
        String result = managerController.fight(attackUser, enemyUser);
        return result;
    }

    private String addPlayerCard(String[] information) {
        String username = information[0];
        String cardName = information[1];
        String result = managerController.addPlayerCard(username, cardName);
        return result;
    }

    private String addCard(String[] information) {
        String type = information[0];
        String cardName = information[1];
        String result = managerController.addCard(type, cardName);
        return result;
    }

    private String addPlayer(String[] information) {
        String type = information[0];
        String playerName = information[1];
        String result = managerController.addPlayer(type, playerName);
        return result;
    }
}
