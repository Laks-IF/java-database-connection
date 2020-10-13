package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

    public static String getConnectionUri(ConnectionConfig config) {
        return "jdbc:mysql://"
                + (config.getHost())
                + ":"
                + (config.getPort())
                + "/"
                + (config.getDatabase()) + "?useTimezone=true&serverTimezone=UTC";
    }

    public static Connection getConnection(ConnectionConfig config) {
        Connection connection;

        final String uri = getConnectionUri(config);

        // Nota:
        // Class.forName("driver.bla.bla") - Esse é o carinha que pode lançar uma
        // ClassNotFoundException()
        // Como não estou chamando ele (pois instalei o driver manualmente)
        // Então o catch(ClassNotFoundException e) { .... } não pôde
        // ser implementado, e mesmo que pudesse, seria desnecessário;
        try {
            connection = DriverManager.getConnection(uri, config.getUser(), config.getPassword());
        } catch (SQLException e) {
            return null;
        }

        return connection;
    }

    public static Statement getStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            return null;
        }
    }

}
