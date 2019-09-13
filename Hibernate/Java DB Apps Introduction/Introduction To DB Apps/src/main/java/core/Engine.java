package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class Engine implements Runnable {
    private Connection connection;
    private BufferedReader reader;
    private CommandHandlerImpl commandHandler;

    public Engine(Connection connection) {
        this.connection = connection;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.commandHandler = new CommandHandlerImpl(this.connection, this.reader);
    }

    public void run() {

        try {
            String commandInput = reader.readLine();
            commandHandler.handle(commandInput);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
