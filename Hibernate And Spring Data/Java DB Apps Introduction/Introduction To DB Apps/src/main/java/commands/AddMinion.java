package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static constantMessages.MessagesAndQueries.*;

public class AddMinion extends CommandImpl {


    public AddMinion(Connection connection, BufferedReader reader) {
        super(connection, reader);
    }

    @Override
    public void execute() throws SQLException, IOException {
        String[] minionData = this.getReader().readLine().split("\\s+");
        String villainName = this.getReader().readLine().split("\\s+")[1];

        String nameOfMinion = minionData[1];
        int ageOfMinion = Integer.parseInt(minionData[2]);
        String townOfMinion = minionData[3];


        PreparedStatement townStatement = this.getConnection().prepareStatement(DEFAULT_SELECT_TOWN_QUERY);
        townStatement.setString(1, townOfMinion);

        if (!townStatement.executeQuery().next()) {
            addToDatabase(townOfMinion, DEFAULT_INSERT_TOWN_QUERY, DEFAULT_INSERT_TOWN_QUERY_RESULT);
        }

        PreparedStatement villainStatement = this.getConnection().prepareStatement(DEFAULT_SELECT_VILLAIN_QUERY);
        villainStatement.setString(1, villainName);

        if (!villainStatement.executeQuery().next()) {
            addToDatabase(villainName, DEFAULT_INSERT_VILLAIN_QUERY, DEFAULT_INSERT_VILLAIN_QUERY_RESULT);
        }

        addMinionToMinionsTable(nameOfMinion, ageOfMinion, townOfMinion);
        addMinionToVillain(villainName, nameOfMinion);
    }

    private void addMinionToVillain(String villainName, String nameOfMinion) throws SQLException {
        PreparedStatement minionToVillainStatement = this.getConnection().prepareStatement(DEFAULT_INSERT_MINION_TO_VILLAIN_QUERY);
        minionToVillainStatement.setString(1, nameOfMinion);
        minionToVillainStatement.setString(2, villainName);
        minionToVillainStatement.executeUpdate();
        System.out.println(String.format(DEFAULT_INSERT_MINION_TO_VILLAIN_QUERY_RESULT, nameOfMinion, villainName));
    }

    private void addMinionToMinionsTable(String nameOfMinion, int ageOfMinion, String townOfMinion) throws SQLException {

        PreparedStatement addMinionStatement = this.getConnection().prepareStatement(DEFAULT_INSERT_MINION_QUERY);
        addMinionStatement.setString(1, nameOfMinion);
        addMinionStatement.setInt(2, ageOfMinion);
        addMinionStatement.setString(3, townOfMinion);
        addMinionStatement.executeUpdate();
    }

    private void addToDatabase(String objectName, String query, String result) throws SQLException {
        PreparedStatement statement = this.getConnection().prepareStatement(query);
        statement.setString(1, objectName);
        statement.executeUpdate();
        System.out.println(String.format(result, objectName));
    }
}
