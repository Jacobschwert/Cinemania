import java.util.ArrayList;

public class Content {
    private int contentID;
    private String contentName;
    private String contentDescription;
    private String[] contentGenres;
    private float contentRating;
    private ArrayList<Review> reviewList;
    private String whereToWatch;

    public Content(int contentID, String contentName, String contentDescription, String[] contentGenres, float contentRating, ArrayList<Review> reviewList, String whereToWatch){
        this.contentID = contentID;
        this.contentName = contentName;
        this.contentDescription = contentDescription;
        this.contentGenres = contentGenres;
        this.contentRating = contentRating;
        this.reviewList = reviewList;
        this.whereToWatch = whereToWatch;
    }
}
