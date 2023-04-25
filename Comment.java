import java.util.Scanner;

public class Comment extends Feedback{
    private String text;
    private int likes;
    private Account feedbackAuthor;
    private int feedbackID;

    public Comment() {
        this.text = "";
        this.likes = 0;
    }

    public void createComment() {
      Scanner input = new Scanner(System.in);
      String text = input.nextLine();
      this.text = text;
      this.likes = 0;
      input.close();
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