package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static constantMessages.MessagesAndQueries.*;

public class RemoveVillain extends CommandImpl {
    private static final int VALUE_WHEN_UPDATE_HAS_FAILED = 0;

    public RemoveVillain(Connection connection, BufferedReader reader) {
        super(connection, reader);
    }

    @Override
    public void execute() throws SQLException, IOException {
        int villainID = Integer.parseInt(this.getReader().readLine());

        PreparedStatement villainStatement = this.getConnection().prepareStatement(DEFAULT_SELECT_VILLAIN_NAME_QUERY);
        villainStatement.setInt(1, villainID);
        ResultSet villainResult = villainStatement.executeQuery();
        if (!villainResult.next()) {
            System.out.println("No such villain was found");
            return;
        }

        String releaseMinionsQuery = "UPDATE minions_villains SET minion_id = NULL WHERE villain_id = ?";
        PreparedStatement releaseMinionsStatement = this.getConnection().prepareStatement(releaseMinionsQuery);
        releaseMinionsStatement.setInt(1, villainID);
        int numberOfMinionsReleased = releaseMinionsStatement.executeUpdate();


        PreparedStatement deleteVillainStatement = this.getConnection().prepareStatement(DELETE_VILLAIN_FROM_MINIONS_VILLAINS_TABLE_QUERY);

        deleteVillainStatement.setInt(1, villainID);
        deleteVillainStatement.executeUpdate();

        deleteVillainStatement = this.getConnection().prepareStatement(DELETE_VILLAIN_FROM_VILLAINS_TABLE_QUERY);
        deleteVillainStatement.setInt(1, villainID);

        if (deleteVillainStatement.executeUpdate() != VALUE_WHEN_UPDATE_HAS_FAILED) {
            System.out.println(String.format("%s was deleted%n" +
                            "%d minions released",
                    villainResult.getString("name"),
                    numberOfMinionsReleased));
        }
    }
}
