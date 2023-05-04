import java.util.ArrayList;

public class TVShow extends Content{
     // Noting this out for now, I forgot to grab season and episode numbers from TMDB. If I get to it, I should un-note this.
    // private int seasons;
    // private int episodes;

    public TVShow(int contentID, String contentName, String contentDescription, String[] contentGenreNames, 
                  int[] contentGenreIds, Float contentRating, ArrayList<Review> reviewList, String[] buyProviders, String[] rentalProviders, String[] flatrateProviders, int seasons, int episodes){

        super(contentID, contentName, contentDescription, contentGenreNames, contentGenreIds, contentRating, reviewList, buyProviders, rentalProviders, flatrateProviders);

        // Noting this out for now, I forgot to grab season and episode numbers from TMDB. If I get to it, I should un-note this.
        // this.seasons = seasons;
        // this.episodes = episodes;
    }

    // Noting this out for now, I forgot to grab season and episode numbers from TMDB. If I get to it, I should un-note this.
    // public int getSeasons(){
    //     return seasons;
    // }
    
    // public int getEpisodes(){
    //     return episodes;
    // }

    // Noting this out for now, I forgot to grab season and episode numbers from TMDB. If I get to it, I should un-note this.

    // @Override
    // public String toString(){
    //     StringBuilder stringBuilder = new StringBuilder();
    //     stringBuilder.append(getContentName()).append(" - ").append(getContentDescription()).append("\n");
    //     if(getContentRating() != null) stringBuilder.append("Content Rating: ").append(getContentRating()).append("\n");
    //     stringBuilder.append("Seasons: ").append(seasons).append("\n");
    //     stringBuilder.append("Episodes: ").append(episodes).append("\n");
    //     String[] contentGenreNames = getContentGenreNamesCopy();
    //     if(contentGenreNames != null){
    //         stringBuilder.append("Content Genres:");
    //         if(contentGenreNames.length > 0) stringBuilder.append(" ").append(contentGenreNames[0]);
    //         for(int i = 1; i < contentGenreNames.length; i++){
    //             stringBuilder.append(", ").append(contentGenreNames[i]);
    //         }
    //         stringBuilder.append("\n");
    //     }
    //     String[] buyProviders = getBuyProvidersCopy();
    //     if(buyProviders != null){
    //         stringBuilder.append("You can buy this content from:");
    //         if(buyProviders.length > 0) stringBuilder.append(" ").append(buyProviders);
    //         for(int i = 1; i < buyProviders.length; i++){
    //             stringBuilder.append(", ").append(buyProviders);
    //         }
    //         stringBuilder.append("\n");
    //     }
    //     String[] rentalProviders = getRentalProvidersCopy();
    //     if(rentalProviders != null){
    //         stringBuilder.append("You can rent this content from:");
    //         if(rentalProviders.length > 0) stringBuilder.append(" ").append(rentalProviders[0]);
    //         for(int i = 1; i < rentalProviders.length; i++){
    //             stringBuilder.append(", ").append(rentalProviders[i]);
    //         }
    //         stringBuilder.append("\n");
    //     }
    //     String[] flatrateProviders = getFlatrateProvidersCopy();
    //     if(flatrateProviders != null){
    //         stringBuilder.append("These companies offer this content under a flatrate subscription: ");
    //         if(flatrateProviders.length > 0) stringBuilder.append(" ").append(flatrateProviders[0]);
    //         for(int i = 1; i < flatrateProviders.length; i++){
    //             stringBuilder.append(", ").append(flatrateProviders[i]);
    //         }
    //         stringBuilder.append("\n");
    //     }

    //     return stringBuilder.toString();
    // }
}
