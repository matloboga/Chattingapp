import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // Test 1: Message Length Validation
    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        // Create a string longer than 250 characters
        String longText = "A".repeat(260);
        Message msg = new Message(0, "+27718693002", longText);
        assertEquals("Message exceeds 250 characters by 10 characters; please reduce the size.", msg.checkMessageLength());
    }

    // Test 2: Cell Phone Number Formatting
    @Test
    public void testRecipientCellSuccess() {
        Message msg = new Message(0, "+27718693002", "Hello");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message(0, "0837973889", "Hello");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    // Test 3: Message Hash Generation
    @Test
    public void testMessageHashCreation() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = msg.createMessageHash();
        
        // Asserts that the generated hash ends with the expected pattern
        assertTrue(hash.endsWith(":0:HITONIGHT"));
    }

    // Test 4: Action Selections (Send, Disregard, Store)
    @Test
    public void testSendMessageOptions() {
        Message msg = new Message(0, "+27718693002", "Test");
        assertEquals("Message successfully sent", msg.SentMessage(1));
        assertEquals("Press 0 to delete the message", msg.SentMessage(2));
        assertEquals("Message successfully stored", msg.SentMessage(3));
    }
}