import java.util.Scanner;
import java.util.ArrayList;

public class SearchController {

    // We need a way for the search controller to gain access to a Content Manager.
   
    
    public void viewReccomendations(ContentManager contentManager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Viewing Reccomendations");
        ArrayList<ContentList> recommendationLists = contentManager.getRecommendationLists();
        System.out.println("Enter a number matching a recommendation list, or, type back to go back");
        displayRecommendationListSummaries(recommendationLists);

        
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
    public static void main(String[] args){

    }
}
