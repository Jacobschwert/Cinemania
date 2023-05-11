import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Comment extends Feedback{
    private String text;
    private int likes;
    private Account feedbackAuthor;
    private int feedbackID;
    private Review reviewTarget;
    private int targetID;

    private SqliteConnector db = new SqliteConnector();
    private Connection conn = db.connect();
    private SqliteQueries query = new SqliteQueries(conn);
    private String queryString;


    public Comment(String text, Review reviewTarget) throws IllegalArgumentException {
        if (text.length() > 150) {
            throw new IllegalArgumentException("Comment cannot exceed 150 characters");
        }
        this.text = text;
        this.likes = 0;
        this.reviewTarget = reviewTarget;
        this.targetID = reviewTarget.getFeedbackID();
        this.feedbackAuthor = reviewTarget.getFeedbackAuthor();
        queryString = "INSERT INTO comment(text, likes, review_id, author_id) VALUES('" + text + "', " + likes + ", " + reviewTarget.getFeedbackID() + ", " + feedbackAuthor.getAccountNumber() + ");";
        try {
            query.executeUpdate(queryString);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    

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
    
    
    public void editFeedback() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter 'Y' to confirm you want to edit the comment or 'N' to cancel");
        String answer = scan.nextLine();
        scan.close();
        if(answer.equalsIgnoreCase("Y"))
        {
            System.out.println("Enter your new comment text: ");
            String newText = scan.nextLine();
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

    }

    public void deleteFeedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'Y' to confirm that you want to delete the comment: ");
        String response = scanner.nextLine();
        scanner.close();
        if(response.equalsIgnoreCase("Y")){
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
        return text;
    }

}