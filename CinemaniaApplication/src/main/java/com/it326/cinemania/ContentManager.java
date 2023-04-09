package com.it326.cinemania;
import java.util.ArrayList;

public class ContentManager {
    private ArrayList<ContentList> reccomendationLists;
    private int cmID;
    private Account user;
    private ArrayList<ContentList> contentLists;
    private ArrayList<ContentList> pinnedLists;

    // Enums of this type are meant to indicate special behaviour from the generateReccomendationList class.
    public enum reccomendationType {
        POPULAR,
        ACTION,
        ADVENTURE,
        COMEDY,
        HORROR,
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

    }

    /* A helper method for the displayReccomendations() method. This method is meant to generate a list of reccomendations that 
     * all fall under a shared category. This method will likely also need data from TMDB and may also use some sort of communicator class.
    */
    private ContentList generateReccomendationList(reccomendationType rType){
        switch(rType){
            case POPULAR:
                break;
            case ACTION:
                break;
            case ADVENTURE:
                break;
            case COMEDY:
                break;
            case HORROR:
                break;
        }
    }

    // This method should take generated reccomendation lists and store them in the reccomendationLists variable.
    // This really might not be needed as a method, this might just be handled by the displayReccommendations method.
    private void cacheReccomendations(){

    }

    /*The point of this method is to get cached reccomendations or to generate reccomendations to cache and return. 
     * Maybe it would be best to output the different reccomendation list types, allow the user to pick a type, then show the corresponding
     * list for the type. Then the user could select content from that specific list to view. This should probably return a COPY of the
     * reccomendationLists ArrayList rather than the actual list.
    */
    public ArrayList<ContentList> getReccomendationLists(){
        return reccomendationLists;
    }

    /* The purpose of this method is to collect a list of accounts based on a search query. I'll need a method of
     * searching the Cinemania database to find accounts with similar appearing names, hopefully listing the most similar names
     * at the beginning of the list. The Controller will likely make use of this method in some way in order to display
     * user accounts to view.
     */
    public ArrayList<Account> userSearch(String searchQuery){

    }

    /* The point of this method is to pin a contentList to the current user's account for easy access.
     * The Controller will likely be the class that class this method and passes in the proper ContentList.
     * This also means that the Controller needs a way to allow the user to view pinned lists.
     */
    public void pinList(ContentList){

    }

    /* A getter to allow the controller class access to the lists that are currently pinned.
     * This information may also be stored in the database, meaning that this information may have to be loaded
     * from the database first.
     */
    public ArrayList<ContentList> getPinnedLists(){
        return pinnedLists;
    }



}
