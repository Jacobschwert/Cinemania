import java.util.Scanner;
import java.sql.SQLException;

public class CreationController {

    FeedbackFactory feedbackFactory = new FeedbackFactory();

    public void createReview() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter review summary: ");
        String summary = scanner.next();

        System.out.println("Enter rating (1-5): ");
        int rating = scanner.nextInt();

        try {
            Review review = feedbackFactory.createReview(summary, rating);
            System.out.println("Review created: " + review.getFeedbackSummary());
        } catch (SQLException e) {
            System.out.println("Error creating review: " + e.getMessage());
        }

        scanner.close();
    }

    public void createComment() {
        
    }

    public void createContentList() {
        // implementation here
    }
}