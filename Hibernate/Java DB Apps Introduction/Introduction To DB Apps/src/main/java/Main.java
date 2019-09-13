import core.Engine;
import core.EstablishConnectionImpl;

import java.sql.SQLException;
public class Main {
    public static void main(String[] args) throws SQLException {
        EstablishConnectionImpl connection = new EstablishConnectionImpl();
        Engine engine = new Engine(connection.getConnection());
        engine.run();
    }
}
