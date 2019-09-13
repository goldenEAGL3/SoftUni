package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class IncreaseMinionsAge extends CommandImpl {
    public IncreaseMinionsAge(Connection connection, BufferedReader reader) {
        super(connection, reader);
    }

    @Override
    public void execute() throws SQLException, IOException {
        int[] minionIDs = Arrays
                .stream(this.getReader().readLine()
                        .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        String query;
        PreparedStatement statement;

        for (int id : minionIDs) {
            query = "UPDATE minions SET age = age + 1, name = LOWER(name) WHERE id = ?";
            statement = this.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        }

        query = "SELECT name, age FROM minions";
        statement = this.getConnection().prepareStatement(query);
        ResultSet result = statement.executeQuery();
        while(result.next()) {
            System.out.printf("%s %d%n", result.getString("name"), result.getInt("age"));
        }
    }
}
