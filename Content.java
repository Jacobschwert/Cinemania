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

    public Float getContentRating(){
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

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getContentName()).append(" - ").append(getContentDescription()).append("\n");
        if(getContentRating() != null) stringBuilder.append("Content Rating: ").append(getContentRating()).append("\n");
        if(contentGenreNames != null){
            stringBuilder.append("Content Genres:");
            if(contentGenreNames.length > 0) stringBuilder.append(" ").append(contentGenreNames[0]);
            for(int i = 1; i < contentGenreNames.length; i++){
                stringBuilder.append(", ").append(contentGenreNames[i]);
            }
            stringBuilder.append("\n");
        }
        if(buyProviders != null){
            stringBuilder.append("You can buy this content from:");
            if(buyProviders.length > 0) stringBuilder.append(" ").append(buyProviders);
            for(int i = 1; i < buyProviders.length; i++){
                stringBuilder.append(", ").append(buyProviders);
            }
            stringBuilder.append("\n");
        }
        if(rentalProviders != null){
            stringBuilder.append("You can rent this content from:");
            if(rentalProviders.length > 0) stringBuilder.append(" ").append(rentalProviders[0]);
            for(int i = 1; i < rentalProviders.length; i++){
                stringBuilder.append(", ").append(rentalProviders[i]);
            }
            stringBuilder.append("\n");
        }
        if(flatrateProviders != null){
            stringBuilder.append("These companies offer this content under a flatrate subscription: ");
            if(flatrateProviders.length > 0) stringBuilder.append(" ").append(flatrateProviders[0]);
            for(int i = 1; i < flatrateProviders.length; i++){
                stringBuilder.append(", ").append(flatrateProviders[i]);
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
        
        
    }
    
}