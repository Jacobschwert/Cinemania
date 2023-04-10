import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private Connection connection;

    public void connect(String dbPath) throws SQLException, ClassNotFoundException {
        // Load the SQLite JDBC driver
        Class.forName("org.sqlite.JDBC");

        // Create a connection to the specified database file
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}