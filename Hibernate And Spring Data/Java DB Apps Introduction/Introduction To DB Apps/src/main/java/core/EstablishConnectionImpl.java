package core;

import core.interfaces.EstablishConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class EstablishConnectionImpl implements EstablishConnection {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/minions_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private Properties properties;
    private Connection connection;

    public EstablishConnectionImpl() throws SQLException {
        this.setProperties();
        this.setConnection();
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    private void setProperties() {
        this.properties = new Properties();
        this.properties.setProperty("user", EstablishConnectionImpl.USER);
        this.properties.setProperty("password", EstablishConnectionImpl.PASSWORD);
    }

    private void setConnection() throws SQLException {
        this.connection = DriverManager.getConnection(EstablishConnectionImpl.CONNECTION_URL, this.properties);
    }


}
