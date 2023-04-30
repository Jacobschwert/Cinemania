import java.util.ArrayList;

public class TVShow extends Content{
    private int seasons;
    private int episodes;

    public TVShow(int contentID, String contentName, String contentDescription, String[] contentGenreNames, 
                  int[] contentGenreIds, Float contentRating, ArrayList<Review> reviewList, String[] buyProviders, String[] rentalProviders, String[] flatrateProviders, int seasons, int episodes){

        super(contentID, contentName, contentDescription, contentGenreNames, contentGenreIds, contentRating, reviewList, buyProviders, rentalProviders, flatrateProviders);
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public int getSeasons(){
        return seasons;
    }
    
    public int getEpisodes(){
        return episodes;
    }
}
