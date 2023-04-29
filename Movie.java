import java.util.ArrayList;

public class Movie extends Content{

    public Movie(int contentID, String contentName, String contentDescription, String[] contentGenreNames, 
                  int[] contentGenreIds, float contentRating, ArrayList<Review> reviewList, String whereToWatch){
                        
        super(contentID, contentName, contentDescription, contentGenreNames, contentGenreIds, contentRating, reviewList, whereToWatch);
    }
}
