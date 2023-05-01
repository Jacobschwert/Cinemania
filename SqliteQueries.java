import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteQueries extends Queries {
    public SqliteQueries(Connection conn) {
        super(conn);
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeUpdate(); //The int being returned is the number of rows being affected
    }

    // Use this to test out your constructer queries
    public static void main(String[] args) { 
        Account test = new Account(7,"ted", "email", "description", "password");
    }
}
