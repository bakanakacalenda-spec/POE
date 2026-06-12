/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart3;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Component managing messaging data operations using parallel arrays.
 * @author User
 */
public class Messages {
    // Parallel arrays required by Part 3 Rubric
    private String[] sentMessages;
    private String[] disregardedMessages;
    private String[] storedMessages;
    private String[] messageHashes;
    private String[] messageIDs;
    private String[] recipients; // Helper parallel array for tracking recipients

    private int maxCapacity;
    private int sentCount = 0;
    private int disregardCount = 0;
    private int storeCount = 0;
    private int totalGlobalMessages = 0;

    public Messages(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.sentMessages = new String[maxCapacity];
        this.disregardedMessages = new String[maxCapacity];
        this.storedMessages = new String[maxCapacity];
        this.messageHashes = new String[maxCapacity];
        this.messageIDs = new String[maxCapacity];
        this.recipients = new String[maxCapacity];
    }

    // Generate custom 10-digit tracking string
    public String generateMessageID() {
        Random rand = new Random();
        long num = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(num);
    }

    public String createMessageHash(String msgID, String content, int currentCount) {
        if (msgID == null || msgID.length() < 2) return "00:0:EMPTY";
        String firstTwoId = msgID.substring(0, 2);
        String[] words = content.trim().split("\\s+");
        String firstWord = (words.length > 0) ? words[0].replaceAll("[^a-zA-Z]", "") : "";
        String lastWord = (words.length > 0) ? words[words.length - 1].replaceAll("[^a-zA-Z]", "") : "";
        return (firstTwoId + ":" + currentCount + ":" + firstWord + lastWord).toUpperCase();
    }

    public void processMessageAction(String choice, String recipient, String content) {
        if (content.length() > 250) {
            System.out.println("Please enter a message of less than 250 characters.");
            return;
        }

        String msgID = generateMessageID();
        totalGlobalMessages++;
        String hash = createMessageHash(msgID, content, totalGlobalMessages);

        // Map data systematically across parallel arrays
        messageIDs[totalGlobalMessages - 1] = msgID;
        messageHashes[totalGlobalMessages - 1] = hash;
        recipients[totalGlobalMessages - 1] = recipient;

       switch (choice) {
            case "1": // Send Message
                sentMessages[totalGlobalMessages - 1] = content; // Aligning index
                sentCount++;
                System.out.println("Message successfully sent.");
                System.out.println(msgID + ", " + hash + ", " + recipient + ", " + content);
                break;
            case "2": // Disregard Message
                disregardedMessages[totalGlobalMessages - 1] = content; // Aligning index
                disregardCount++;
                System.out.println("Press 0 to delete the message.");
                break;
            case "3": // Store Message
                storedMessages[totalGlobalMessages - 1] = content; // Aligning index
                storeCount++;
                System.out.println("Message successfully stored.");
                break;
            default:
                System.out.println("Unknown command selection applied.");
                break;
        }
    }
    // Feature 2a: Display sender and recipient of all stored messages
    public void displaySendersAndRecipients() {
        System.out.println("\n--- Stored Messages Senders & Recipients ---");
        boolean found = false;
        for (int i = 0; i < totalGlobalMessages; i++) {
            if (messageIDs[i] != null && isMessageStored(messageHashes[i])) {
                System.out.println("Hash: " + messageHashes[i] + " | Recipient: " + recipients[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No stored messages to display.");
    }

    // Feature 2b: Display the longest stored message
    public String displayLongestStoredMessage() {
        String longest = "";
        for (int i = 0; i < totalGlobalMessages; i++) {
            // Check matching tracking hash against stored entries safely
            if (isMessageStored(messageHashes[i]) && messageIDs[i] != null) {
                // Find message text associated with this index location
                String currentMsg = getMessageTextByIndex(i);
                if (currentMsg.length() > longest.length()) {
                    longest = currentMsg;
                }
            }
        }
        if (!longest.isEmpty()) {
            System.out.println("Longest stored message: " + longest);
        } else {
            System.out.println("No stored messages found.");
        }
        return longest;
    }

    // Feature 2c: Search for a message ID
    public String searchByMessageID(String targetID) {
        for (int i = 0; i < totalGlobalMessages; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(targetID)) {
                String result = "Recipient: " + recipients[i] + ", Message: " + getMessageTextByIndex(i);
                System.out.println(result);
                return result;
            }
        }
        System.out.println("Message ID not found.");
        return "Message ID not found.";
    }

    // Feature 2d: Search for all messages stored for a particular recipient
    public String searchByRecipient(String targetRecipient) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < totalGlobalMessages; i++) {
            if (recipients[i] != null && recipients[i].equals(targetRecipient)) {
                sb.append(getMessageTextByIndex(i)).append("\n");
            }
        }
        if (sb.length() > 0) {
            System.out.print(sb.toString());
            return sb.toString().trim();
        } else {
            System.out.println("No messages found for recipient.");
            return "No messages found.";
        }
    }

    // Feature 2e: Delete a message using the message hash
    public boolean deleteMessageByHash(String targetHash) {
        for (int i = 0; i < totalGlobalMessages; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(targetHash)) {
                String deletedMsg = getMessageTextByIndex(i);
                
                // Clear out contents across your parallel indexing tracks
                messageIDs[i] = null;
                messageHashes[i] = null;
                recipients[i] = null;
                clearMessageFromStatusArrays(deletedMsg);

                System.out.println("Message: \"" + deletedMsg + "\" successfully deleted.");
                return true;
            }
        }
        System.out.println("Message hash not found.");
        return false;
    }

    // Feature 2f: Display a report that lists the full details of all messages
    public void displayFullReport() {
        System.out.println("\n================ FULL MESSAGE REPORT ================");
        for (int i = 0; i < totalGlobalMessages; i++) {
            if (messageIDs[i] != null) {
                System.out.println("ID: " + messageIDs[i]);
                System.out.println("Hash: " + messageHashes[i]);
                System.out.println("Recipient: " + recipients[i]);
                System.out.println("Content: " + getMessageTextByIndex(i));
                System.out.println("----------------------------------------------------");
            }
        }
    }

    // Requirement 1: Load stored messages out of JSON architecture into arrays
    public void populateStoredMessagesFromJSON() {
        try (BufferedReader br = new BufferedReader(new FileReader("messages.json"))) {
            String line;
            String currentMessage = null;
            while ((line = br.readLine()) != null) {
                if (line.contains("\"Message\":")) {
                    currentMessage = line.split("\"Message\":")[1].replace("\"", "").replace(",", "").trim();
                    if (storeCount < maxCapacity) {
                        storedMessages[storeCount++] = currentMessage;
                    }
                }
            }
            System.out.println("Stored messages array updated from JSON database file.");
        } catch (IOException e) {
            System.out.println("No existing JSON database discovered to parse yet.");
        }
    }

    // Helper utilities to balance data elements across arrays safely
    private boolean isMessageStored(String hash) {
        for (int i = 0; i < totalGlobalMessages; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash)) {
                return storedMessages[i] != null;
            }
        }
        return false;
    }

    private String getMessageTextByIndex(int index) {
        if (sentMessages[index] != null) return sentMessages[index];
        if (storedMessages[index] != null) return storedMessages[index];
        if (disregardedMessages[index] != null) return disregardedMessages[index];
        return "";
    }

    private void clearMessageFromStatusArrays(String text) {
        for (int i = 0; i < maxCapacity; i++) {
            if (sentMessages[i] != null && sentMessages[i].equals(text)) sentMessages[i] = null;
            if (storedMessages[i] != null && storedMessages[i].equals(text)) storedMessages[i] = null;
            if (disregardedMessages[i] != null && disregardedMessages[i].equals(text)) disregardedMessages[i] = null;
        }
    }

    public int getTotalGlobalMessages() { return totalGlobalMessages; }
    public String[] getSentMessages() { return sentMessages; }
    
    public String[] getMessageIDs() { return messageIDs; }
    public String[] getMessageHashes() { return messageHashes; }
}