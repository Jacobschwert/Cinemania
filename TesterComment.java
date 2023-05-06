import org.junit.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TesterComment {

    private Review review;
    private FeedbackFactory feedbackFactory;
    private Content content;
    private Account account;
    private Comment comment;
    @Before
    public void setUp() throws SQLException {
        // Create the piece of content to be reviewed
        content = new Movie(
            1234,
            "The Great Gatsby",
            "A film adaptation of the novel by F. Scott Fitzgerald about the decadence and excess of the Roaring Twenties.",
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
        // Create a new comment on a review
        comment = new Comment("I agree that this was a good movie!!", content,account);
        // Create a FeedbackFactory to handle creation and retreival of the review
        feedbackFactory = new FeedbackFactory();
    }
    @Test
    public void testValidCreation() {
        // Ensure that the comment object was created with the correct length
        assertEquals("I agree that this was a good movie!!", comment.getFeedbackSummary());
    }

    @Test
    public void testCommentLengthExceeded() {
        // Attempt to create a new comment object with text that exceeds 150 characters and expect an IllegalArgumentException to be thrown
        assertThrows(IllegalArgumentException.class, () -> {
            Comment comment = new Comment("This is a movie that I really didn't like at all. It was very poorly made and didn't work properly. I would definitely not recommend it to anyone not even my mother.", content, account);
        });
    }

    @Test
    // Attempt to add a like to created comment
    public void testAddLike() throws SQLException{
        comment.addLike();
        assertEquals(1, comment.getLikes());
    }

    @Test
    //Attempts to remove a like
    public void testRemoveLike() throws SQLException{
        comment.addLike();
        comment.removeLike();
        assertEquals(0, comment.getLikes());
    }

    @Test
    //Atttempts to edit a comment to have a new string
    public void testEditFeedback() throws SQLException{
        Comment comment = new Comment("This is an edited comment",content, account);

        String input = "Y\nNew comment\n5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        comment.editFeedback();
        assertEquals("New comment", comment.getFeedbackSummary());
    }

    @Test
    //Attempts to delete a comment
    public void testDeleteFeedback() throws SQLException{
        Comment comment = new Comment("This is a test comment",content, account);
        String input = "Y";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        comment.deleteFeedback();
        assertNull(comment.getFeedbackSummary());
        assertEquals(null,comment.getFeedbackSummary());
    }
    

}
