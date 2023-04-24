// The purpose of this class is to parse JSON directly from TMDB and put it in an easy-to-use format with the help of the GSON library.
public class MovieJSON {
    // The TMDB id of a movie, this can be used to gather more detailed information about a movie off TMBD.
    private int id;
    private String overview;
    // Ids corresponding to different genres on TMDB.
    private int[] genre_ids;
    // The original title of the movie according to TMDB.
    private String original_title;

}
