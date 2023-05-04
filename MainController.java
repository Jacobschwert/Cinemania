import java.util.Scanner;

public class MainController {
    LoginController loginController = new LoginController();
    SearchController searchController = new SearchController();
    CreationController creationController = new CreationController();
    
    public void listStartupOptions() {
        // Print welcome message
        System.out.println("\n\n\n\n\n\n");
        System.out.println("                 /$$$$$$  /$$                                                       /$$                        ");
        System.out.println("        /$$/$$  /$$__  $$|__/                                                      |__/            /$$/$$      ");
        System.out.println("       |  $$$/ | $$  \\__/ /$$ /$$$$$$$   /$$$$$$  /$$$$$$/$$$$   /$$$$$$  /$$$$$$$  /$$  /$$$$$$  |  $$$/     ");
        System.out.println("       /$$$$$$$| $$      | $$| $$__  $$ /$$__  $$| $$_  $$_  $$ |____  $$| $$__  $$| $$ |____  $$ /$$$$$$$     ");
        System.out.println("      |__ $$$_/| $$      | $$| $$  \\ $$| $$$$$$$$| $$ \\ $$ \\ $$  /$$$$$$$| $$  \\ $$| $$  /$$$$$$$|__ $$$_/ ");
        System.out.println("        /$$ $$ | $$    $$| $$| $$  | $$| $$_____/| $$ | $$ | $$ /$$__  $$| $$  | $$| $$ /$$__  $$  /$$ $$      ");
        System.out.println("       |__/__/ |  $$$$$$/| $$| $$  | $$|  $$$$$$$| $$ | $$ | $$|  $$$$$$$| $$  | $$| $$|  $$$$$$$ |__/__/      ");
        System.out.println("                \\______/ |__/|__/  |__/ \\_______/|__/ |__/ |__/ \\_______/|__/  |__/|__/ \\_______/          ");                                                                               
        System.out.println("\n\n\n\n\n\n");                                                                      

        // Print options
        System.out.println("\n**************************************");
        System.out.println("*      Please Select a Choice        *");
        System.out.println("**************************************\n");

        System.out.println("1. View Your Account");
        System.out.println("2. View Content");
        System.out.println("3: Exit");
        // Get user input
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        // Call appropriate method based on user input
        if (choice == 1) {
            listAccountOptions();
        } 
        else if (choice == 2) {
            listContentOptions();
        }
        else if (choice == 3) {
            System.out.println("Goodbye!");
            System.exit(0);
        } 
        else {
            System.out.println("Invalid choice.");
        }
    }
    

    private void listAccountOptions() {
        System.out.println("1. Log In");
        System.out.println("2. Log Out");
        System.out.println("3: Sign Up");
        System.out.println("4: Go back");
        // Get user input
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        Boolean flag = false;
        // Call appropriate method based on user input
        Account user;
        ContentManager cm;
        while(!flag){
            if (choice == 1) {
                user = loginController.login();
                if(user == null)
                    System.out.println("Invalid Username and/or Password.");
                else{
                    cm = new ContentManager(user.getCManage(), user);
                    flag = true;
                }
            } 
            else if (choice == 2) {
                user = loginController.logout();
                cm = null;
                flag = true;
            }
            else if (choice == 3) {
                user = loginController.signup();
                cm = new ContentManager(user.getCManage(), user);
                flag = true;
            } 
            else if (choice == 4) {
                flag = true;
                listStartupOptions();
            }
            else {
                System.out.println("Invalid choice.");
            }
        }
    }
    
    
    private void listContentOptions() {
        System.out.println("1. View Reccomendations");
        System.out.println("2. Search Content");
        System.out.println("3: Search Users");
        System.out.println("4: Go back");
        // Get user input
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        // Call appropriate method based on user input
        if (choice == 1) {
            searchController.viewReccomendations();
        } 
        else if (choice == 2) {
            searchController.searchContent();
        }
        else if (choice == 3) {
            searchController.searchUsers();
        } 
        else if (choice == 4) {
            listStartupOptions();
        }
        else {
            System.out.println("Invalid choice.");
        }
    }
}
