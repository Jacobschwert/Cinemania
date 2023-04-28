import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class TMDBCommunicator {

    // Need a better way of storing the API key that our program will use.
    // Needs to be hidden from GitHub and the public in general.
    // For now, I'll just avoid pushing my key, keeping this as an empty string before commits (otherwise, the key will be public in the git history)
    // Don't push a commit with an actual API_KEY stored here, just use it for testing.
    private static final String API_KEY = "";

    public static TMDBMovieResultList getReccomendationListMovieResults(ContentManager.reccomendationType rType){
        switch(rType){
            case POPULAR:
                break;
            case ACTION:
                return getPopularActionMovieResultList();
            case ADVENTURE:
                break;
            case COMEDY:
                break;
            case HORROR:
                break;
        }
        return null;
    }
    
    // Get an array of lists containing information about where you can buy a movie to watch. This array corresponds to a list of movies that is passed in.
    public static TMDBWatchOptionResultList[] getMovieWatchOptionsArray(TMDBMovieResultList resultList){
        TMDBMovieResult[] results = resultList.getResults();
        TMDBWatchOptionResultList[] returnList = new TMDBWatchOptionResultList[results.length];
        Gson gson = new Gson();
        for(int i = 0; i < results.length; i++){
            int movieID = results[i].getId();
            HttpResponse<String> getResponse = getRequestWithURL(String.format("https://api.themoviedb.org/3/movie/%d/watch/providers?api_key=%s", movieID, API_KEY));
            returnList[i] = gson.fromJson(getResponse.body(), TMDBWatchOptionResultList.class);
        }
        return returnList;
    }

    // TODO: Think about how to handle different HTTP status codes.
    // This code will currently return a MovieResultList of popular action movies sorted in descending order.
    private static TMDBMovieResultList getPopularActionMovieResultList(){
        HttpResponse<String> getResponse = getRequestWithURL(String.format("https://api.themoviedb.org/3/discover/movie?api_key=%s&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=28&with_watch_monetization_types=flatrate", API_KEY));
        Gson gson = new Gson();
        TMDBMovieResultList movieResultList = gson.fromJson(getResponse.body(), TMDBMovieResultList.class);
        return movieResultList;
    }

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

    // Just using this for testing purposes.
    // public static void main(String args[]){
    //     TMDBMovieResultList movieResults = getPopularActionMovieResultList();
    //     for(int i = 0; i < movieResults.getResults().length; i++){
    //         System.out.println(movieResults.getResults()[i].getTitle());
    //     }
    //     // Note that not all movies have buy or flatrate options filled out, they can be null.
    //     // Will have to keep this in mind
    //     TMDBWatchOptionResultList[] watchOptions = getMovieWatchOptionsArray(movieResults);
    //     for(int i = 0; i < movieResults.getResults().length; i++){
    //         System.out.println(watchOptions[i].getResults().getUSOptionList().getBuyOptions()[0].getProviderName());
    //     }
    // }
}
