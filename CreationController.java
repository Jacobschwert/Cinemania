import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;

public class CreationController {

    public void createReview() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter review summary: ");
        String summary = scanner.nextLine();

        System.out.println("Enter rating (1-5): ");
        int rating = scanner.nextInt();

        try {
            Review review = FeedbackFactory.createReview(summary, rating, null, null);
            System.out.println("Review created: " + review.getFeedbackSummary());
        } catch (SQLException e) {
            System.out.println("Error creating review: " + e.getMessage());
        }

        scanner.close();
    }

    public void createComment(int feedbackID, Account feedbackAuthor) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Review review = FeedbackFactory.getReview(feedbackID);

        System.out.println("Enter comment: ");
        String text = scanner.next();

        Comment comment = FeedbackFactory.createComment(text, review, feedbackAuthor);
        System.out.println("Comment created: " + comment.getFeedbackSummary());

        scanner.close();
    }

    public void createContentList() {
        // Prompt user for list name and description
        System.out.print("Enter list name: ");
        String listName = Main.scanner.nextLine();
        System.out.print("Enter list description: ");
        String listDescription = Main.scanner.nextLine();

        // Create new ContentList object
        MainController.cManage.createNewContentList();
        ArrayList<ContentList> lists = MainController.cManage.getContentLists();
        int newlistIndex = lists.size()-1;
        lists.get(newlistIndex).setContentListName(listName);
        lists.get(newlistIndex).setContentListDescription(listDescription);
    }
}