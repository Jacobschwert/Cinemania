import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import com.google.gson.Gson;

public class TMDBCommunicator {

    public MovieJSON[] getReccomendationListJSON(ContentManager.reccomendationType rType){
        MovieJSON[] movieJson = null;
        switch(rType){
            case POPULAR:
                break;
            case ACTION:
                break;
            case ADVENTURE:
                break;
            case COMEDY:
                break;
            case HORROR:
                break;
        }
        return movieJson;
    }

    private static MovieJSON[] getActionJSON(){
        //This code isn't workable yet, just figuring out how to parse JSON from TMDB.
        HttpClient httpclient = HttpClient.newHttpClient();
        try{
            HttpRequest getRequest = HttpRequest.newBuilder()
            .uri(new URI(""))
            .build();

            HttpResponse<String> getResponse = httpclient.send(getRequest, BodyHandlers.ofString());
            Gson gson = new Gson();
            MovieJSON[] movieJSON = gson.fromJson(getResponse.body(), MovieJSON[].class);
            return movieJSON;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Just using this for testing purposes.
    public static void main(String args[]){
        MovieJSON[] movieJSON = getActionJSON();

    }
}
