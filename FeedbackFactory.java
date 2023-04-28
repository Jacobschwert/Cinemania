import java.util.ArrayList;

public class FeedbackFactory {
    private int feedbackID;
    private ArrayList<Feedback> feedbackList;
    
    public FeedbackFactory() {
        this.feedbackID = 0;
        this.feedbackList = new ArrayList<Feedback>();
    }
    
    public Comment createComment() {
        Comment comment = new Comment();
        comment.createComment();
        comment.setFeedbackID(feedbackID);
        feedbackID++;
        feedbackList.add(comment);
        return comment;
    }
    
    public Review createReview(String summary, int rating) {
        Review review = null;
        try {
            review = new Review(summary, rating);
            review.setFeedbackID(feedbackID);
            feedbackID++;
            feedbackList.add(review);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return review;
    }
    
    public ArrayList<Feedback> getFeedbackList() {
        return feedbackList;
    }
}
