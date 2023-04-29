import java.util.ArrayList;

public class TVShow extends Content{
    private int seasons;
    private int episodes;

    public TVShow(int contentID, String contentName, String contentDescription, String[] contentGenres, 
                  float contentRating, ArrayList<Review> reviewList, String whereToWatch, int seasons, int episodes){

        super(contentID, contentName, contentDescription, contentGenres, contentRating, reviewList, whereToWatch);
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
