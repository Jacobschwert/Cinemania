import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Account {
    private int accountID;
    private String userName;
    private String email;
    private String description;
    private String password;
    private ContentManager Cmanage;
    private List<Comment> comments;
    private List<Review> reviews;

    //This part is required to access and update the database
    private SqliteConnector db = new SqliteConnector();
    private Connection conn = db.connect();
    private SqliteQueries query = new SqliteQueries(conn);
    private String queryString;

    //When I write information to the account, also write it into the DB
    public Account(int number, String userName, String contact, String description, String password) { //Consider using setter methods here
        //Consider creating the random number to be used as the key for Cmanager here
        this.accountID = number;
        this.userName = userName;
        this.email = contact;
        this.description = description;
        this.password = password;
        String numberStr = String.valueOf(number);
        queryString = "INSERT INTO account(accountID, uName, password, email, description) VALUES(" + numberStr + ", '" + userName + "', '" + password + "', '" + email + "', '" + description + "');";
        try{
        query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int getAccountNumber() {
        return accountID;
    }

    public void setAccountNumber(int number) { //The primary key is NEVER to be changed, ask Rishi for clarification
        this.accountID = number;
    }

    public String getUName() {
        return userName;
    }

    public void setUName(String uName) {
        queryString = "UPDATE account SET uName = '" + uName + "' WHERE accountID = " + this.accountID + ";";
        try{
            query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.userName = uName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String contact) {
        queryString = "UPDATE account SET email = '" + contact + "' WHERE accountID = " + this.accountID + ";";
        try{
            query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.email = contact;
    }

    public String getDescription() {
        return userName;
    }

    public void setDescription(String description) {
        queryString = "UPDATE account SET descr = '" + description + "' WHERE accountID = " + this.accountID + ";";
        try{
            query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.description = description;
    }

    public ContentManager getCManage() {
        return this.Cmanage;
    }  
    
    public void setCManage(ContentManager Cmanage) {
        queryString = "UPDATE account SET cManager = " + Cmanage + " WHERE accountID = " + this.accountID + ";";
        try{
            query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.Cmanage = Cmanage;
    }

    public void editAccount(String userName, String contact, String description, Boolean delete) { //In controller, just pass account.getUName() when not changing
        setEmail(contact);
        setUName(userName);
        setDescription(description);
        this.description = description;
        if(delete == true){ //This will be determined in the controller class
            Scanner deleting = new Scanner(System.in); //close this
            System.out.println("Enter 'Yes' to confirm that you want to delete your account: ");
            String response = deleting.nextLine();
            deleting.close();
            System.out.println("Account is being deleted.");
            if(response.equals("Yes")){
                deleteAccount();
            }
        }
    }

    private void deleteAccount(){
        queryString = "DELETE FROM account WHERE accountID = " + this.accountID + ";";
        try{
            query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void recoverAccount(){ //Need to download the gmail jar and create a cinemania email account I think?

    }
}
