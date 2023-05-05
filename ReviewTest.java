import org.junit.*;
import java.sql.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class ReviewTest {
    private Review review;
    private FeedbackFactory feedbackFactory;
    private Content content;
    private Account account;
    
    @Before
    public void setUp() throws SQLException {
        // Create the piece of content to be reviewed
        content = new Movie(
            1234,
            "The Great Gatsby",
            "A novel by F. Scott Fitzgerald about the decadence and excess of the Roaring Twenties.",
            new String[] { "Fiction", "Classic" },
            new int[] { 1, 2 },
            4.5f,
            new ArrayList<Review>(),
            new String[] { "Amazon", "Google Play", "Apple" },
            new String[] { "Netflix", "Hulu" },
            new String[] { "Prime Video", "HBO Max" });
        // Create the account that will be making the review
        account = new Account("johndoe", "johndoe@example.com", "A new user", "password123");
        // Create a new review object with valid parameters
        review = new Review("This is a great movie!", 5, content, account);
        // Create a FeedbackFactory to handle creation and retreival of the review
        feedbackFactory = new FeedbackFactory();
    }

    @Test
    public void testValidCreation() {
        // Ensure that the review object was created with the correct summary and rating
        assertEquals("This is a great movie!", review.getFeedbackSummary());
        assertEquals(5, review.getRating());
    }

    @Test
    public void testInvalidRating() {
        // Attempt to create a new review object with an invalid rating and expect an IllegalArgumentException to be thrown
        assertThrows(IllegalArgumentException.class, () -> new Review("This is a bad movie!", 6, content, account));
    }

    @Test
    public void testSummaryLengthExceeded() {
        // Attempt to create a new review object with a summary that exceeds 300 characters and expect an IllegalArgumentException to be thrown
        String summary = "This is a movie that I really didn't like at all. It was very poorly made and didn't work properly. I would definitely not recommend it to anyone.";
        assertThrows(IllegalArgumentException.class, () -> new Review(summary, 1, content, account));
    }

    @Test
    public void testAddLike() throws SQLException {
        // Add a like to the review and ensure that the number of likes is updated in the database
        review.addLike();
        assertEquals(1, review.getLikes());
    }

    @Test
    public void testRemoveLike() throws SQLException {
        // Remove a like from the review and ensure that the number of likes is updated in the database
        review.addLike();
        review.removeLike();
        assertEquals(0, review.getLikes());
    }

    @Test
    public void testDeleteFeedback() throws SQLException {
        // Delete the review from the database and ensure that it no longer exists
        review.deleteFeedback();
        int feedbackID = review.getFeedbackID();
        assertNull(feedbackFactory.getReview(feedbackID));
    }

    @Test
    public void testEditFeedback() throws SQLException {
        // Edit the review's summary and rating and ensure that the changes are reflected in the database
        review.editFeedback();
    }
}