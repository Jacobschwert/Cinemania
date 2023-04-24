public interface Feedback {
    int getFeedbackID();
    void setFeedbackID(int feedbackID);

    String getFeedbackSummary();
    void setFeedbackSummary(String feedbackSummary);

    int getLikes();

    Account getFeedbackAuthor();
    void setFeedbackAuthor(Account feedbackAuthor);

    void editFeedback();

    void deleteFeedback();

    void likeFeedback();
    void dislikeFeedback();
}