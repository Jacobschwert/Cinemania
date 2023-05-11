import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;

import com.google.gson.Gson;

public class TMDBCommunicator {

    private static HashMap<Integer,String> movieGenres;
    private static HashMap<Integer,String> tvGenres;

    // Need a better way of storing the API key that our program will use.
    // Needs to be hidden from GitHub and the public in general.
    // For now, I'll just avoid pushing my key, keeping this as an empty string before commits (otherwise, the key will be public in the git history)
    // Don't push a commit with an actual API_KEY stored here, just use it for testing.
    private static final String API_KEY = "c75eadbc8c681679c1367a2c3f62e19a";

    // A method for getting TMDB movie info that matches with a recommendation type.
    public static TMDBMovieResultList getRecommendationListMovieResults(ContentManager.RecommendationType rType){
        switch(rType){
            case MOVIES_POPULAR:
                return getPopularMovieResultList();
            case MOVIES_ACTION:
                return getPopularMovieResultList(28);
            case MOVIES_ADVENTURE:
                return getPopularMovieResultList(12);
            case MOVIES_COMEDY:
                return getPopularMovieResultList(35);
            case MOVIES_HORROR:
                return getPopularMovieResultList(27);
            default:
                break;
        }
        return null;
    }

    // A method for getting TMDB tv info that matches with a recommendation type.
    public static TMDBTVResultList getRecommendationListTVResults(ContentManager.RecommendationType rType){
        switch(rType){
            case TV_POPULAR:
                return getPopularTVResultList();
            case TV_ACTION_ADVENTURE:
                return getPopularTVResultList(10759);
            case TV_MYSTERY:
                return getPopularTVResultList(9648);
            case TV_COMEDY:
                return getPopularTVResultList(35);
            case TV_SCIFI_FANTASY:
                return getPopularTVResultList(10765);
            default:
                break;
        }
        return null;
    }
    
    // Get an array of lists containing information about where you can buy a movie to watch. This array corresponds to a list of movies that is passed in.
    // Example JSON object: https://api.themoviedb.org/3/movie/550/watch/providers?api_key= (Requires an API KEY)
    // Note that arrays containing watch options, such as the buy array or flatrate array, can be null
    public static TMDBWatchOption[] getMovieWatchOptionsArray(TMDBMovieResultList resultList){
        TMDBMovieResult[] results = resultList.getResults();
        TMDBWatchOption[] returnList = new TMDBWatchOption[results.length];
        Gson gson = new Gson();
        for(int i = 0; i < results.length; i++){
            int movieID = results[i].getId();
            HttpResponse<String> getResponse = getRequestWithURL(String.format("https://api.themoviedb.org/3/movie/%d/watch/providers?api_key=%s", movieID, API_KEY));
            returnList[i] = gson.fromJson(getResponse.body(), TMDBWatchOption.class);
        }
        return returnList;
    }

    // Get an array of lists containing information about where you can buy a movie to watch. This array corresponds to a list of movies that is passed in.
    // Note that arrays containing watch options, such as the buy array or flatrate array, can be null.
    public static TMDBWatchOption[] getTVWatchOptionsArray(TMDBTVResultList resultList){
        TMDBTVResult[] results = resultList.getResults();
        TMDBWatchOption[] returnList = new TMDBWatchOption[results.length];
        Gson gson = new Gson();
        for(int i = 0; i < results.length; i++){
            int tvID = results[i].getId();
            HttpResponse<String> getResponse = getRequestWithURL(String.format("https://api.themoviedb.org/3/tv/%d/watch/providers?api_key=%s", tvID, API_KEY));
            returnList[i] = gson.fromJson(getResponse.body(), TMDBWatchOption.class);
        }
        return returnList;
    }

    // Method for getting a movie genre name by its TMDB id
    public static String getMovieGenreByTMDBID(int id){
        if(movieGenres == null) defineMovieGenresHashMap();
        return movieGenres.get(id);
    }

    // Method for getting a TV genre name by its TMDB id
    public static String getTVGenreByTMDBID(int id){
        if(tvGenres == null) defineTVGenresHashMap();
        return tvGenres.get(id);
    }

    // Method for getting a movie genre names by their TMDB ids
    public static String[] getMovieGenresByTMDBID(int[] id){
        String[] genreNames = new String[id.length];
        for(int i = 0; i < id.length; i++){
            genreNames[i] = getMovieGenreByTMDBID(i);
        }
        return genreNames;
    }

    // Method for getting a TV genre names by their TMDB ids
    public static String[] getTVGenresByTMDBID(int[] id){
        String[] genreNames = new String[id.length];
        for(int i = 0; i < id.length; i++){
            genreNames[i] = getTVGenreByTMDBID(i);
        }
        return genreNames;
    }

    // This method will parse JSON gathered from TMDB with the help of the GSON library. 
    // It gathers JSON information about movies based on a search query.
    public static TMDBMovieResultList getContentSearchMovieResultList(String searchQuery){ //https://api.themoviedb.org/3/search/movie?api_key=c75eadbc8c681679c1367a2c3f62e19a&language=en-US&query=Wow&include_adult=false
        HttpResponse<String> getResponse = getRequestWithURL(String.format("https://api.themoviedb.org/3/search/movie?api_key=%s&language=en-US&query=%s&include_adult=false", API_KEY, searchQuery));
        Gson gson = new Gson();
        TMDBMovieResultList movieResultList = gson.fromJson(getResponse.body(), TMDBMovieResultList.class);
        return movieResultList;
    }

    // This method will parse JSON gathered from TMDB with the help of the GSON library. 
    // It gathers JSON information about tv shows based on a search query.
    public static TMDBTVResultList getContentSearchTVResultList(String searchQuery){
        HttpResponse<String> getResponse = getRequestWithURL(String.format("https://api.themoviedb.org/3/search/tv?api_key=%s&language=en-US&query=%s&include_adult=false", API_KEY, searchQuery));
        Gson gson = new Gson();
        TMDBTVResultList tvResultList = gson.fromJson(getResponse.body(), TMDBTVResultList.class);
        return tvResultList;
    }


    private static TMDBMovieResultList getPopularMovieResultList(){
        HttpResponse<String> getResponse = getRequestWithURL(String.format("https://api.themoviedb.org/3/discover/movie?api_key=%s&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1", API_KEY));
        Gson gson = new Gson();
        TMDBMovieResultList movieResultList = gson.fromJson(getResponse.body(), TMDBMovieResultList.class);
        return movieResultList;
    }

    // This code will currently return a MovieResultList of popular movies in a specific genre, sorted in descending order.
    // Example JSON Object: https://api.themoviedb.org/3/discover/movie?api_key=&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=28
    // (Requires an API Key)
    private static TMDBMovieResultList getPopularMovieResultList(int genreId){
        HttpResponse<String> getResponse = getRequestWithURL(String.format("https://api.themoviedb.org/3/discover/movie?api_key=%s&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=%d", API_KEY, genreId));
        Gson gson = new Gson();
        TMDBMovieResultList movieResultList = gson.fromJson(getResponse.body(), TMDBMovieResultList.class);
        return movieResultList;
    }

    // This code will currently return a TMDBTVResultList of popular TV Shows in general, sorted in descending order.
    // Example JSON Object: https://api.themoviedb.org/3/discover/movie?api_key=&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=28
    // (Requires an API Key)
    private static TMDBTVResultList getPopularTVResultList(){
        HttpResponse<String> getResponse = getRequestWithURL("https://api.themoviedb.org/3/discover/tv?api_key="+ API_KEY + "&language=en-US&sort_by=popularity.desc&page=1&timezone=America%2FNew_York&include_null_first_air_dates=false&with_original_language=en");
        Gson gson = new Gson();
        TMDBTVResultList tvResultList = gson.fromJson(getResponse.body(), TMDBTVResultList.class);
        return tvResultList;
    }

    // This code will currently return a TMDBTVResultList of popular TV Shows in a specific genre, sorted in descending order.
    // Example JSON Object: https://api.themoviedb.org/3/discover/movie?api_key=&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=28
    // (Requires an API Key)
    private static TMDBTVResultList getPopularTVResultList(int genreId){
        HttpResponse<String> getResponse = getRequestWithURL("https://api.themoviedb.org/3/discover/tv?api_key="+ API_KEY + "&language=en-US&sort_by=popularity.desc&page=1&timezone=America%2FNew_York&include_null_first_air_dates=false&with_original_language=en&with_genres=" + genreId);
        Gson gson = new Gson();
        TMDBTVResultList tvResultList = gson.fromJson(getResponse.body(), TMDBTVResultList.class);
        return tvResultList;
    }

    // Returns a TMDB Genre list that contains all types of movie genres and their ids.
    private static TMDBGenreList getTMDBMovieGenreList(){
        HttpResponse<String> getResponse = getRequestWithURL(String.format("https://api.themoviedb.org/3/genre/movie/list?api_key=%s&language=en-US", API_KEY));
        Gson gson = new Gson();
        TMDBGenreList genreList = gson.fromJson(getResponse.body(), TMDBGenreList.class);
        return genreList;
    }

    // Returns a TMDB Genre list that contains all types of tv genres and their ids.
    private static TMDBGenreList getTMDBTVGenreList(){
        HttpResponse<String> getResponse = getRequestWithURL(String.format("https://api.themoviedb.org/3/genre/tv/list?api_key=%s&language=en-US", API_KEY));
        Gson gson = new Gson();
        TMDBGenreList genreList = gson.fromJson(getResponse.body(), TMDBGenreList.class);
        return genreList;
    }

    // Fill the movieGenres member variable HashMap with genres in String form as values and their TMDB ids as keys.
    // Makes use of getTMDBMovieGenreList() for genre names and ids.
    private static void defineMovieGenresHashMap(){
        TMDBGenre[] genres = getTMDBMovieGenreList().getGenres();
        HashMap<Integer, String> genreHashMap = new HashMap<>(genres.length);
        for(int i = 0; i < genres.length; i++){
            genreHashMap.put(genres[i].getID(), genres[i].getName());
        }
        movieGenres = genreHashMap;
    }

    // Fill the movieGenres member variable HashMap with genres in String form as values and their TMDB ids as keys.
    // Makes use of getTMDBMovieGenreList() for genre names and ids.
    private static void defineTVGenresHashMap(){
        TMDBGenre[] genres = getTMDBTVGenreList().getGenres();
        HashMap<Integer, String> genreHashMap = new HashMap<>(genres.length);
        for(int i = 0; i < genres.length; i++){
            genreHashMap.put(genres[i].getID(), genres[i].getName());
        }
        tvGenres = genreHashMap;
    }

    // TODO: Think about how to handle different HTTP status codes.
    // This code makes a get request to a given url, returning an HttpResponse.
    // Maybe this would be more cohesive in a Pure Fabrication or other class?
    private static HttpResponse<String> getRequestWithURL(String url){
        try{
            HttpClient httpclient = HttpClient.newHttpClient();
            HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(new URI(url))
            .build();
            HttpResponse<String> getResponse = httpclient.send(getRequest, BodyHandlers.ofString());
            return getResponse;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }



    //Just using this for testing purposes.
    // public static void main(String args[]){
    //     TMDBMovieResultList movieResults = getPopularActionMovieResultList();
    //     for(int i = 0; i < movieResults.getResults().length; i++){
    //         System.out.println(movieResults.getResults()[i].getTitle());
    //     }
    //     // Note that not all movies have buy, rental, or flatrate options filled out, they can be null.
    //     // Will have to keep this in mind
    //     TMDBWatchOption[] watchOptions = getMovieWatchOptionsArray(movieResults);
    //     for(int i = 0; i < movieResults.getResults().length; i++){
    //         System.out.println(watchOptions[i].getResults().getUSOptionList().getBuyOptions()[0].getProviderName());
    //     }
    // }
}
