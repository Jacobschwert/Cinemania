import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {
    public static ResultSet executeQuery(String sql) throws SQLException {
        Connection conn = Connector.connect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeQuery();
    }

    public static int executeUpdate(String sql) throws SQLException {
        Connection conn = Connector.connect();
        PreparedStatement stmt = conn.prepareStatement(sql);
        return stmt.executeUpdate();
    }
    
}
