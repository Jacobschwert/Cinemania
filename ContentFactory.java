import java.util.ArrayList;

public class ContentFactory {

    public enum contentType{
        TVSHOW,
        MOVIE,
    }

    // A method for creating a Movie object from TMDB information.
    public static Movie getMovieFromTMDBMovieInfo(TMDBMovieResult result, TMDBWatchOption movieWatchOption){
        int contentId = result.getId(); 
        String contentName = result.getTitle();
        String contentDescription = result.getOverview();
        int[] contentGenreIds = result.getGenre_ids();
        String[] genreNames = TMDBCommunicator.getMovieGenresByTMDBID(contentGenreIds);

        TMDBCountryWatchOptionList usList = movieWatchOption.getResults().getUSOptionList();
        String[] buyProviders = getProviderNamesFromProviderInfoArray(usList.getBuyOptions());
        String[] rentalProviders = getProviderNamesFromProviderInfoArray(usList.getRentOptions());
        String[] flatrateProviders = getProviderNamesFromProviderInfoArray(usList.getFlatrateOptions());

        // For a review list, I'll need to ping the Feedback factory, which will then look through the cinemania database use information stored there
        // to bring back a list of review objects representing reviews written from that piece of content. If there are no reviews,
        // then I'll have the review list be either null or empty.

        // To get the content rating, I can just go over the list of the reviews and take the average rating.  

    }

    // A method for creating a TVShow object from TMDB information.
    public static TVShow getTVShowFromTMDBTVInfo(TMDBTVResult result, TMDBWatchOption tvWatchOption){
        int contentID = result.getId();
        String contentName = result.getName();
        String contentDescription = result.getOverview();
        int[] contentGenreIds = result.getGenreIds();
        String[] genreNames = TMDBCommunicator.getTVGenresByTMDBID(contentGenreIds);

        TMDBCountryWatchOptionList usList = tvWatchOption.getResults().getUSOptionList();
        String[] buyProviders = getProviderNamesFromProviderInfoArray(usList.getBuyOptions());
        String[] rentalProviders = getProviderNamesFromProviderInfoArray(usList.getRentOptions());
        String[] flatrateProviders = getProviderNamesFromProviderInfoArray(usList.getFlatrateOptions());

        // For a review list, I'll need to ping the Feedback factory, which will then look through the cinemania database use information stored there
        // to bring back a list of review objects representing reviews written from that piece of content. If there are no reviews,
        // then I'll have the review list be either null or empty.

        // To get the content rating, I can just go over the list of the reviews and take the average rating.  
    }

    private static String[] getProviderNamesFromProviderInfoArray(TMDBWatchOptionProviderInfo[] providerInfos){
        String[] returnStrings = new String[providerInfos.length];
        for(int i = 0; i < providerInfos.length; i++){
            returnStrings[i] = providerInfos[i].getProviderName();
        }
        return returnStrings;
    }

}
