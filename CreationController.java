import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;

public class CreationController {

    public void createReview(Content content) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter review summary: ");
        String summary = scanner.next();

        System.out.println("Enter rating (1-5): ");
        int rating = scanner.nextInt();

        Review review = new Review(summary, rating, content, MainController.user);
        System.out.println("Review created: " + review.getFeedbackSummary());

        scanner.close();
    }

    public void createComment(int feedbackID) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Review review = FeedbackFactory.getReview(feedbackID);

        System.out.println("Enter comment: ");
        String text = scanner.next();

        Comment comment = FeedbackFactory.createComment(text, review);
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
