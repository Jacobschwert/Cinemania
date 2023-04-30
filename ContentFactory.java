import java.util.ArrayList;

public class ContentFactory {

    public enum contentType{
        TVSHOW,
        MOVIE,
    }

    /* Method that is meant to return a ContentList by making use of the TMDBCommunicator, based off of a Recommendation type. */
    public static ContentList getRecommendedContentList(ContentManager.RecommendationType rType){
        switch(rType){
            case MOVIES_POPULAR:
                break;
            case MOVIES_ACTION:
                TMDBMovieResultList resultList = TMDBCommunicator.getRecommendationListMovieResults(rType);
                TMDBMovieResult[] movieResults = resultList.getResults();
                TMDBWatchOption[] watchOptions = TMDBCommunicator.getMovieWatchOptionsArray(resultList);
                ArrayList<Content> contentArrayList = new ArrayList<Content>(movieResults.length);
                for(int i = 0; i < movieResults.length; i++){
                    contentArrayList.add(getMovieFromTMDBMovieInfo(movieResults[i], watchOptions[i]));
                }

                String contentListName = "Action Movies";
                String contentListDescription = "Popular Action Movies according to The Movie Database";

                // For contentListID, I'll need a way to generate a unique ID. 
                
            case MOVIES_ADVENTURE:
                break;
            case MOVIES_COMEDY:
                break;
            case MOVIES_HORROR:
                break;
        }
    }

    // A helper method for creating a Movie object from TMDB information.
    private static Movie getMovieFromTMDBMovieInfo(TMDBMovieResult result, TMDBWatchOption movieWatchOption){
        int contentId = result.getId(); 
        String contentName = result.getTitle();
        String contentDescription = result.getOverview();
        int[] contentGenreIds = result.getGenre_ids();
        String[] genreNames = TMDBCommunicator.getMovieGenresByTMDBID(contentGenreIds);

        TMDBCountryWatchOptionList usList = movieWatchOption.getResult().getUSOptionList();
        String[] buyProviders = getProviderNamesFromProviderInfoArray(usList.getBuyOptions());
        String[] rentalProviders = getProviderNamesFromProviderInfoArray(usList.getRentOptions());
        String[] flatrateProviders = getProviderNamesFromProviderInfoArray(usList.getFlatrateOptions());

        // To get the contentRating of a content object, I'll need to ping the Cinemania database and
        // and take the average rating of all reviews for that piece of content. If no ratings exist, then I will have it be null (or thats an idea).

        // For a review list, I'll need to ping the Feedback factory, which will then look through the cinemania database use information stored there
        // to bring back a list of review objects representing reviews written from that piece of content. If there are no reviews,
        // then I'll have the review list be either null or empty.

    }

    private static String[] getProviderNamesFromProviderInfoArray(TMDBWatchOptionProviderInfo[] providerInfos){
        String[] returnStrings = new String[providerInfos.length];
        for(int i = 0; i < providerInfos.length; i++){
            returnStrings[i] = providerInfos[i].getProviderName();
        }
        return returnStrings;
    }

}
