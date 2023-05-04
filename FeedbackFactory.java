import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackFactory {
    private SqliteConnector db = new SqliteConnector();
    private Connection conn = db.connect();
    
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

    public Comment getComment() {
        return this.getComment();
    }

    public Review getReview(int feedbackID) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reviews WHERE feedbackID = ?");
        stmt.setInt(1, feedbackID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String summary = rs.getString("summary");
            int rating = rs.getInt("rating");
            int likes = rs.getInt("likes");
            return new Review(summary, rating, likes);
        } else {
            return null;
        }
    }
}
