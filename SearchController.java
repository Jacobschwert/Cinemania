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
            displayRecommendationListSummaries(recommendationLists);
            while(choice < 1 || choice > recommendationLists.size()){
                String userInput = scanner.nextLine().trim();
                if(userInput.equalsIgnoreCase("back")){
                    viewingRecommendations = false;
                    break;
                }
                try {
                    choice = Integer.parseInt(userInput);
                    recommendationLists.get(choice);
                } 
                catch (NumberFormatException e) {
                    System.out.println("Invalid Input, please enter a number matching a recommendation list index, or, type 'back' to go back.");
                }
            }

            ContentList selectedList = recommendationLists.get(choice);
            ArrayList<Content> listContent = selectedList.getList();
            choice = 0;
            // Loop for selecting a specific piece of content in a content list
            System.out.println("Enter a number matching a piece of content, or, type 'back' to go back to selecting a list.");
            boolean isEmpty = selectedList.displayList();
            if(isEmpty) break;
            while(choice < 1 || choice > listContent.size()){
                String userInput = scanner.nextLine().trim();
                if(userInput.equalsIgnoreCase("back")){
                    break;
                }
                try {
                    choice = Integer.parseInt(userInput);
                    recommendationLists.get(choice);
                } 
                catch (NumberFormatException e) {
                    System.out.println("Invalid Input, please enter a number matching a content index, or, type 'back' to go back.");
                }
            }

            // Loop for selecting a specific content option
            // User should be able to: Create a Review and view reviews.
            // User should also be able to add content to content lists, but I'm guessing this will be implemented first
            // when the user is viewing their own content lists.
            Content selectedContent = listContent.get(choice);
            choice = 0;
            int numberOfChoices = 2;
            selectedContent.toString();
            System.out.println("Two available options: 1. Create Review or 2. View User Reviews. Please type in a number corresponding to an option index, or type 'back' to go back");
            if(isEmpty) break;
            while(choice < 1 || choice > numberOfChoices){
                String userInput = scanner.nextLine().trim();
                if(userInput.equalsIgnoreCase("back")){
                    break;
                }
                try {
                    choice = Integer.parseInt(userInput);
                    recommendationLists.get(choice);
                } 
                catch (NumberFormatException e) {
                    System.out.println("Invalid Input, please enter a number matching an option index, or, type 'back' to go back.");
                }
            }
            switch(choice){
                case 1:
                    // Begin the process of creating a review here.
                    creationController.createReview();
                    break;
                case 2:
                    //Display reviews here, then, allow the user to create a comment, probably with he help of the createComment() method
                    break;
            }

        }
    }

    public void searchUsers() {
        System.out.println("Searching Users");
    }

    public void searchContent() {
        System.out.println("Searching Content");
    }

    public void viewPinnedLists(){

    }

    // Display a generic overview of each Recommendation List, meant to help the user narrow down their content searching.
    private void displayRecommendationListSummaries(ArrayList<ContentList> recommendationLists){
        for(int i = 1; i < recommendationLists.size() + 1; i++){
            ContentList rList = recommendationLists.get(i - 1);
            System.out.println(i + ". " + rList.getContentListName() + " - " + rList.getContentListDescription());
        }
    }

    // for testing
    // public static void main(String[] args){
    //     ContentManager cManager = new ContentManager(0, null);
    //     SearchController searchController = new SearchController();
    //     searchController.viewReccomendations(cManager);
    // }
}
