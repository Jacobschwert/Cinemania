import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class Content {
    private int contentID;
    private String contentName;
    private String contentDescription;
    private String[] contentGenreNames;
    private int[] contentGenreIds;
    private Float contentRating;
    private ArrayList<Review> reviewList;
    private String[] buyProviders;
    private String[] rentalProviders;
    private String[] flatrateProviders;

    public Content(int contentID, String contentName, String contentDescription, String[] contentGenreNames, int[] contentGenreIds, Float contentRating, ArrayList<Review> reviewList, String[] buyProviders, String[] rentalProviders, String[] flatrateProviders){
        this.contentID = contentID;
        this.contentName = contentName;
        this.contentDescription = contentDescription;
        this.contentGenreNames = contentGenreNames;
        this.contentGenreIds = contentGenreIds;
        this.contentRating = contentRating;
        this.reviewList = reviewList;
        this.buyProviders = buyProviders;
        this.rentalProviders = rentalProviders;
        this.flatrateProviders = flatrateProviders;
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

    public String[] getBuyProvidersCopy(){
        String[] copy = Arrays.copyOf(buyProviders, buyProviders.length);
        return copy;
    }

    public String[] getFlatrateProvidersCopy(){
        String[] copy = Arrays.copyOf(flatrateProviders, flatrateProviders.length);
        return copy;
    }

    public String[] getRentalProvidersCopy(){
        String[] copy = Arrays.copyOf(flatrateProviders, rentalProviders.length);
        return copy;
    }
    
}
