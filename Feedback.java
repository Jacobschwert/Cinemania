public abstract class Feedback implements Likeable{
    private int feedbackID;
    private String feedbackSummary;
    private int likes;
    private Account feedbackAuthor;

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getFeedbackSummary() {
        return feedbackSummary;
    }

    public void setFeedbackSummary(String feedbackSummary) {
        this.feedbackSummary = feedbackSummary;
    }

    public int getLikes() {
        return likes;
    }

    public Account getFeedbackAuthor() {
        return feedbackAuthor;
    }

    public void setFeedbackAuthor(Account feedbackAuthor) {
        this.feedbackAuthor = feedbackAuthor;
    }

    public abstract void editFeedback();

    public abstract void deleteFeedback();

    public abstract void addLike();

    public abstract void removeLike();
}
