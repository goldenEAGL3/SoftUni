package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static constantMessages.MessagesAndQueries.SELECT_GET_VILLAINS_NAME_BY_COUNT_QUERY;

public class GetVillainsName extends CommandImpl {

    public GetVillainsName(Connection connection, BufferedReader reader) {
        super(connection, reader);
    }

    @Override
    public void execute() throws SQLException, IOException {

        PreparedStatement getVillainsNamesStatement = this.getConnection().prepareStatement(SELECT_GET_VILLAINS_NAME_BY_COUNT_QUERY);

        int valueOfCount = Integer.parseInt(this.getReader().readLine());

        getVillainsNamesStatement.setInt(1, valueOfCount);

        ResultSet result = getVillainsNamesStatement.executeQuery();

        while (result.next()) {
            System.out.println(String.format("%s %d",
                    result.getString("name"),
                    result.getInt("count")));
        }
    }
}
