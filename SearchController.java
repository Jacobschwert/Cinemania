import java.util.Scanner;
import java.util.ArrayList;

public class SearchController {

    // We need a way for the search controller to gain access to a Content Manager.
    public void viewRecommendations(ContentManager contentManager, CreationController creationController) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Viewing Reccomendations");
        ArrayList<ContentList> recommendationLists = contentManager.getRecommendationLists();
        // I don't think this if statement should occur.
        if(recommendationLists.size() < 1){
            System.out.println("No lists were generated.");
            return;
        }

        boolean viewingRecommendations = true;
        while(viewingRecommendations){
            int choice = 0;
            // Loop for selecting a specific recommendation List.
            System.out.println("Enter a number matching a recommendation list, or, type 'back' to go back to the main menu");
            displayContentListSummaries(recommendationLists);
            while(choice < 1 || choice > recommendationLists.size()){
                String userInput = scanner.nextLine().trim();
                if(userInput.equalsIgnoreCase("back")){
                    viewingRecommendations = false;
                    break;
                }
                try {
                    choice = Integer.parseInt(userInput);
                } 
                catch (NumberFormatException e) {
                    System.out.println("Invalid Input, please enter a number matching a recommendation list index, or, type 'back' to go back.");
                }
            }
            if(viewingRecommendations == false) break;
            ContentList selectedList = recommendationLists.get(choice - 1);
            ArrayList<Content> listContent = selectedList.getList();
            choice = 0;
            // Loop for selecting a specific piece of content in a content list
            boolean isEmpty = !selectedList.displayList();
            if(isEmpty) break;
            System.out.println("Enter a number matching a piece of content, or, type 'back' to go back to selecting a list.");
            while(choice < 1 || choice > listContent.size()){
                String userInput = scanner.nextLine().trim();
                if(userInput.equalsIgnoreCase("back")){
                    break;
                }
                try {
                    choice = Integer.parseInt(userInput);
                } 
                catch (NumberFormatException e) {
                    System.out.println("Invalid Input, please enter a number matching a content index, or, type 'back' to go back.");
                }
            }

            // Loop for selecting a specific content option
            // User should be able to: Create a Review, view reviews, and get access to watch status choices for a piece of content.
            // User should also be able to add content to content lists, but I'm guessing this will be implemented first
            // when the user is viewing their own content lists. (We could also give the user an option here to view their content lists here, then select one to add to)
            Content selectedContent = listContent.get(choice - 1);
            choice = 0;
            int numberOfChoices = 2;
            System.out.println(selectedContent.toString()); // toString method will display necesarry information about the selected piece of content.
            System.out.println("Two available options: 1. Create Review or 2. View User Reviews. Please type in a number corresponding to an option index, or type 'back' to go back");
            if(isEmpty) break;
            while(choice < 1 || choice > numberOfChoices){
                String userInput = scanner.nextLine().trim();
                if(userInput.equalsIgnoreCase("back")){
                    break;
                }
                try {
                    choice = Integer.parseInt(userInput);
                } 
                catch (NumberFormatException e) {
                    System.out.println("Invalid Input, please enter a number matching an option index, or, type 'back' to go back.");
                }
            }
            switch(choice){
                case 1:
                    // Begin the process of creating a review here.
                    // creationController.createReview();
                    break;
                case 2:
                    // Display reviews here, then, allow the user to pick a review
                    // Allow the user to like the review, unlike the review, create a comment, or view comments.
                    // If the user chooses to view comments, display the comments (numbering each comment),
                    // Allow the user to pick a comment, allow the user to like the comment or unlike the comment.
                    // Maybe it would be best if we gave reviews and comments their own toString methods and used those to help out with this part?
                    break;
                default:
                    break;
            }

        }
    }

    public void searchUsers() {
        System.out.println("Searching Users");
        // This method is going to call the userSearch method on the ContentManager and get an ArrayList of Accounts.
        // Show names, descriptions, content lists, potentially pinned lists.
        // There is a displayContentListSummaries() method
        // ContentList objects actually a method on them for displaying them called displayList(), will display a content list and a numbered list of their content.
        // When viewing content lists, viewing the content in the lists is not absolutely required.
        
    }


    // I should probably change this so that the method gathers the search query on its own.
    public void searchContent(String searchQuery, ContentManager contentManager) {
        System.out.println("Searching Content");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Content> gatheredContent = contentManager.contentSearch(searchQuery);
        if(gatheredContent.size() < 1){
            System.out.println("No content was found for your query.");
            return;
        }

        boolean searchingContent = true;
        while(searchingContent){
            int choice = 0;
            // Loop for selecting a specific piece of content in an ArrayList of Content
            System.out.println("Enter a number matching a piece of content, or, type 'back' to go back to the main menu.");
            displayContentSearchSummaries(gatheredContent);
            while(choice < 1 || choice > gatheredContent.size()){
                String userInput = scanner.nextLine().trim();
                if(userInput.equalsIgnoreCase("back")){
                    searchingContent = false;
                    break;
                }
                try {
                    choice = Integer.parseInt(userInput);
                } 
                catch (NumberFormatException e) {
                    System.out.println("Invalid Input, please enter a number matching a content index, or, type 'back' to go back.");
                }
            }
            // To be continued
        }

    }

    // Calls ContentManager.getPinnedLists() Gives you an ArrayList of contentList objects.
    public void viewPinnedLists(){
        // view titles and descriptions of pinned lists, view content within if theres time.

    }

    // Display a generic overview of each Recommendation List, meant to help the user narrow down their content searching.
    private void displayContentListSummaries(ArrayList<ContentList> contentListSummaries){
        for(int i = 1; i < contentListSummaries.size() + 1; i++){
            ContentList rList = contentListSummaries.get(i - 1);
            System.out.println(i + ". " + rList.getContentListName() + " - " + rList.getContentListDescription());
        }
    }

    // Display a generic overview of each piece of content in an ArrayList of content.
    private void displayContentSearchSummaries(ArrayList<Content> gatheredContent){
        for(int i = 1; i < gatheredContent.size() + 1; i++){
            Content content = gatheredContent.get(i - 1);
            System.out.println(i + ". " + content.getContentName() + " - " + content.getContentDescription());
        }
    }

    // Display the reviews related to a specific piece of content.
    // Meant to allow the user to view reviews ona piece of content.
    // These reviews should probably be numbered, so that the user can
    // select a review to like, comment, or unlike.
    private void displayContentReviews(Content content){

    }

    //for testing
    public static void main(String[] args){
        ContentManager cManager = new ContentManager(0, null);
        SearchController searchController = new SearchController();
        CreationController creationController = new CreationController();
        searchController.viewRecommendations(cManager, creationController);
    }
}