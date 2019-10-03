package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constantMessages.MessagesAndQueries.DEFAULT_AFFECTED_TOWNS_QUERY;
import static constantMessages.MessagesAndQueries.DEFAULT_CHANGE_TOWN_CASING_QUERY;

public class ChangeTownCasing extends CommandImpl {
    private static final int VALUE_WHEN_UPDATE_HAS_FAILED = 0;

    public ChangeTownCasing(Connection connection, BufferedReader reader) {
        super(connection, reader);
    }

    @Override
    public void execute() throws SQLException, IOException {
        PreparedStatement townCasingStatement = this.getConnection().prepareStatement(DEFAULT_CHANGE_TOWN_CASING_QUERY);

        String countryName = this.getReader().readLine();
        townCasingStatement.setString(1, countryName);

        if (townCasingStatement.executeUpdate() == VALUE_WHEN_UPDATE_HAS_FAILED) {
            System.out.println("No town names were affected.");
            return;
        }

        printAffectedTowns(countryName);
    }

    private void printAffectedTowns(String countryName) throws SQLException {

        PreparedStatement affectedTownsStatement = this.getConnection().prepareStatement(DEFAULT_AFFECTED_TOWNS_QUERY);
        affectedTownsStatement.setString(1, countryName);
        ResultSet result = affectedTownsStatement.executeQuery();
        List<String> townNames = new ArrayList<>();

        while (result.next()) {
            townNames.add(result.getString("name"));
        }
        System.out.println(String.format("%d town names were affected.%n[%s]", townNames.size(), String.join(", ", townNames)));
    }
}
