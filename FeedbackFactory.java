import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackFactory {
    private static SqliteConnector db = new SqliteConnector();
    private static Connection conn = db.connect();
    
    public static Comment createComment(String text, Review reviewTarget) throws IllegalArgumentException {
        Comment comment = null;
        try {
            comment = new Comment(text, reviewTarget);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return comment;
    }
    
    public static Review createReview(String summary, int rating, Content content, Account author) throws SQLException {
        Review review = null;
        try {
            review = new Review(summary, rating, content, author);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return review;
    }

    public static Comment getComment(int feedbackID) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comment WHERE feedbackID = ?");
        stmt.setInt(1, feedbackID);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String text = rs.getString("summary");
            Review review = (Review) rs.getRef(feedbackID);
            return new Comment(text, review);
        } else {
            return null;
        }
    }

    public static Review getReview(int feedbackID) throws SQLException{
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
