import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Random;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class Account {
    private int accountID;
    private String userName;
    private String email;
    private String description;
    private String password;
    private int Cmanage;
    private String comments;
    private String reviews;

    //This part is required to access and update the database
    private SqliteConnector db = new SqliteConnector();
    private Connection conn = db.connect();
    private SqliteQueries query = new SqliteQueries(conn);
    private String queryString;

    public Account(String uName, String password){ //This constructor is used for logging in, the confirmation is done in the controller though
        queryString = "SELECT * FROM account WHERE uName = '" + uName + "' AND password = '" + password + "';" ;
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
                this.comments = rs.getString("comments");
                this.reviews = rs.getString("reviews");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    //Use case Create Account
    public Account(String userName, String contact, String description, String password) {
        Random rand = new Random();
        Boolean moveOn = false;
        int number = 0;
        while (moveOn == false){ //Generate accountID
            number = rand.nextInt(88888) + 11111; //This gives a range of 11111 - 99999
            queryString = "SELECT accountID FROM account WHERE accountID = " + number + ";" ;
            ResultSet rs;
            try{
                rs = query.executeQuery(queryString);
                if (!rs.next()) {
                    this.accountID = number;
                    moveOn = true;
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        moveOn = false;
        int number2 = 0;
        while (moveOn == false){ //Generate cmID
            number2 = rand.nextInt(88888) + 11111; //This gives a range of 11111 - 99999
            queryString = "SELECT cmID FROM contentManager WHERE cmID = " + number2 + ";" ;
            ResultSet rs;
            try{
                rs = query.executeQuery(queryString);
                if (!rs.next()) {
                    this.Cmanage = number2;
                    moveOn = true;
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        this.userName = userName;
        this.email = contact;
        this.description = description;
        this.password = password;
        String numberStr = String.valueOf(number);
        queryString = "INSERT INTO account(accountID, uName, password, email, description, cManager) VALUES(" + numberStr + ", '" + userName + "', '" + password + "', '" + email + "', '" + description + "', " + Cmanage + ");";
        try{
        query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int getAccountNumber() {
        return this.accountID;
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
        return this.userName;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        queryString = "UPDATE account SET password = '" + password + "' WHERE accountID = " + this.accountID + ";";
        try{
            query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.password = password;
    }

    public String getEmail() {
        return this.email;
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
        return this.description;
    }

    public void setDescription(String description) {
        queryString = "UPDATE account SET description = '" + description + "' WHERE accountID = " + this.accountID + ";";
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

    public String getReviews() { //The idea for this and Comments is that it will be a continuous string hold the id of every comment associated with this account
        return this.reviews;
    }

    public void setReviews(String reviews) {
        queryString = "UPDATE account SET reviews = '" + reviews + "' WHERE accountID = " + this.accountID + ";";
        try{
            query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.reviews = reviews;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        queryString = "UPDATE account SET comments = '" + comments + "' WHERE accountID = " + this.accountID + ";";
        try{
            query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
        this.comments = comments;
    }


    //Use case Edit Account
    public void editAccount(String userName, String contact, String description, String password, Boolean delete) {
        setEmail(contact);
        setUName(userName);
        setDescription(description);
        setPassword(password);
        this.description = description;
        if(delete == true){ //This will be determined in the controller class
            Scanner deleting = new Scanner(System.in); //close this
            System.out.print("Enter 'Yes' to confirm that you want to delete your account: ");
            String response = deleting.nextLine();
            deleting.close();
            System.out.println("Account is being deleted.");
            if(response.equals("Yes")){
                deleteAccount();
            }
        }
    }


    //Use case Delete Account
    private void deleteAccount(){
        queryString = "DELETE FROM account WHERE accountID = " + this.accountID + ";";
        try{
            query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }


    //Use case Recover Account
    public void recoverAccount(){ //Will test later, concept SHOULD work though
        Random rand = new Random();
        this.password = Integer.toString(rand.nextInt(88888) + 11111);
        queryString = "UPDATE account SET password = " + this.password + " WHERE accountID = " + this.accountID + ";";
        try{
            query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create the session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cinemaniarecover@gmail.com", "eafmwtcaiqhdwwdb");            
            }
        });

        // Create the email message
        try{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("cinemaniarecover@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.email));
        message.setSubject("Password Recovery");
        message.setText("Dear User, \n\nYour password has now been changed to: " + this.password + ". Please either remember this one, or change it when you sign in.");

        // Send the email
        Transport.send(message);

        System.out.println("Email sent successfully.");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}