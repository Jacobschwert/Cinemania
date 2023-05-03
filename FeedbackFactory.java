import java.sql.SQLException;
import java.util.ArrayList;

public class FeedbackFactory {
    private ArrayList<Feedback> feedbackList;
    
    public Comment createComment(String text, Review reviewTarget) throws IllegalArgumentException {
        Comment comment = null;
        try {
            comment = new Comment(text, reviewTarget);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return comment;
    }
    
    public Review createReview(String summary, int rating) throws SQLException {
        Review review = null;
        try {
            review = new Review(summary, rating);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return review;
    }
    
    public ArrayList<Feedback> getFeedbackList() {
        return feedbackList;
    }
}
