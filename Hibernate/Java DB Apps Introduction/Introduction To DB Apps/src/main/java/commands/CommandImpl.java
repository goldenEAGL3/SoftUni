package commands;

import commands.interfaces.Command;

import java.io.BufferedReader;
import java.sql.Connection;

public abstract class CommandImpl implements Command {

    private Connection connection;
    private BufferedReader reader;


    public CommandImpl(Connection connection, BufferedReader reader) {
        this.connection = connection;
        this.reader = reader;
    }

    protected Connection getConnection() {
        return this.connection;
    }

    protected BufferedReader getReader() {
        return this.reader;
    }
}
