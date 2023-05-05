import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class Review extends Feedback{
    private String summary;
    private int rating;
    private int likes;
    private Account feedbackAuthor;
    private int feedbackID;
    private ArrayList<Comment> commentList;
    private Content reviewTarget;
    private int targetID;

    private SqliteConnector db = new SqliteConnector();
    private Connection conn = db.connect();
    private SqliteQueries query = new SqliteQueries(conn);
    private String queryString;
    

    //Contructor for new review object
    public Review(String summary, int rating) throws IllegalArgumentException {
        this(summary, rating, 0);
        targetID = reviewTarget.getContentID();
        queryString = "INSERT INTO review(text, likes, review_id, author_id) VALUES('" + summary + "', " + likes + ", " + targetID + ", " + feedbackAuthor.getAccountNumber() + ");";
        try {
            query.executeUpdate(queryString);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Constructor for review object being pulled from database
    public Review(String summary, int rating, int likes) throws IllegalArgumentException {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating should be between 1 and 5");
        }
        if (summary.length() > 300) {
            throw new IllegalArgumentException("Summary cannot exceed 300 characters");
        }
        this.summary = summary;
        this.rating = rating;
        this.likes = likes;
        targetID = reviewTarget.getContentID();

    }

    //Method for adding likes to a review
    @Override
    public void addLike() {
        this.likes++;
        queryString = "UPDATE review SET likes = " + likes + " WHERE feedback_id = " + feedbackID + ";";
        try {
            query.executeUpdate(queryString);
            System.out.println("Like added successfully.");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }  

    //Method for removing likes from a review
    @Override
    public void removeLike() {
        if (this.likes > 0) {
            this.likes--;
            queryString = "UPDATE review SET likes = " + likes + " WHERE feedback_id = " + feedbackID + ";";
            try {
                query.executeUpdate(queryString);
                System.out.println("Like removed successfully.");
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    //Method for deleting a review from the database
    @Override
    public void deleteFeedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'Y' to confirm that you want to delete the comment: ");
        String response = scanner.nextLine();
        scanner.close();
        if(response.equalsIgnoreCase("Y")){
            queryString = "DELETE FROM review WHERE feedback_id = " + feedbackID + ";";
            try{
                query.executeUpdate(queryString);
                System.out.println("Review has been deleted successfully.");
            } catch(SQLException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Review deletion has been cancelled.");
        }
    }

    //Method for editing the summary and rating for a review and updating the database
    @Override
    public void editFeedback() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'Y' to confirm you want to edit the review");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            System.out.println("Enter new summary:");
            String newSummary = scanner.nextLine();
            int newRating;
            while (true) {
                System.out.println("Enter new rating:");
                newRating = scanner.nextInt();
                if (newRating >= 1 && newRating <= 5) {
                    break;
                }
                System.out.println("Invalid rating. Rating should be between 1 and 5");
            }
            this.summary = newSummary;
            this.rating = newRating;
            queryString = "UPDATE review SET text = '" + newSummary + "', rating = " + newRating + " WHERE feedback_id = " + feedbackID + ";";
            try {
                query.executeUpdate(queryString);
                System.out.println("Review has been edited successfully");
            } catch(SQLException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Review edit has been cancelled");
        }
        scanner.close();
    }

    //Method adds a comment that gets created to its list of comments
    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }

    //Method lists all comments left on a review
    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    //Getters and setters
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public void setFeedbackSummary(String summary) {
        this.summary = summary;
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

    @Override
    public String getFeedbackSummary() {
        return summary;
    }
    
    @Override
    public int getLikes() {
        return likes;
    }

    //Overridden toString method that outputs the summary and rating of a review, along with its list of comments left on it
    @Override
    public String toString() {
        String comments = "";
        if(commentList != null && commentList.size() > 0) {
            comments = "\nComments:\n";
            for(Comment c : commentList) {
                comments += "\t" + c.toString() + "\n";
            }
        }
        return "Summary: " + summary + "\n" + "Rating: " + rating + comments;
    }
    
}