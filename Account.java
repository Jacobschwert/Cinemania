import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Random;

public class Account {
    private int accountID;
    private String userName;
    private String email;
    private String description;
    private String password;
    private int Cmanage;
    private int comments;
    private int reviews;

    //This part is required to access and update the database
    private SqliteConnector db = new SqliteConnector();
    private Connection conn = db.connect();
    private SqliteQueries query = new SqliteQueries(conn);
    private String queryString;

    public Account(String uName, String password){ //This constructor is used for logging in, the confirmation is done in the controller though
        queryString = "SELECT * FROM account WHERE uName = '" + uName + "';" ;
        this.userName = uName;
        this.password = password;
        ResultSet rs;
        try{
            rs = query.executeQuery(queryString);
            if (rs.next()) {
                this.accountID = rs.getInt("accountID");
                this.email = rs.getString("email");
                this.description = rs.getString("description");
                this.Cmanage = rs.getInt("cManager");
                this.comments = rs.getInt("comments");
                this.reviews = rs.getInt("reviews");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //When I write information to the account, also write it into the DB
    public Account(String userName, String contact, String description, String password) { //Consider using setter methods here
        //Consider creating the random number to be used as the key for Cmanager here
        Random rand = new Random();
        Boolean moveOn = false;
        int number = 0;
        while (moveOn == false){
            number = rand.nextInt(8888) + 1111; //This gives a range of 1111 - 9999, which should
            //Select query for above number, if found do nothing, else assign to accountID and moveOne = true
        }
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

    public void setAccountNumber(int number) { //This should NEVER be called, but I am adding functionality regardless
        queryString = "UPDATE account SET accountID = '" + number + "' WHERE uName = " + this.userName + ";";
        try{
            query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
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

    public int getCManage() {
        return this.Cmanage;
    }  
    
    public void setCManage(int Cmanage) {
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
        this.password = "1234567890";
        queryString = "UPDATE account SET password = " + this.password + " WHERE accountID = " + this.accountID + ";";
        try{
            query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
