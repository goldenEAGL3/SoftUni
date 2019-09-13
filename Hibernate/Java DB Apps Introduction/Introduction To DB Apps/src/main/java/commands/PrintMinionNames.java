package commands;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintMinionNames extends CommandImpl {
    public PrintMinionNames(Connection connection, BufferedReader reader) {
        super(connection, reader);
    }

    @Override
    public void execute() throws SQLException {
        String query = "SELECT name FROM minions;";
        String countQuery = "SELECT COUNT(id) AS `count` FROM minions;";

        PreparedStatement statement = this.getConnection().prepareStatement(query);
        PreparedStatement countStatement = this.getConnection().prepareStatement(countQuery);

        ResultSet result = statement.executeQuery();
        ResultSet count = countStatement.executeQuery();
        count.next();
        int countOfMinions = count.getInt(1);

        int currentIteration = 0;

        while (currentIteration != countOfMinions / 2) {

            result.first();
            for (int i = 0; i < currentIteration; i++) {
                result.next();
            }
            System.out.printf("%s%n", result.getString("name"));

            result.last();
            for (int i = 0; i < currentIteration; i++) {
                result.previous();
            }
            System.out.printf("%s%n", result.getString("name"));
            currentIteration++;
        }
    }
}
