package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static constantMessages.MessagesAndQueries.DEFAULT_SELECT_VILLAIN_MINIONS_NAME_QUERY;
import static constantMessages.MessagesAndQueries.DEFAULT_SELECT_VILLAIN_NAME_QUERY;

public class GetMinionNames extends CommandImpl {

    public GetMinionNames(Connection connection, BufferedReader reader) {
        super(connection, reader);
    }

    @Override
    public void execute() throws SQLException, IOException {
        int villainID = Integer.parseInt(this.getReader().readLine());

        PreparedStatement getVillainStatement = this.getConnection().prepareStatement(DEFAULT_SELECT_VILLAIN_NAME_QUERY);
        getVillainStatement.setInt(1, villainID);
        ResultSet villainName = getVillainStatement.executeQuery();

        if (!villainName.next()) {
            System.out.println(String.format("No villain with ID %s exists in the database.", villainID));
            return;
        }

        PreparedStatement getVillainMinionsStatement = this.getConnection().prepareStatement(DEFAULT_SELECT_VILLAIN_MINIONS_NAME_QUERY);
        getVillainMinionsStatement.setInt(1, villainID);
        ResultSet minionsInformation = getVillainMinionsStatement.executeQuery();

        System.out.println(String.format("Villain: %s", villainName.getString("name")));
        int index = 1;
        while (minionsInformation.next()) {
            System.out.println(String.format("%d. %s %d",
                    index++,
                    minionsInformation.getString("name"),
                    minionsInformation.getInt("age")
            ));
        }
    }

}
