package core;

import commands.*;
import commands.interfaces.Command;
import core.interfaces.CommandHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;

public class CommandHandlerImpl implements CommandHandler {
    private Connection connection;
    private BufferedReader reader;
    private Command command;

    public CommandHandlerImpl(Connection connection, BufferedReader reader) {
        this.connection = connection;
        this.reader = reader;
    }

    public void handle(String inputCommand) throws IOException, SQLException {
        switch (inputCommand) {
            case "Get Villains’ Names":
                command = new GetVillainsName(this.connection, this.reader);
                break;

            case "Get Minion Names":
                command = new GetMinionNames(this.connection, this.reader);
                break;

            case "Add Minion":
                command = new AddMinion(this.connection, this.reader);
                break;

            case "Change Town Names Casing":
                command = new ChangeTownCasing(this.connection, this.reader);
                break;

            case "Remove Villain":
                command = new RemoveVillain(this.connection, this.reader);
                break;

            case "Print All Minion Names":
                command = new PrintMinionNames(this.connection, this.reader);
                break;

            case "Increase Minions Age":
                command = new IncreaseMinionsAge(this.connection, this.reader);
                break;

            case "Increase Age Stored Procedure":
                command = new IncreaseMinionsAgeWithStoredProcedure(this.connection, this.reader);
                break;
        }
        command.execute();
    }

}
