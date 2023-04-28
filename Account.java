import java.util.List;


public class Account {
    private int accountID;
    private String userName;
    private String email;
    private String description;
    private ContentManager Cmanage;
    private List<Comment> comments;
    private List<Review> reviews;

    //When I write information to the account, also write it into the DB
    public Account(int number, String userName, String contact, String description) {
        this.accountID = number;
        this.userName = userName;
        this.email = contact;
        this.description = description;
    }

    public int getAccountNumber() {
        return accountID;
    }

    public void setAccountNumber(int number) {
        this.accountID = number;
    }

    public String getUName() {
        return userName;
    }

    public void setUName(String uName) {
        this.userName = uName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String contact) {
        this.email = contact;
    }

    public void editAccount(String userName, String contact, String description) { //Do something to call deleteAccount
        this.userName = userName;
        this.email = contact;
        this.description = description;
        //Maybe ask user if they wish to delete?
    }

    private void deleteAccount(){
        //interact with db and delete row matching the accountID
    }
}
