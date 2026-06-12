package com.mycompany.poepart3;

// Notice these imports are now using "jupiter.api" (JUnit 5)
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Automated Unit/Integration Tests for the Messages class (JUnit 5 version).
 */
public class MessagesIT {
    
    private Messages manager;

    // In JUnit 5, @Before is changed to @BeforeEach
    @BeforeEach
    public void setUp() {
        // Initialize the Messages class with a capacity of 10 for testing
        manager = new Messages(10);
        
        // Test Data Message 1 (Sent)
        manager.processMessageAction("1", "+27834557896", "Did you get the cake?");
        
        // Test Data Message 2 (Stored)
        manager.processMessageAction("3", "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        
        // Test Data Message 3 (Disregard)
        manager.processMessageAction("2", "+27834484567", "Yohoooo, I am at your gate.");
        
        // Test Data Message 4 (Sent)
        manager.processMessageAction("1", "0838884567", "It is dinner time !");
        
        // Test Data Message 5 (Stored)
        manager.processMessageAction("3", "+27838884567", "Ok, I am leaving without you.");
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        String[] sent = manager.getSentMessages();
        
        // The first sent message goes to parallel index 0
        assertEquals("Did you get the cake?", sent[0]);
        // The second sent message (Message 4) goes to parallel index 3
        assertEquals("It is dinner time !", sent[3], "Message 4 should be at index 3");
    }

    @Test
    public void testDisplayLongestMessage() {
        String expectedLongest = "Where are you? You are late! I have asked you to be on time.";
        String actualLongest = manager.displayLongestStoredMessage();
        
        assertEquals(expectedLongest, actualLongest);
    }

    @Test
    public void testSearchForMessageID() {
        // Message 4 is the 4th message processed (index 3 in parallel arrays)
        String targetID = manager.getMessageIDs()[3]; 
        
        String expectedOutput = "Recipient: 0838884567, Message: It is dinner time !";
        String actualOutput = manager.searchByMessageID(targetID);
        
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSearchMessagesByRecipient() {
        // Test Data: +27838884567
        // Should find Message 2 and Message 5
        String expectedOutput = "Where are you? You are late! I have asked you to be on time.\nOk, I am leaving without you.";
        String actualOutput = manager.searchByRecipient("+27838884567");
        
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDeleteMessageUsingHash() {
        // Message 2 is the 2nd message processed (index 1 in parallel arrays)
        String targetHash = manager.getMessageHashes()[1];
        
        // The delete method returns true if successful
        boolean isDeleted = manager.deleteMessageByHash(targetHash);
        
        // FIXED FOR JUNIT 5: Condition comes first, custom message comes last
        assertTrue(isDeleted, "Message should be successfully deleted.");
        
        // FIXED FOR JUNIT 5: Object comes first, custom message comes last
        assertNull(manager.getMessageHashes()[1], "Hash should be null after deletion.");
    }

    @Test
    public void testDisplayReport() {
        // Ensuring the standard console formatting loop runs to completion without errors
        try {
            manager.displayFullReport();
            assertTrue(true); 
        } catch (Exception e) {
            fail("Display report threw an error: " + e.getMessage());
        }
    }
}