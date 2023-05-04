/*
 * ContentManager
 */

import java.util.ArrayList;
import java.util.Collections;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


 public class ContentManager {
    private ArrayList<ContentList> recommendationLists;
    private int cmID;
    private Account user;
    private ArrayList<ContentList> contentLists;
    private ArrayList<ContentList> pinnedLists;
    private SqliteConnector db = new SqliteConnector();
    private Connection conn = db.connect();
    private SqliteQueries query = new SqliteQueries(conn);
    private String queryString;

    // Default constructor, probably needs changes.
    public ContentManager(int cmID, Account user)
    {
        
        this.cmID = cmID;
        this.user = user;
        
        // Maybe there should be a method for loading stored recommendationList info (if stored in the databse).
        recommendationLists = new ArrayList<ContentList>();

        // Maybe there should be a method for loading stored contentList info.
        contentLists = new ArrayList<ContentList>();

        // Maybe there should be a method for loading stored pinnedList info.
        pinnedLists = new ArrayList<ContentList>();
    }

    // Just makes an empty ContentList and adds it to the ArrayList of them.
    public void createNewContentList()
    {
        //Needs to be assigned a unique id;
        contentLists.add(new ContentList());
    }

    // Getter for contentLists.
    public ArrayList<ContentList> getContentLists()
    {
        return this.contentLists;
    }

    // Deletes a list from contentLists given the list in question.
    public void deleteList(ContentList toRemove)
    {
        // Needs to be removed from the database as well.
        contentLists.remove(toRemove);
    }

    // Enums of this type are meant to indicate special behaviour from the generateReccomendationList method.
    public enum RecommendationType {
        MOVIES_POPULAR,
        MOVIES_ACTION,
        MOVIES_ADVENTURE,
        MOVIES_COMEDY,
        MOVIES_HORROR,
        TV_POPULAR,
        TV_ACTION_ADVENTURE,
        TV_MYSTERY,
        TV_COMEDY,
        TV_SCIFI_FANTASY,
    }

    // How should we indicate whereToWatch and watchStatus for each individual piece of content?
    // Maybe information about where to watch content will simply be gathered on request?
    // Maybe watchStatus will be handeled by storing info somewhere in a database?

    /*The purpose of this method is to collect a list of relevant content based on a search query.
     * I imagine this working in a way such that the Controller class will ask the user for the query, then call this method to gather content,
     * then use some other method, either in this class or the Controller, to display the content to the console.
     * Then, I imagine the user will have a way to select an individual piece of content and view more details.
     * 
     * I'll need some way to ping The Movie Database (TMDB) to fuffill the query, might make some sort of TMBD communicator class or something for this.
     */
    public ArrayList<Content> contentSearch(String searchQuery){
        ArrayList<Content> movieList = generateMovieContentArrayList(searchQuery);
        ArrayList<Content> tvList = generateTVContentArrayList(searchQuery);
        ArrayList<Content> returnList = new ArrayList<Content>(movieList.size() + tvList.size());
        returnList.addAll(movieList);
        returnList.addAll(tvList);
        return returnList;
    }

    /* The purpose of this method is to add a specific piece of content to a specific user created contentlist.
     * I imagine it will do this by taking the unique id of the content list.
     * It will store it locally in this class, but also, store the content within the Cinemania database.
     */
    public void addToUserContentList(Content contentToAdd, int contentListID){

    }

    /*The point of this method is to get cached Recommendations or to generate Recommendations to cache and return. 
     * Maybe it would be best to output the different Recommendation list types, allow the user to pick a type, then show the corresponding
     * list for the type. Then the user could select content from that specific list to view. This should probably return a COPY of the
     * RecommendationLists ArrayList rather than the actual list.
    */
    public ArrayList<ContentList> getRecommendationLists(){
        // Right now this just generates a new set of recommendations if the manager has not stored them. Then, it returns a copy of the recommendations.
        // If we get to it, we should try to store the recommendations after initially generating them, then load them in if stored.
        // Though, now that I think about it, is storing a copy in the database necesarry?
        // Maybe it would be if we were still making this a website.
        if(recommendationLists.size() < 1){
            recommendationLists.add(generateRecommendationList(RecommendationType.MOVIES_POPULAR));
            recommendationLists.add(generateRecommendationList(RecommendationType.MOVIES_ACTION));
            recommendationLists.add(generateRecommendationList(RecommendationType.MOVIES_ADVENTURE));
            recommendationLists.add(generateRecommendationList(RecommendationType.MOVIES_COMEDY));
            recommendationLists.add(generateRecommendationList(RecommendationType.MOVIES_HORROR));
            recommendationLists.add(generateRecommendationList(RecommendationType.TV_POPULAR));
            recommendationLists.add(generateRecommendationList(RecommendationType.TV_ACTION_ADVENTURE));
            recommendationLists.add(generateRecommendationList(RecommendationType.TV_MYSTERY));
            recommendationLists.add(generateRecommendationList(RecommendationType.TV_COMEDY));
            recommendationLists.add(generateRecommendationList(RecommendationType.TV_SCIFI_FANTASY));
        }
        ArrayList<ContentList> copy = new ArrayList<>(contentLists.size());
        Collections.copy(copy, recommendationLists);
        return recommendationLists;
    }

    /* The purpose of this method is to collect a list of accounts based on a search query. I'll need a method of
     * searching the Cinemania database to find accounts with similar appearing names, hopefully listing the most similar names
     * at the beginning of the list. The Controller will likely make use of this method in some way in order to display
     * user accounts to view.
     */
    public ArrayList<Account> userSearch(String searchQuery){

    }

    /* The point of this method is to pin a contentList to the current user's account for easy access.
     * The Controller will likely be the class that passes in the proper ContentList.
     * This also means that the Controller needs a way to allow the user to view pinned lists.
     */
    public void pinList(ContentList listToPin){

    }

    /* A getter to allow the controller class access to the lists that are currently pinned.
     * This information may also be stored in the database, meaning that this information may have to be loaded
     * from the database first.
     */
    public ArrayList<ContentList> getPinnedLists(){
        return pinnedLists;
    }

    /* A helper method for the getRecommendationLists() method. This method is meant to generate a list of Recommendations that 
     * all fall under a shared category.
    */
    private ContentList generateRecommendationList(RecommendationType rType){
        String contentListName;
        String contentListDescription;
        ArrayList<Content> contentArrayList;
        // For content id, I need a way to generate a unique id.
        switch(rType){
            case MOVIES_POPULAR:
                contentArrayList = generateMovieContentArrayList(rType);
                contentListName = "Popular Movies";
                contentListDescription = "Popular Movies according to The Movie Database";    
                break;
            case MOVIES_ACTION:
                contentArrayList = generateMovieContentArrayList(rType);
                contentListName = "Action Movies";
                contentListDescription = "Popular Action Movies according to The Movie Database";     
            case MOVIES_ADVENTURE:
                contentArrayList = generateMovieContentArrayList(rType);
                contentListName = "Adventure Movies";
                contentListDescription = "Popular Adventure Movies according to The Movie Database";    
                break;
            case MOVIES_COMEDY:
                contentArrayList = generateMovieContentArrayList(rType);
                contentListName = "Comedy Movies";
                contentListDescription = "Popular Comedy Movies according to The Movie Database";  
                break;
            case MOVIES_HORROR:
                contentArrayList = generateMovieContentArrayList(rType);
                contentListName = "Horror Movies";
                contentListDescription = "Popular Horror Movies according to The Movie Database";  
                break;
            case TV_POPULAR:
                contentArrayList = generateTVContentArrayList(rType);
                contentListName = "Popular TV Shows";
                contentListDescription = "Popular TV Shows according to The Movie Database";  
                break;
            case TV_ACTION_ADVENTURE:
                contentArrayList = generateTVContentArrayList(rType);
                contentListName = "Popular Action & Adventure TV Shows";
                contentListDescription = "Popular Action & Adventure Movies according to The Movie Database";  
                break;
            case TV_MYSTERY:
                contentArrayList = generateTVContentArrayList(rType);
                contentListName = "Popular Mystery TV Shows";
                contentListDescription = "Popular Mystery Movies according to The Movie Database";  
                break;
            case TV_COMEDY:
                contentArrayList = generateTVContentArrayList(rType);
                contentListName = "Comedy TV Shows";
                contentListDescription = "Popular Comedy TV Shows according to The Movie Database";  
                break;
            case TV_SCIFI_FANTASY:
                contentArrayList = generateTVContentArrayList(rType);
                contentListName = "Sci-Fi & Fantasy";
                contentListDescription = "Popular Science Fiction and Fantasy TV Shows according to The Movie Database";  
                break;
        }

        // return a ContentList
    }

    // Helper method for generating and composing a list of movie objects into an ArrayList of Content objects relating to a search query.
    private ArrayList<Content> generateMovieContentArrayList(String searchQuery){
                TMDBMovieResultList resultList = TMDBCommunicator.getContentSearchMovieResultList(searchQuery);
                TMDBMovieResult[] movieResults = resultList.getResults();
                TMDBWatchOption[] watchOptions = TMDBCommunicator.getMovieWatchOptionsArray(resultList);
                ArrayList<Content> contentArrayList = new ArrayList<Content>(movieResults.length);
                for(int i = 0; i < movieResults.length; i++){
                    contentArrayList.add(ContentFactory.getMovieFromTMDBMovieInfo(movieResults[i], watchOptions[i]));
                }
                return contentArrayList;
    }

    // Helper method for generating and composing a list of movie recommendations into an ArrayList of Content objects.
    private ArrayList<Content> generateMovieContentArrayList(RecommendationType rType){
                TMDBMovieResultList resultList = TMDBCommunicator.getRecommendationListMovieResults(rType);
                TMDBMovieResult[] movieResults = resultList.getResults();
                TMDBWatchOption[] watchOptions = TMDBCommunicator.getMovieWatchOptionsArray(resultList);
                ArrayList<Content> contentArrayList = new ArrayList<Content>(movieResults.length);
                for(int i = 0; i < movieResults.length; i++){
                    contentArrayList.add(ContentFactory.getMovieFromTMDBMovieInfo(movieResults[i], watchOptions[i]));
                }
                return contentArrayList;
    }

    // Helper method for generating and composing a list of tv objects into an ArrayList of Content objects relating to a search query.
    private ArrayList<Content> generateTVContentArrayList(String searchQuery){
        TMDBTVResultList resultList = TMDBCommunicator.getContentSearchTVResultList(searchQuery);
        TMDBTVResult[] tvResults = resultList.getResults();
        TMDBWatchOption[] watchOptions = TMDBCommunicator.getTVWatchOptionsArray(resultList);
        ArrayList<Content> contentArrayList = new ArrayList<Content>(tvResults.length);
        for(int i = 0; i < tvResults.length; i++){
            contentArrayList.add(ContentFactory.getTVShowFromTMDBTVInfo(tvResults[i], watchOptions[i]));
        }
        return contentArrayList;
}

    // Helper method for generating and composing a list of TV Show recommendations into an ArrayList of Content objects.
    private ArrayList<Content> generateTVContentArrayList(RecommendationType rType){
        TMDBTVResultList resultList = TMDBCommunicator.getRecommendationListTVResults(rType);
        TMDBTVResult[] tvResults = resultList.getResults();
        TMDBWatchOption[] watchOptions = TMDBCommunicator.getTVWatchOptionsArray(resultList);
        ArrayList<Content> contentArrayList = new ArrayList<Content>(tvResults.length);
        for(int i = 0; i < tvResults.length; i++){
            contentArrayList.add(ContentFactory.getTVShowFromTMDBTVInfo(tvResults[i], watchOptions[i]));
        }
        return contentArrayList;
}

    // This method should take generated Recommendation lists and store them in the RecommendationLists variable.
    // This really might not be needed as a method, this might just be handled by the displayReccommendations method.
    private void cacheRecommendations(){

    }

    //The addWatchStatus() changeWatchStatus() and getWatchStatus() methods all encapsulate Use Case Edit Watch Status
    public void addWatchStatus(int num, int cID){ //These potentially need to be changed to account for movie/tv show
        int uID = this.user.getAccountNumber();
        queryString = "INSERT INTO watchStatus(uID, cID, status) VALUES(" + uID + ", " + cID + ", " + num + ");";
        try{
        query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void changeWatchStatus(int num, int cID){
        int uID = this.user.getAccountNumber();
        queryString = "UPDATE watchStatus Set status = " + num + " WHERE uID = " + uID +" AND cID = " + cID + ";";
        try{
        query.executeUpdate(queryString);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public int getWatchStatus(int cID){
        int uID = this.user.getAccountNumber();
        queryString = "SELECT status FROM watchStatus WHERE uID = " + uID +" AND cID = " + cID + ";";
        int result = 0;
        ResultSet rs;
        try{
            rs = query.executeQuery(queryString);
            if (rs.next()) {
                result = rs.getInt("status");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

}