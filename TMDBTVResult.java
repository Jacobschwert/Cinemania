public class TMDBTVResult {
    private String overview;
    private int[] genre_ids;
    private int id;
    private String name;

    public String getOverview(){
        return overview;
    }

    public int[] getGenreIds(){
        return genre_ids;
    }
    
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    
}