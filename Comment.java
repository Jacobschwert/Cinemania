import java.util.Scanner;

public class Comment {
    private String text;
    private int likes;

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

    public void likeComment() {
        this.likes++;
    }

    public void unlikeComment() {
        if (this.likes > 0) {
            this.likes--;
        }
    }

    public void editComment(String newText) {
        this.text = newText;
    }

   public void deleteComment() {
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

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLikes() {
        return this.likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
