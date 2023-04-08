package com.it326.cinemania;

public class ContentFactory {
    private static ContentFactory instance = null;

    public enum contentType{
        TVSHOW,
        MOVIE,
    }

    /*Constructor is made private to ensure that only one ContentFactory class exists at a time*/
    private ContentFactory(){

    }

    // Helper method to ensure that an instance exists.
    private static void checkInstance(){
        if(instance == null){
            instance = new ContentFactory();
        }
    }

    /* Method that is meant to return a tv show or movie and populate its attributes accordingly. */
    public Content getContent(contentType cType){
        checkInstance();
        switch(cType){
            case TVSHOW:
                break;
            case MOVIE:
                break;
        }
    }

    /*Method that allows other classes to get the ContentFactory instance.
     *This method may not be necessary.
    */
    public static ContentFactory getInstance(){
        checkInstance();
        return instance;
    }
}
