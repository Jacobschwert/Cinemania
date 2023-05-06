import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Comment extends Feedback{
    private String text;
    private int likes;
    private Account feedbackAuthor;
    private ArrayList<Comment> commentList;
    private int feedbackID;
    private Review commentTarget;
    private int targetID;

    private SqliteConnector db = new SqliteConnector();
    private Connection conn = db.connect();
    private SqliteQueries query = new SqliteQueries(conn);
    private String queryString;

    //Creates a comment on a specific Review
    public Comment(String text, Review commentTarget, Account feedbackAuthor) throws IllegalArgumentException {
        if (text.length() > 150) {
            throw new IllegalArgumentException("Comment cannot exceed 150 characters");
        }
        this.text = text;
        this.likes = 0;
        targetID = commentTarget.getFeedbackID();
        this.feedbackAuthor = feedbackAuthor;
        queryString = "INSERT INTO comment(text, likes, review_ID, author_id) VALUES('" + text + "', " + likes + ", " + commentTarget.getFeedbackID() + ", " + feedbackAuthor.getAccountNumber() + ");";
        try {
            query.executeUpdate(queryString);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Adds a like to a comment
    public void addLike() {
        this.likes++;
        queryString = "UPDATE comment SET likes = " + likes + " WHERE feedback_id = " + feedbackID + ";";
        try {
            query.executeUpdate(queryString);
            System.out.println("Like added successfully.");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }    
    //Removes a like from a comment
    public void removeLike() {
        if (this.likes > 0) {
            this.likes--;
            queryString = "UPDATE comment SET likes = " + likes + " WHERE feedback_id = " + feedbackID + ";";
            try {
                query.executeUpdate(queryString);
                System.out.println("Like removed successfully.");
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Edits a comment to have new text
    public void editFeedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'Y' to confirm you want to edit the comment or 'N' to cancel");
        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("Y"))
        {
            System.out.println("Enter your new comment text: ");
            String newText = scanner.nextLine();
            this.text = newText;
            queryString = "UPDATE comment SET text = '" + newText + "' WHERE feedback_id = " + feedbackID + ";";
            try{
                query.executeUpdate(queryString);
                System.out.println("Comment has been edited successfully");
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Comment edit has been cancelled");
        }
        scanner.close();
    }
    //Deletes a comment
    public void deleteFeedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'Y' to confirm that you want to delete the comment: ");
        String response = scanner.nextLine();
        scanner.close();
        if(response.equalsIgnoreCase("Y")){
            setFeedbackSummary(null);
            setLikes(0);
            queryString = "DELETE FROM comment WHERE feedback_id = " + feedbackID + ";";
            try{
                query.executeUpdate(queryString);
                System.out.println("Comment has been deleted successfully.");
            } catch(SQLException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Comment deletion has been cancelled.");
        }
    }

    public void generateID() {
        Random rand = new Random();
        Boolean moveOn = false;
        int number = 0;
        while (moveOn == false){ //Generate accountID
            number = rand.nextInt(88888) + 11111; //This gives a range of 11111 - 99999
            queryString = "SELECT feedbackID FROM account WHERE feedbackID = " + number + ";" ;
            ResultSet rs;
            try{
                rs = query.executeQuery(queryString);
                if (!rs.next()) {
                    this.feedbackID = number;
                    moveOn = true;
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
     //Method adds a comment that gets created to its list of comments
     public void addComment(Comment comment) {
        this.commentList.add(comment);
    }

    //Method lists all comments left on a review
    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    // Getters and setters

    public String getFeedbackSummary() {
        return this.text;
    }

    public void setFeedbackSummary(String text) {
        this.text = text;
    }

    public int getLikes() {
        return this.likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public void setFeedbackAuthor(Account feedbackAuthor) {
        this.feedbackAuthor = feedbackAuthor;
    }

    @Override
    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    @Override
    public int getFeedbackID() {
        return feedbackID;
    }

    @Override
    public Account getFeedbackAuthor() {
        return feedbackAuthor;
    }

    public String toString() {
        return "Comment by " + feedbackAuthor.getUName() + ": " + text + " (Likes: " + likes + ")";

        
    }

}
