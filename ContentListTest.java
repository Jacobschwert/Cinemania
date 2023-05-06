/**
 * ContentListTest.java
 * 
 * @author Jonathan Bogue
 * @version 0.2
 * 
 * Description: This class contains tests for the ContentList class, the tests themselves
 * are fairly self-explanatory given their names and the descriptions associated with them.
 */

import static org.junit.Assert.*;

import org.junit.*;
import java.util.*;

public class ContentListTest
{
    // Global variables to make testing easier.
    ContentList list = null;
    String[] genres = new String[2];
    int[] genreIDs = {1111, 2222};
    ArrayList<Review> reviews = new ArrayList<Review>();
    String[] buy = new String[2];
    String[] rent = new String[2];
    String[] flatRate = new String[2];

    // Create the ContentList object to use and modify its variables testable values.
    @Before
    public void start()
    {
        ArrayList<Content> starwars = new ArrayList<Content>();
        
        genres[0] = "STARWARS";
        genres[1] = "ACTION";
        
        buy[0] = "Internet";
        buy[1] = "Lucasfilm";
        
        rent[0] = "Internet";
        rent[1] = "a store near you";
        
        flatRate[0] = "Internet";
        flatRate[1] = "Disney+";

        starwars.add(new Movie(1, "Phantom Menace", "Star Wars Episode I", genres,
            genreIDs, (float) 1.0, reviews, buy, rent, flatRate));
        starwars.add(new Movie(2, "Attack of the Clones", "Star Wars Episode II", genres,
            genreIDs, (float) 2.0, reviews, buy, rent, flatRate));
        starwars.add(new Movie(3, "Revenge of the Sith", "Star Wars Episode III", genres,
            genreIDs, (float) 3.0, reviews, buy, rent, flatRate));
        starwars.add(new Movie(4, "A New Hope", "Star Wars Episode IV", genres,
            genreIDs, (float) 4.0, reviews, buy, rent, flatRate));
        starwars.add(new Movie(5, "Empire Strikes Back", "Star Wars Episode V", genres,
            genreIDs, (float) 5.0, reviews, buy, rent, flatRate));
        starwars.add(new Movie(6, "Return of the Jedi", "Star Wars Episode VI", genres,
            genreIDs, (float) 6.0, reviews, buy, rent, flatRate));

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
     * Also indirectly tests each of the "get" methods.
     */
    @Test
    public void testCreateNewContentList()
    {
        ContentList freshList = new ContentList();
        assertEquals(0, freshList.getContentListID());
        assertEquals(0, freshList.getList().size());
        assertEquals("New List", freshList.getContentListName());
        assertEquals("(Edit the list to change the description.)", freshList.getContentListDescription());
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
    }

    /*
     * Tests the getContentListID method.
     */
    @Test
    public void testGetContentListID()
    {
        assertEquals(1234, list.getContentListID());
    }

    /*
     * Tests the getContentListName method.
     */
    @Test
    public void testGetContentListName()
    {
        assertEquals("Star Wars", list.getContentListName());
    }

    /*
     * Tests the getContentListDescription method.
     */
    @Test
    public void testGetContentListDescription()
    {
        assertEquals("Star Wars Episodes I-VI", list.getContentListDescription());
    }

    /*
     * Tests the getContentListSize method.
     */
    @Test
    public void testGetContentListSize()
    {
        assertEquals(6, list.getContentListSize());
    }

    /*
     * Tests the getList method by checking the IDs of each piece of Content that should be in it.
     */
    @Test
    public void testGetList()
    {
        assertEquals(1, list.getList().get(0).getContentID());
        assertEquals(2, list.getList().get(1).getContentID());
        assertEquals(3, list.getList().get(2).getContentID());
        assertEquals(4, list.getList().get(3).getContentID());
        assertEquals(5, list.getList().get(4).getContentID());
        assertEquals(6, list.getList().get(5).getContentID());
    }

    /*
     * Tests the getListCapacity method (assuming it is 100).
     */
    @Test
    public void testGetListCapacity()
    {
        assertEquals(100, list.getListCapacity());
    }

    /*
     * Tests the setContentListID method.
     */
    @Test
    public void testSetContentListID()
    {
        list.setContentListID(1138);
        assertEquals(1138, list.getContentListID());
    }

    /*
     * Tests the setContentListName method.
     */
    @Test
    public void testSetContentListName()
    {
        list.setContentListName("Sun Battles");
        assertEquals("Sun Battles", list.getContentListName());
    }

    /*
     * Tests the setContentListDescription method.
     */
    @Test
    public void testSetContentListDescription()
    {
        list.setContentListDescription("The classic Star Wars story");
        assertEquals("The classic Star Wars story", list.getContentListDescription());
    }

    /*
     * Tests adding a single Content object to the list successfully.
     */
    @Test
    public void testAddContentSingle()
    {
        Movie newContent = new Movie(7, "Rogue One", "A Star Wars Story", genres,
        genreIDs, (float) 7.0, reviews, buy, rent, flatRate);
        list.addContent(newContent);
        assertEquals("Rogue One", list.getList().get(6).getContentName());
    }

    /*
     * Tests attempting to add singular Content objects that go over the hard-set capacity.
     */
    @Test
    public void testAddContentSingleCapacityReached()
    {
        Movie newContent;
        for (int i = 0; i < (list.getListCapacity() + 10); i++)
        {
            newContent = new Movie(99, "Movie", "Unnecessary", genres,
                genreIDs, (float) 10.0, reviews, buy, rent, flatRate);
            list.addContent(newContent);
        }
        assertEquals(100, list.getContentListSize());
    }

    /*
     * Tests adding a ContentList's Content objects to the list successfully.
     */
    @Test
    public void testAddContentList()
    {
        ContentList secondList = new ContentList();
        secondList.addContent(new Movie(7, "Rogue One", "A Star Wars Story", genres,
        genreIDs, (float) 7.0, reviews, buy, rent, flatRate));
        secondList.addContent(new Movie(8, "Solo", "Another Star Wars Story", genres,
        genreIDs, (float) 8.0, reviews, buy, rent, flatRate));

        list.addContent(secondList.getList());
        assertEquals("Rogue One", list.getList().get(6).getContentName());
        assertEquals("Solo", list.getList().get(7).getContentName());
    }

    /*
     * Tests attempting to add a ContentList's Content objects when the second list is empty.
     */
    @Test
    public void testAddContentListEmpty()
    {
        ContentList secondList = new ContentList();
        list.addContent(secondList.getList());

        assertEquals(6, list.getContentListSize());
    }

    /*
     * Tests attempting to add a ContentList's Content objects when the second list would cause the first to go over
     * the hard-set capacity.
     */
    @Test
    public void testAddContentListCapacityReached()
    {
        ContentList secondList = new ContentList();
        for (int i = 0; i < (list.getListCapacity()); i++)
        {
            secondList.addContent(new Movie(99, "Movie", "Unnecessary", genres,
                genreIDs, (float) 10.0, reviews, buy, rent, flatRate));
        }
        list.addContent(secondList.getList());
        assertEquals(6, list.getContentListSize());
    }

    /*
     * Tests the removeContents method with an empty list.
     */
    @Test
    public void testRemoveContentsEmpty()
    {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        list.removeContents(indexes);
        assertEquals(6, list.getContentListSize());
    }

    /*
     * Tests the removeContents method with only some of the Content in the list.
     */
    @Test
    public void testRemoveContentsPartial()
    {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        indexes.add(0);
        indexes.add(1);
        indexes.add(5);
        list.removeContents(indexes);
        assertEquals(3, list.getContentListSize());
    }

    /*
     * Tests the removeContents method with all the Content in the list.
     */
    @Test
    public void testRemoveContentsAll()
    {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        indexes.add(0);
        indexes.add(1);
        indexes.add(2);
        indexes.add(3);
        indexes.add(4);
        indexes.add(5);
        list.removeContents(indexes);
        assertEquals(0, list.getContentListSize());
    }

    /*
     * Tests the displayList method to ensure no errors occur when it is used.
     */
    @Test
    public void testDisplayList()
    {
        list.displayList();
        assertTrue(true);
    }   
}
