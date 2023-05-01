import java.util.Scanner;

public class Comment extends Feedback{
    private String text;
    private int likes;
    private Account feedbackAuthor;
    private int feedbackID;
    private Review reviewTarget;
    private int targetID;

    public Comment(String text) throws IllegalArgumentException {
        if (text.length() > 150) {
            throw new IllegalArgumentException("Comment cannot exceed 150 characters");
        }
        this.text = text;
        this.likes = 0;
        targetID = reviewTarget.getFeedbackID();
    }

    public void addLike() {
        this.likes++;
    }

    public void removeLike() {
        if (this.likes > 0) {
            this.likes--;
        }
    }

    public void editFeedback() {
        //this.text = newText;
    }

   public void deleteFeedback() {
        Scanner input = new Scanner(System.in);
        System.out.println("Are you sure you want to delete your comment? (y/n)");
        String confirm = input.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            this.text = "";
            this.likes = 0;
            System.out.println("Comment successfully deleted.");
        } else {
            System.out.println("Comment not deleted.");
        }
        input.close();
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
