public class ContentFactory {

    public enum contentType{
        TVSHOW,
        MOVIE,
    }

    /*Constructor is made private to ensure that only one ContentFactory class exists at a time*/
    private ContentFactory(){

    }

    /* Method that is meant to return a tv show or movie after populating its attributes accordingly. */
    public static Content getContent(contentType cType){
        switch(cType){
            case TVSHOW:
                break;
            case MOVIE:
                break;
        }
    }
}
