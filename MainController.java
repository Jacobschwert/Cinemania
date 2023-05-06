import java.util.ArrayList;

public class MainController {
    LoginController loginController = new LoginController();
    SearchController searchController = new SearchController();
    CreationController creationController = new CreationController();
    static Account user;
    static ContentManager cManage;

    Content movie1 = new Movie(
    1,
    "The Matrix",
    "A computer programmer is drawn into a rebellion against machines.",
    new String[] { "Action", "Sci-Fi" },
    new int[] { 1, 3 },
    8.7f,
    new ArrayList<Review>(),
    new String[] { "Amazon", "Google Play", "iTunes" },
    new String[] { "Amazon", "Google Play", "Vudu" },
    new String[] { "Netflix", "Hulu" }
);

Content tvShow1 = new TVShow(
    2,
    "Stranger Things",
    "In the summer of 1985, a group of friends uncover a dark secret in their small town.",
    new String[] { "Drama", "Horror", "Sci-Fi" },
    new int[] { 2, 4, 3 },
    8.8f,
    new ArrayList<Review>(),
    new String[] { "Netflix", "Hulu" },
    new String[] { "Amazon", "Google Play", "Vudu" },
    new String[] { "Netflix", "Hulu" }
);

Content movie2 = new Movie(
    3,
    "The Social Dilemma",
    "Experts sound the alarm on the dangerous impact of social networking.",
    new String[] { "Documentary" },
    new int[] { 5 },
    7.7f,
    new ArrayList<Review>(),
    new String[] { "Netflix" },
    new String[] {},
    new String[] { "Netflix" }
);

Content tvShow2 = new TVShow(
    4,
    "Fullmetal Alchemist: Brotherhood",
    "Two brothers use alchemy to try to bring their mother back to life.",
    new String[] { "Anime", "Action", "Fantasy" },
    new int[] { 6, 1, 7 },
    9.1f,
    new ArrayList<Review>(),
    new String[] { "Amazon", "Google Play" },
    new String[] { "Amazon", "Google Play", "Vudu" },
    new String[] {}
);






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
        int choice = Main.scanner.nextInt();
        
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
        
        Boolean flag = false;
        while(!flag){
            System.out.println("1: Log In");
            System.out.println("2: Log Out");
            System.out.println("3: Sign Up");
            System.out.println("4: Edit account");
            System.out.println("5: Go back");
            // Get user input
            int choice = Main.scanner.nextInt();
            Main.scanner.nextLine();
            // Call appropriate method based on user input
            if (choice == 1) {
                
                user = loginController.login();
                if(user == null)
                    System.out.println("Invalid Username and/or Password.");
                else{
                    cManage = new ContentManager(user.getCManage(), user);
                    System.out.println("Welcome back " + user.getUName() + "!");
                }
            } 
            else if (choice == 2) { //Flag should be false? Unsure if the program should break or something here.
                
                user = loginController.logout();
                cManage = null;
            }
            else if (choice == 3) {
                
                user = loginController.signup();
                cManage = new ContentManager(user.getCManage(), user);
            } 
            else if (choice == 4) {
                if(user == null){
                    System.out.println("Sign in to your account first.");
                }
                else{
                    
                    loginController.editAccount(user);  
                }
            }
            else if (choice == 5) {
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
        int choice = Main.scanner.nextInt();
        Main.scanner.nextLine();
        
        // Call appropriate method based on user input
        if (choice == 1) {
            
            //searchController.viewReccomendations();
        } 
        else if (choice == 2) {
            Boolean flag = false;
        while(!flag){
            System.out.println("1: " + movie1.getContentName());
            System.out.println("2: " + movie2.getContentName());
            System.out.println("3: " + tvShow1.getContentName());
            System.out.println("4: " + tvShow2.getContentName());

            choice = Main.scanner.nextInt();
            Main.scanner.nextLine();

            if(choice==1) {
                movie1.getContentDescription();
                System.out.println("1: Write a review?");
                System.out.println("2: Go back");
                choice = Main.scanner.nextInt();
                if(choice==1) {
                    creationController.createReview(movie1);
                }
                else if(choice==2){
                    listContentOptions();
                }
            }

            else if(choice==2) {
                movie2.getContentDescription();
                System.out.println("1: Write a review?");
                System.out.println("2: Go back");
                choice = Main.scanner.nextInt();
                if(choice==1) {
                    creationController.createReview(movie2);
                }
                else if(choice==2){
                    listContentOptions();
                }
            }

            else if(choice==3) {
                tvShow1.getContentDescription();
                System.out.println("1: Write a review?");
                System.out.println("2: Go back");
                choice = Main.scanner.nextInt();
                if(choice==1) {
                    creationController.createReview(tvShow1);
                }
                else if(choice==2){
                    listContentOptions();
                }
            }

            else if(choice==4) {
                tvShow2.getContentDescription();
                System.out.println("1: Write a review?");
                System.out.println("2: Go back");
                choice = Main.scanner.nextInt();
                if(choice==1) {
                    creationController.createReview(tvShow2);
                }
                else if(choice==2){
                    listContentOptions();
                }
            }
        }
            //System.out.println("Search for a movie\nSearch:");
            //String search = Main.scanner.nextLine();
            //searchController.searchContent(search, cManage);
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