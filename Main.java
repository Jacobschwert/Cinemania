import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MainController controller = new MainController();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            // Display startup options and get user choice
            controller.listStartupOptions();

            // Ask user if they want to continue or quit
            System.out.print("Enter 'q' to quit, or any other key to continue: ");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("q")) {
                running = false;
            }
        }

        System.out.println("Goodbye!");
    }

}