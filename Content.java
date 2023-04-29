import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class Content {
    private int contentID;
    private String contentName;
    private String contentDescription;
    private String[] contentGenreNames;
    private int[] contentGenreIds;
    private float contentRating;
    private ArrayList<Review> reviewList;
    private String whereToWatch;

    public Content(int contentID, String contentName, String contentDescription, String[] contentGenreNames, int[] contentGenreIds, float contentRating, ArrayList<Review> reviewList, String whereToWatch){
        this.contentID = contentID;
        this.contentName = contentName;
        this.contentDescription = contentDescription;
        this.contentGenreNames = contentGenreNames;
        this.contentGenreIds = contentGenreIds;
        this.contentRating = contentRating;
        this.reviewList = reviewList;
        this.whereToWatch = whereToWatch;
    }

    public int getContentID(){
        return contentID;
    }

    public String getContentName(){
        return contentName;
    }

    public String getContentDescription(){
        return contentDescription;
    }

    public String[] getContentGenreNamesCopy(){
        String[] copy = Arrays.copyOf(contentGenreNames, contentGenreNames.length);
        return copy;
    }

    public int[] getContentGenreIdsCopy(){
        int[] copy = Arrays.copyOf(contentGenreIds, contentGenreIds.length);
        return copy;
    }

    public float getContentRating(){
        return contentRating;
    }

    public ArrayList<Review> getReviewListCopy(){
        ArrayList<Review> copy = new ArrayList<Review>(reviewList.size());
        Collections.copy(reviewList, copy);
        return copy;
    }

    public String getWhereToWatch(){
        return whereToWatch;
    }
}
