import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Queries {
    protected Connection conn;
    
    public Queries(Connection conn) {
        this.conn = conn;
    }
    
    public abstract ResultSet executeQuery(String sql) throws SQLException;
    public abstract int executeUpdate(String sql) throws SQLException;
}