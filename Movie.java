public class Movie extends Content{

    public Movie(int contentID, String contentName, String contentDescription, String[] contentGenres, 
                  float contentRating, ArrayList<Review> reviewList, String whereToWatch, boolean inTheaters){
                        
        super(contentID, contentName, contentDescription, contentGenres, contentRating, reviewList, whereToWatch);
        this.inTheaters = inTheaters;
    }
}
