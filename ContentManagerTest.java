
import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

public class ContentManagerTest {

    Account test1 = new Account("test1U", "test1E", "test1D", "test1P");
    ContentManager cmTest = new ContentManager(test1.getCManage(), test1);

    @Test
    public void editWatchStatusTest1(){
        cmTest.addWatchStatus(0, 100); //The 0 here represents that the content: 100 has not been watched
        cmTest.changeWatchStatus(1, 100); //This method changes the status to 1 indicating that 
        assertEquals(cmTest.getWatchStatus(100), 1);

    }
}
