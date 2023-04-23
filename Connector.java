import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
   
public class Connector {  

    public static Connection connect() {
        Connection conn = null;  
        try {  
            // db parameters  
            String url = "jdbc:sqlite:C:/Users/jacob/Desktop/IT 326/tableDB/cinemania.db";  //You need to change this filepath to reference where you have the db saved
            // create a connection to the database  
            conn = DriverManager.getConnection(url);  
              
            System.out.println("Connection to SQLite has been established.");  
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } 
        return conn;
    }
    public static void main(String[] args) {
        Connection conn = connect();
        try {
            // execute your queries using the conn object
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}  
