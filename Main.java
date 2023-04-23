import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Feedback feedback = null;
        
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Create feedback");
            System.out.println("2. View feedback");
            System.out.println("3. Exit");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            
            switch (option) {
                case 1:
                    System.out.println("Enter feedback summary:");
                    String summary = scanner.nextLine();
                    
                    System.out.println("Enter feedback rating (1-5):");
                    int rating = scanner.nextInt();
                    scanner.nextLine();
                    
                    try {
                        feedback = new Feedback(summary, rating);
                        System.out.println("Feedback created successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Failed to create feedback: " + e.getMessage());
                    }
                    break;
                    
                case 2:
                    if (feedback == null) {
                        System.out.println("No feedback to view.");
                    } else {
                        System.out.println("Feedback summary: " + feedback.getSummary());
                        System.out.println("Feedback rating: " + feedback.getRating());
                        System.out.println("Feedback likes: " + feedback.getLikes());
                        
                        System.out.println("Select an option:");
                        if (feedback.getLikes() == 0) {
                            System.out.println("1. Like feedback");
                        } else {
                            System.out.println("1. Dislike feedback");
                        }
                        System.out.println("2. Edit feedback");
                        int subOption = scanner.nextInt();
                        scanner.nextLine();
                        
                        switch (subOption) {
                            case 1:
                                if (feedback.getLikes() == 0) {
                                    feedback.likeFeedback();
                                } else {
                                    feedback.dislikeFeedback();
                                }
                                break;
                                
                            case 2:
                                feedback.editFeedback();
                                break;
                                
                            default:
                                System.out.println("Invalid option. Please try again.");
                                break;
                        }
                    }
                    break;
                    
                case 3:
                    System.out.println("Exiting program.");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
