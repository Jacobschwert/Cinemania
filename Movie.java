import java.util.ArrayList;

public class Movie extends Content{

    public Movie(int contentID, String contentName, String contentDescription, String[] contentGenreNames, 
                  int[] contentGenreIds, Float contentRating, ArrayList<Review> reviewList, String[] buyProviders, String[] rentalProviders, String[] flatrateProviders){
                        
        super(contentID, contentName, contentDescription, contentGenreNames, contentGenreIds, contentRating, reviewList, buyProviders, rentalProviders, flatrateProviders);
    }
    
}
