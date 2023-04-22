/**
 * ContentListTest.java
 * 
 * @author Jonathan Bogue
 * @version 0.1
 * 
 * Description: This class contains tests for the ContentList class, the tests themselves
 * are fairly self-explanatory given their names, but note that since most of the methods are private
 * and are currently used by the editContentList() method, which takes input from the command line
 * directly, the 0.2 version of ContentList is not fully tested.
 */

import static org.junit.Assert.*;

import org.junit.*;
import java.util.*;

public class ContentListTest
{
    ContentList list = null;

    // Create the ContentList object to use and modify its variables testable values.
    @Before
    public void start()
    {
        ArrayList<Content> starwars = new ArrayList<Content>();
        starwars.add(new Content("Phantom Menace"));
        starwars.add(new Content("Attack of the Clones"));
        starwars.add(new Content("Revenge of the Sith"));
        starwars.add(new Content("A New Hope"));
        starwars.add(new Content("Empire Strikes Back"));
        starwars.add(new Content("Return of the Jedi"));

        list = new ContentList(1234, 6, "Star Wars",
            "Star Wars Episodes I-VI", starwars);
    }

    // Reset the ContentList object to null.
    @After
    public void end()
    {
        list = null;
    }

    /*
     * Tests the contents of a new ContentList to ensure they are the desired default values.
     * Also indirectly tests each of the "get" methods as well as checkMarkedForDeletion().
     */
    @Test
    public void testCreateNewContentList()
    {
        ContentList freshList = new ContentList();
        assertEquals(0, freshList.getContentListID());
        assertEquals(0, freshList.getList().size());
        assertEquals("New List", freshList.getContentListName());
        assertEquals("(Edit the list to change the description.)", freshList.getContentListDescription());
        assertFalse(freshList.checkMarkedForDeletion());
    }

    /*
     * Tests how accurately a ContentList is copied onto a new one.
     */
    @Test
    public void testCopyContentList()
    {
        ContentList list2 = new ContentList(list.getContentListID(), list.getList().size(),
            list.getContentListName(), list.getContentListDescription(), list.getList());
        assertEquals(list.getContentListID(), list2.getContentListID());
        assertEquals(list.getList().size(), list2.getList().size());
        assertEquals(list.getContentListName(), list2.getContentListName());
        assertEquals(list.getContentListDescription(), list2.getContentListDescription());
        assertFalse(list2.checkMarkedForDeletion());
    }

    /*
     * Tests the displayList() method to ensure no errors occur when it is used.
     */
    @Test
    public void testDisplayList()
    {
        list.displayList();
    }
}
