public class ContentFactory {

    public enum contentType{
        TVSHOW,
        MOVIE,
    }

    /*Constructor is made private to ensure that only one ContentFactory class exists at a time*/
    private ContentFactory(){

    }

    /* Method that is meant to return a tv show or movie after populating its attributes accordingly. 
     * This will likely be refactored to manipulate JSON or JSON parsed into java classes.
    */
    public static Content getContent(contentType cType){
        switch(cType){
            case TVSHOW:
                break;
            case MOVIE:
                break;
        }
    }

    /* Method that is meant to return a ContentList by making use of the TMDBCommunicator, based off of a reccomendation type. */
    public ContentList getContentList(ContentManager.reccomendationType rType){
        
    }
}
