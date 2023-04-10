import java.sql.*;

public class Queries {
    
    private Connector connector;
    private Connection connection;
    
    public Queries(Connector connector) {
        this.connector = connector;
        this.connection = connector.getConnection();
    }
    
    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
    
    public int executeUpdate(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }
    
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }
    
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}