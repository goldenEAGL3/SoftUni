package core.interfaces;


import java.io.IOException;
import java.sql.SQLException;

public interface CommandHandler {
    void handle(String command) throws IOException, SQLException;
}
