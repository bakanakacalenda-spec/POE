/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.poepart3;
import java.util.Scanner;
/**
 * Main entrance runner execution loop.
 * @author User
 */
public class POEPart3 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
             Registration registrationEngine = new Registration();
            Login loginEngine = new Login();
            Messages manager = null;

            System.out.println("QuickChat Registration");
            System.out.print("Enter your first name: ");
            String firstName = input.nextLine().trim();
            System.out.print("Enter your last name: ");
            String lastName = input.nextLine().trim();
            System.out.print("Enter your username: ");
            String username = input.nextLine().trim();
            System.out.print("Enter password: ");
            String password = input.nextLine().trim();
            System.out.print("Enter cellphone number (+27...): ");
            String cellphone = input.nextLine().trim();

            String registerResult = registrationEngine.registerUser(firstName, lastName, username, password, cellphone);
            System.out.println(registerResult);

            if (registerResult.equals("The user has successfully registered")) {
                User newUser = new User(firstName, lastName, username, password, cellphone);
                loginEngine.setRegisteredUser(newUser);

                System.out.println("\n--- User Login ---");
                System.out.print("Enter username to login: ");
                String loginUsername = input.nextLine().trim();
                System.out.print("Enter password to login: ");
                String loginPassword = input.nextLine().trim();

                String loginResult = loginEngine.returnLoginStatus(loginUsername, loginPassword);
                System.out.println(loginResult);

                if (loginEngine.loginUser(loginUsername, loginPassword)) {
                    System.out.println("\nWelcome to QuickChat.");
                    System.out.print("How many messages do you wish to enter when the application starts? ");
                    int maxAllowedMessages = Integer.parseInt(input.nextLine().trim());
                    
                    manager = new Messages(maxAllowedMessages);
                    manager.populateStoredMessagesFromJSON(); // Preload data out of files
                    
                    int messagesEnteredSoFar = 0;
                    boolean running = true;

                   
                    while (running) {
                        System.out.println("\n====== MAIN MENU =====");
                        System.out.println("1. Add New Message");
                        System.out.println("2. Display All Sent Messages");
                        System.out.println("3. Display Longest Sent Message");
                        System.out.println("4. Search Message by ID");
                        System.out.println("5. Search Messages by Recipient");
                        System.out.println("6. Delete Message by Hash");
                        System.out.println("7. Display Sent Messages Report");
                        System.out.println("0. Exit");
                        System.out.print("Enter your choice: ");
                        String choice = input.nextLine().trim();

                        switch (choice) {
                            case "1": // Add New Message
                                if (messagesEnteredSoFar >= maxAllowedMessages) {
                                    System.out.println("Maximum session entry limit reached.");
                                    break;
                                }
                                System.out.println("\n--- Entering Details for Message " + (messagesEnteredSoFar + 1) + " ---");
                                System.out.print("Enter recipient cellphone number: ");
                                String recipientCell = input.nextLine().trim();
                                System.out.print("Enter message content: ");
                                String msgText = input.nextLine().trim();

                                if (!registrationEngine.checkPhoneNumber(recipientCell)) {
                                    System.out.println("Incorrectly formatted recipient cell number.");
                                    break;
                                }

                                System.out.println("\nChoose message action choice:");
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message to send later");
                                System.out.print("Choice: ");
                                String actionChoice = input.nextLine().trim();

                                manager.processMessageAction(actionChoice, recipientCell, msgText);
                                messagesEnteredSoFar++;
                                break;

                            case "2": // Display All Sent Messages
                                manager.displaySendersAndRecipients();
                                break;

                            case "3": // Display Longest Sent Message
                                manager.displayLongestStoredMessage();
                                break;

                            case "4": // Search Message by ID
                                System.out.print("Enter Message ID to search: ");
                                manager.searchByMessageID(input.nextLine().trim());
                                break;

                            case "5": // Search Messages by Recipient
                                System.out.print("Enter Recipient Cell to search: ");
                                manager.searchByRecipient(input.nextLine().trim());
                                break;

                            case "6": // Delete Message by Hash
                                System.out.print("Enter Message Hash to drop: ");
                                manager.deleteMessageByHash(input.nextLine().trim());
                                break;

                            case "7": // Display Sent Messages Report
                                manager.displayFullReport();
                                break;

                            case "0": // Exit
                                System.out.println("\nTotal number of messages processed: " + manager.getTotalGlobalMessages());
                                System.out.println("Exiting QuickChat... Goodbye!");
                                running = false;
                                break;

                            default:
                                System.out.println("Invalid selection. Please enter a number between 0 and 7.");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An application tracking error occurred: " + e.getMessage());
        }
    }
}