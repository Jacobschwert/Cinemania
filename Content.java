/**
 * Custom, simplified version of Content class that is suitable for testing purposes.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
 
public class Content
{
    private int contentID;
    private String contentName;
    private String contentDescription;
    private float contentRating;
    // private ArrayList<Review> reviewList;
 
    public Content(int contentID, String contentName, String contentDescription, float contentRating)
    {
        this.contentID = contentID;
        this.contentName = contentName;
        this.contentDescription = contentDescription;
        this.contentRating = contentRating;
        // this.reviewList = reviewList;
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

    public float getContentRating(){
        return contentRating;
    }

    // public ArrayList<Review> getReviewListCopy(){
    //     ArrayList<Review> copy = new ArrayList<Review>(reviewList.size());
    //     Collections.copy(reviewList, copy);
    //     return copy;
    // }
}