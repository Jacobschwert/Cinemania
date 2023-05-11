import org.junit.Assert;
import org.junit.Test;

public class TMDBCommunicatorTest {
    // These tests require an API key be placed in TMDBCommunicator
    @Test
    public void getMovieWatchOptionsArrayNullTest(){
        for(ContentManager.RecommendationType rType : ContentManager.RecommendationType.values()){
            // Based on these tests, it appears that resultList can be null
            TMDBMovieResultList resultList = TMDBCommunicator.getRecommendationListMovieResults(rType);
            if(resultList == null){
                System.out.println("Skipping: " + rType.toString());
                continue;
            }
            TMDBWatchOption[] watchOptions = TMDBCommunicator.getMovieWatchOptionsArray(resultList);
            // Checking for Null Values
            for(int i = 0; i < watchOptions.length; i++){
                //getResults should not return a null value
                Assert.assertTrue(watchOptions[i].getResults() != null);

                // US watch options can be null
                TMDBCountryWatchOptionList usOptionList = watchOptions[i].getResults().getUSOptionList();
                if(usOptionList == null){
                    System.out.println("US option list is null!");
                } 
                else if(usOptionList != null){
                    // Buy information array can be null
                    TMDBWatchOptionProviderInfo[] buyInfos = usOptionList.getBuyOptions();
                    if(buyInfos == null) System.out.println("Null buy info Array!");

                    // Rental information array can be null
                    TMDBWatchOptionProviderInfo[] rentalInfos = usOptionList.getRentOptions();
                    if(rentalInfos == null) System.out.println("Null rental info array!");
                    
                    // Flatrate information array can be null
                    TMDBWatchOptionProviderInfo[] flatrateInfos = usOptionList.getFlatrateOptions();
                    if(flatrateInfos == null) System.out.println("Null flatrate info array!");
                }
            }
        }
    }
    @Test
    public void getTVWatchOptionsArrayNullTest(){
        for(ContentManager.RecommendationType rType : ContentManager.RecommendationType.values()){
            // Based on these tests, it appears that resultList can be null
            TMDBTVResultList resultList = TMDBCommunicator.getRecommendationListTVResults(rType);
            if(resultList == null){
                System.out.println("Skipping: " + rType.toString());
                continue;
            }
            TMDBWatchOption[] watchOptions = TMDBCommunicator.getTVWatchOptionsArray(resultList);
            // Checking for Null Values
            for(int i = 0; i < watchOptions.length; i++){
                //getResults should not return a null value
                Assert.assertTrue(watchOptions[i].getResults() != null);

                // US watch options can be null
                TMDBCountryWatchOptionList usOptionList = watchOptions[i].getResults().getUSOptionList();
                if(usOptionList == null){
                    System.out.println("US option list is null!");
                } 
                else if(usOptionList != null){
                    // Buy information array can be null
                    TMDBWatchOptionProviderInfo[] buyInfos = usOptionList.getBuyOptions();
                    if(buyInfos == null) System.out.println("Null buy info Array!");

                    // Rental information array can be null
                    TMDBWatchOptionProviderInfo[] rentalInfos = usOptionList.getRentOptions();
                    if(rentalInfos == null) System.out.println("Null rental info array!");
                    
                    // Flatrate information array can be null
                    TMDBWatchOptionProviderInfo[] flatrateInfos = usOptionList.getFlatrateOptions();
                    if(flatrateInfos == null) System.out.println("Null flatrate info array!");
                }
            }
        }
    }
}
