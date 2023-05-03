import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContentManagerPlaceHolder {
    private SqliteConnector db = new SqliteConnector();
    private Connection conn = db.connect();
    private SqliteQueries query = new SqliteQueries(conn);
    private String queryString;

    private Account user;
    

    //The addWatchStatus() changeWatchStatus() and getWatchStatus() methods all encapsulate Use Case Edit Watch Status
    public void addWatchStatus(int num, int cID){ //These potentially need to be changed to account for movie/tv show
        int uID = this.user.getAccountNumber();
        queryString = "INSERT INTO watchStatus(uID, cID, status) VALUES(" + uID + ", " + cID + ", " + num + ");";
        try{
        query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void changeWatchStatus(int num, int cID){
        int uID = this.user.getAccountNumber();
        queryString = "UPDATE watchStatus Set status = " + num + " WHERE uID = " + uID +" AND cID = " + cID + ";";
        try{
        query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public int getWatchStatus(int num, int cID){
        int uID = this.user.getAccountNumber();
        queryString = "SELECT status FROM watchStatus WHERE uID = " + uID +" AND cID = " + cID + ";";
        int result = 0;
        ResultSet rs;
        try{
            rs = query.executeQuery(queryString);
            if (rs.next()) {
                result = rs.getInt("status");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String args[]){
        //Add code to test above methods
    }
}
