import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Review> reviews = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int option = 0;
        while (option != 3) {
            System.out.println("Select an option:");
            System.out.println("1. View Reviews");
            System.out.println("2. Create Reviews");
            System.out.println("3. Exit");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    if (reviews.isEmpty()) {
                        System.out.println("No reviews available.");
                    } else {
                        System.out.println("Select a review to view:");
                        for (int i = 0; i < reviews.size(); i++) {
                            System.out.println((i + 1) + ". " + reviews.get(i).getFeedbackSummary());
                        }
                        int reviewOption = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        if (reviewOption > 0 && reviewOption <= reviews.size()) {
                            Review selectedReview = reviews.get(reviewOption - 1);
                            System.out.println("Selected review: " + selectedReview.toString());

                            System.out.println("Enter a comment or type 'exit' to go back:");
                            String comment = scanner.nextLine();
                            if (!comment.equals("exit")) {
                                Comment newComment = new Comment();
                                selectedReview.addComment(newComment);
                                System.out.println("Comment added successfully.");
                            }
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                    }
                    break;

                case 2:
                    System.out.println("Enter review summary:");
                    String summary = scanner.nextLine();
                    System.out.println("Enter rating (1-5):");
                    int rating = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    Review newReview = new Review(summary, rating);
                    reviews.add(newReview);
                    System.out.println("Review created successfully.");
                    break;

                case 3:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}