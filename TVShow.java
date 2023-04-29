import java.util.ArrayList;

public class TVShow extends Content{
    private int seasons;
    private int episodes;

    public TVShow(int contentID, String contentName, String contentDescription, String[] contentGenreNames, 
                  int[] contentGenreIds, float contentRating, ArrayList<Review> reviewList, String whereToWatch, int seasons, int episodes){

        super(contentID, contentName, contentDescription, contentGenreNames, contentGenreIds, contentRating, reviewList, whereToWatch);
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
