import java.util.Scanner;

public class Feedback {
    private String summary;
    private int rating;
    private int likes;
    
    public Feedback(String summary, int rating) throws IllegalArgumentException {
        this(summary, rating, 0);
    }
    
    public Feedback(String summary, int rating, int likes) throws IllegalArgumentException {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating should be between 1 and 5");
        }
        if (summary.length() > 300) {
            throw new IllegalArgumentException("Summary cannot exceed 300 characters");
        }
        this.summary = summary;
        this.rating = rating;
        this.likes = likes;
    }
    
    public String getSummary() {
        return summary;
    }
    
    public int getRating() {
        return rating;
    }
    
    public int getLikes() {
        return likes;
    }
    
    public void likeFeedback() {
        this.likes++;
        System.out.println("Feedback liked. Current number of likes: " + this.likes);
    }

    public void dislikeFeedback() {
        this.likes--;
        System.out.println("Feedback disliked. Current number of likes: " + this.likes);
    }
    
    public void editFeedback() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        
        while (option != 3) {
            System.out.println("Select an option:");
            System.out.println("1. Edit summary");
            System.out.println("2. Edit rating");
            System.out.println("3. Delete review");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            
            switch (option) {
                case 1:
                    System.out.println("Enter new summary:");
                    String newSummary = scanner.nextLine();
                    if (newSummary.length() > 300) {
                        System.out.println("Summary cannot exceed 300 characters.");
                    } else {
                        this.summary = newSummary;
                        System.out.println("Summary updated successfully.");
                    }
                    break;
                    
                case 2:
                    System.out.println("Enter new rating:");
                    int newRating = scanner.nextInt();
                    if (newRating < 1 || newRating > 5) {
                        System.out.println("Rating should be between 1 and 5.");
                    } else {
                        this.rating = newRating;
                        System.out.println("Rating updated successfully.");
                    }
                    break;
                    
                case 3:
                    System.out.println("Are you sure you want to delete this review? (Y/N)");
                    String confirm = scanner.nextLine().toLowerCase();
                    if (confirm.equals("y")) {
                        System.out.println("Review deleted successfully.");
                    } else {
                        option = 0;
                    }
                    break;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}