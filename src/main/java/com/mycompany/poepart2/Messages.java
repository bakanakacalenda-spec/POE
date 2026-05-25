package com.mycompany.poepart2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Messages class handles all message-related functionality for QuickChat.
 * Fully compliant with NetBeans compilation and assignment requirements.
 */
public class Messages {

    static String returnTotalMessagesText() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Instance variables
    private final String recipient;
    private final String messageContent;
    private final String messageID;
    private final int messageCount;

    // Static collections to hold sent messages during runtime context tracking
    private static final List<Messages> sentMessages = new ArrayList<>();
    private static int totalGlobalMessages = 0;

    /**
     * Creates a new message object.
     *
     * @param recipient      The recipient's cell number
     * @param messageContent The message text
     * @param count          The running message count (used in hash)
     */
    public Messages(String recipient, String messageContent, int count) {
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageCount = count;

        // Generate a random 10-digit tracking string
        Random rand = new Random();
        long num = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        this.messageID = String.valueOf(num);
        System.out.println("Message ID generated: <" + this.messageID + ">");
    }

    // Task Rubric Requirement validations: returns true if ID is 10 or fewer characters
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    // Ensures that the recipient cell number meets requirements
    public String checkRecipientCell() {
        if (this.recipient != null && this.recipient.startsWith("+27") && this.recipient.length() == 13) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Creates custom formatting payload using assignment logic rules
    public String createMessageHash() {
        if (this.messageID == null || this.messageID.length() < 2) {
            return "00:0:EMPTY";
        }
        
        String firstTwoId = this.messageID.substring(0, 2);

        // Split words by blank spaces safely
        String[] words = this.messageContent.trim().split("\\s+");
        
        // FIXED: Explicitly pulling individual array strings to avoid array-to-string cast compilation errors
        String firstWord = (words != null && words.length > 0) ? words[0] : "";
        String lastWord = (words != null && words.length > 0) ? words[words.length - 1] : "";

        // Clean off punctuation symbols
        firstWord = firstWord.replaceAll("[^a-zA-Z]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z]", "");
        
        // Format is: first two digits of ID : message count variable : text strings combined
        String combinedHash = firstTwoId + ":" + this.messageCount + ":" + firstWord + lastWord;
        return combinedHash.toUpperCase();
    }

    // Interactive confirmation workflow window tracking choice tasks
    public void SentMessage(Scanner input) {
        // Validate Content length constraints upfront against exact assignment specification rules
        if (this.messageContent.length() > 250) {
            System.out.println("Please enter a message of less than 250 characters.");
            return;
        } else {
            System.out.println("Message sent");
        }

        System.out.println("\nChoose message action choice:");
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later");
        System.out.print("Choice: ");
        String choice = input.nextLine().trim();

        switch (choice) {
            case "1":
                System.out.println("Message successfully sent.");
                sentMessages.add(this);
                totalGlobalMessages++;
                printMessages(); // Prints structural layout directly after completion sequence
                break;
            case "2":
                System.out.println("Press 0 to delete the message.");
                break;
            case "3":
                System.out.println("Message successfully stored.");
                storeMessage(); // Runs JSON architecture generation routine
                totalGlobalMessages++;
                break;
            default:
                System.out.println("Unknown command selection applied.");
                break;
        }
    }

    // Diagnostic console dump layout matching assignment specifications precisely (Message ID, Message Hash, Recipient, Message)
    public void printMessages() {
        System.out.println(this.messageID + ", " + createMessageHash() + ", " + this.recipient + ", " + this.messageContent);
    }

    // FIXED: Formatted method signature name to perfectly match assignment table requirements for returnTotalMessagess()
    public static int returnTotalMessagess() {
        return totalGlobalMessages;
    }

    // Basic JSON File Writer serialization logic block
    public void storeMessage() {
        String jsonPayload = "{\n" +
                "  \"MessageID\": \"" + this.messageID + "\",\n" +
                "  \"MessageHash\": \"" + createMessageHash() + "\",\n" +
                "  \"Recipient\": \"" + this.recipient + "\",\n" +
                "  \"Message\": \"" + this.messageContent + "\"\n" +
                "}";

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(jsonPayload + "\n");
        } catch (IOException e) {
            System.out.println("Error processing storage: " + e.getMessage());
        }
    }
}