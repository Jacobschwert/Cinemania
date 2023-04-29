import java.util.ArrayList;

public class Movie extends Content{

    public Movie(int contentID, String contentName, String contentDescription, String[] contentGenres, 
                  float contentRating, ArrayList<Review> reviewList, String whereToWatch){
                        
        super(contentID, contentName, contentDescription, contentGenres, contentRating, reviewList, whereToWatch);
    }
}
