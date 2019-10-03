package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;

public class IncreaseMinionsAgeWithStoredProcedure extends CommandImpl {

    public IncreaseMinionsAgeWithStoredProcedure(Connection connection, BufferedReader reader) {
        super(connection, reader);
    }

    @Override
    public void execute() throws SQLException, IOException {
        String query = "{CALL usp_get_older(?)}";
        int minionID = Integer.parseInt(this.getReader().readLine());

        CallableStatement statement = this.getConnection().prepareCall(query);
        statement.setInt(1, minionID);

        statement.executeQuery();

        String printQuery = "SELECT age, name FROM minions WHERE id = ?";
        PreparedStatement printStatement = this.getConnection().prepareStatement(printQuery);
        printStatement.setInt(1, minionID);

        ResultSet result = printStatement.executeQuery();

        while (result.next()) {
            System.out.printf("%s %d", result.getString("name"), result.getInt("age"));
        }
    }
}
