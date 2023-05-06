import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ContentFactory {

    private static SqliteConnector db = new SqliteConnector();
    private static Connection conn = db.connect();
    private static SqliteQueries query = new SqliteQueries(conn);

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
        ArrayList<Review> reviewList = null;

        String queryString = "SELECT reviews FROM movie WHERE contentID = " + contentId + ";" ;
        ResultSet rs;
        String unparsedReviewIds;
        // Find reviews attached to this piece of content in the database(if they're there) and put them in an ArrayList.
        try{
            rs = query.executeQuery(queryString);
            if (rs.next()) {
                unparsedReviewIds = rs.getString("reviews");
                if(unparsedReviewIds != null){
                    String[] reviewIds = unparsedReviewIds.split(",",-1);
                    reviewList = new ArrayList<Review>(reviewIds.length);
                    for(int i = 0; i < reviewIds.length; i++){
                        // Feedback Factory method should be static I think.
                        reviewList.add(FeedbackFactory.getReview(Integer.parseInt(reviewIds[i])));
                    }
                }
            }
        } 
        catch(SQLException e){
            e.printStackTrace();
        }

        // Loop through the reviews and set the content rating as the average rating.
        Float contentRating = null;
        if(reviewList != null){
            float sum = 0;
            for(int i = 0; i < reviewList.size(); i++){
                sum += reviewList.get(i).getRating();
            }
            contentRating = sum/reviewList.size();
        }

        return new Movie(contentId, contentName, contentDescription, genreNames, contentGenreIds, contentRating, reviewList, buyProviders, rentalProviders, flatrateProviders);

    }

    // A method for creating a TVShow object from TMDB information.
    public static TVShow getTVShowFromTMDBTVInfo(TMDBTVResult result, TMDBWatchOption tvWatchOption){
        int contentId = result.getId();
        String contentName = result.getName();
        String contentDescription = result.getOverview();
        int[] contentGenreIds = result.getGenreIds();
        String[] genreNames = TMDBCommunicator.getTVGenresByTMDBID(contentGenreIds);

        TMDBCountryWatchOptionList usList = tvWatchOption.getResults().getUSOptionList();
        String[] buyProviders = getProviderNamesFromProviderInfoArray(usList.getBuyOptions());
        String[] rentalProviders = getProviderNamesFromProviderInfoArray(usList.getRentOptions());
        String[] flatrateProviders = getProviderNamesFromProviderInfoArray(usList.getFlatrateOptions());
        ArrayList<Review> reviewList = null;

        String queryString = "SELECT reviews FROM tvShow WHERE contentID = " + contentId + ";" ;
        ResultSet rs;
        String unparsedReviewIds;
        // Find reviews attached to this piece of content in the database(if they're there) and put them in an ArrayList.
        try{
            rs = query.executeQuery(queryString);
            if (rs.next()) {
                unparsedReviewIds = rs.getString("reviews");
                if(unparsedReviewIds != null){
                    String[] reviewIds = unparsedReviewIds.split(",",-1);
                    reviewList = new ArrayList<Review>(reviewIds.length);
                    for(int i = 0; i < reviewIds.length; i++){
                        // Feedback Factory method should be static I think.
                        reviewList.add(FeedbackFactory.getReview(Integer.parseInt(reviewIds[i])));
                    }
                }
            }
        } 
        catch(SQLException e){
            e.printStackTrace();
        }

        // Loop through the reviews and set the content rating as the average rating.
        Float contentRating = null;
        if(reviewList != null){
            float sum = 0;
            for(int i = 0; i < reviewList.size(); i++){
                sum += reviewList.get(i).getRating();
            }
            contentRating = sum/reviewList.size();
        }

        return new TVShow(contentId, contentName, contentDescription, genreNames, contentGenreIds, contentRating, reviewList, buyProviders, rentalProviders, flatrateProviders);
    }

    private static String[] getProviderNamesFromProviderInfoArray(TMDBWatchOptionProviderInfo[] providerInfos){
        String[] returnStrings = new String[providerInfos.length];
        for(int i = 0; i < providerInfos.length; i++){
            returnStrings[i] = providerInfos[i].getProviderName();
        }
        return returnStrings;
    }

}
