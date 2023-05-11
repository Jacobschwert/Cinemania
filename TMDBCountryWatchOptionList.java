public class TMDBCountryWatchOptionList {
    private TMDBWatchOptionProviderInfo[] buy;
    private TMDBWatchOptionProviderInfo[] rent;
    private TMDBWatchOptionProviderInfo[] flatrate;
 
    public TMDBWatchOptionProviderInfo[] getBuyOptions(){
       return buy;
    }
 
    public TMDBWatchOptionProviderInfo[] getRentOptions(){
       return rent;
    }
 
    public TMDBWatchOptionProviderInfo[] getFlatrateOptions(){
       return flatrate;
    }
    
 }