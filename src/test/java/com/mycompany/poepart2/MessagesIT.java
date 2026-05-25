package com.mycompany.poepart2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Validated Unit tests for the Messages class.
 */
public class MessagesIT {

    private Messages testMessage;
    private final String validRecipient = "+271786930022";
    private final String validContent = "Hi Mike, can you join us for dinner tonight?";

    @BeforeEach
    public void setUp() {
        // Initialize with messageCount = 0 for predictable hash evaluation matching the rubric
        testMessage = new Messages(validRecipient, validContent, 0);
    }

    /**
     * Test of checkMessageID method.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("Testing: checkMessageID");
        assertTrue(testMessage.checkMessageID(), "Message ID should be valid and under 10 characters.");
    }

    /**
     * FIXED: Test of checkRecipientCell method matching exact captured text rules.
     */
    @Test
    public void testCheckRecipientCellValid() {
        System.out.println("Testing: checkRecipientCell (Valid Case)");
        String expected = "Cell phone number successfully captured.";
        String actual = testMessage.checkRecipientCell();
        
        // Using trim() to avoid any hidden trailing newline/space failures
        assertEquals(expected.trim(), actual.trim(), "The recipient confirmation string must match exactly.");
    }

    /**
     * Test of checkRecipientCell method with an invalid format.
     */
    @Test
    public void testCheckRecipientCellInvalid() {
        System.out.println("Testing: checkRecipientCell (Invalid Case)");
        Messages invalidCellMessage = new Messages("0123456789", validContent, 0);
        String expectedPrefix = "Cell phone number is incorrectly formatted";
        String actual = invalidCellMessage.checkRecipientCell();
        assertTrue(actual.startsWith(expectedPrefix), "Should return formatting failure message.");
    }

    /**
     * FIXED: Test of createMessageHash method tracking "HI" + "TONIGHT".
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("Testing: createMessageHash");
        String hash = testMessage.createMessageHash();
        
        assertNotNull(hash);
        // Verifies the message count '0' is positioned correctly between colons
        assertTrue(hash.contains(":0:"), "Hash should contain the message loop index count ':0:'.");
        
        // "Hi" -> HI, "tonight?" -> TONIGHT. Combined = HITONIGHT
        assertTrue(hash.endsWith("HITONIGHT"), "Hash suffix actual value was: " + hash + " (Expected to end with HITONIGHT)");
    }

    /**
     * Test of SentMessage method simulation for 'Send Message' choice.
     */
    @Test
    public void testSentMessageChoiceSend() {
        System.out.println("Testing: SentMessage (Action choice 1 - Send)");
        
        String inputData = "1\n";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(inputData.getBytes()));
            Scanner scanner = new Scanner(System.in);
            
            int initialTotal = Messages.returnTotalMessagess();
            testMessage.SentMessage(scanner);
            
            assertEquals(initialTotal + 1, Messages.returnTotalMessagess(), "Total sent count should increment.");
        } finally {
            System.setIn(stdin);
        }
    }

    /**
     * Test of returnTotalMessagess method.
     */
    @Test
    public void testReturnTotalMessagess() {
        System.out.println("Testing: returnTotalMessagess");
        int total = Messages.returnTotalMessagess();
        assertTrue(total >= 0, "Total message count must be zero or positive.");
    }
}