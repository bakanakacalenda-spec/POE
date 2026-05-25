package com.mycompany.poepart2;

import java.util.Scanner;

/**
 * Main application driver code for POE Part 2
 * * @author user
 */
public class POEPart2 {
    public static void main(String[] args) {

    try (Scanner input = new Scanner(System.in)) {
      System.out.println(" ---Registration---");

      System.out.print("Enter your first name: ");
            String firstName = input.nextLine();
      System.out.print("Enter your last name: ");
            String lastName = input.nextLine().trim();


      System.out.print("Enter your username: ");
            String username = input.nextLine().trim();

 
      System.out.print("Enter password: ");
            String password = input.nextLine().trim();

      System.out.println("\n[Start with +27 and be 13 characters total]");
      System.out.print("Enter cellphone number: ");
            String cellphone = input.nextLine().trim();

            Login reg = new Login(firstName, lastName, username, password, cellphone);

            // Call the registration method
            String registerResult = reg.registerUser();
      System.out.println(registerResult);

            // Check if registration was successful
         if (registerResult.equals("The user has successfully registered")) {
      System.out.println("\nUser Login");
      System.out.print("Enter username to login: ");
            String loginUsername = input.nextLine();
      System.out.print("Enter password to login: ");
            String loginPassword = input.nextLine();

            String loginResult = reg.returnLoginStatus(loginUsername, loginPassword);
      System.out.println(loginResult);

               
       if (reg.loginUser(loginUsername, loginPassword)) {
       System.out.println("\nWelcome to QuickChat.");

                    // Ask how many messages they wish to enter once at application start
       System.out.print("How many messages do you wish to enter when the application starts? ");
            int maxAllowedMessages = Integer.parseInt(input.nextLine().trim());
            int messagesEnteredSoFar = 0;

            boolean running = true;
            while (running) {
        System.out.println("\nPlease choose one of the following features from a numeric menu:");
        System.out.println("1) Send Messages");
        System.out.println("2) Show recently sent messages");
        System.out.println("3) Quit");
        System.out.print("Enter your choice: ");

         String choice = input.nextLine().trim();

           switch (choice) {
                case "1":  
                    if (messagesEnteredSoFar >= maxAllowedMessages) {
                        System.out.println("You have reached your maximum session entry limit of " + maxAllowedMessages + " message(s).");
                          break;
                        }

                System.out.println("\n--- Entering Details for Message " + (messagesEnteredSoFar + 1) + " ---");
                System.out.print("Enter recipient cellphone number: ");
                  String recipientCell = input.nextLine().trim();

                System.out.print("Enter message content: ");
                  String msgText = input.nextLine().trim();

                                
                Messages msg = new Messages(recipientCell, msgText, messagesEnteredSoFar + 1);

                 // Print cellphone capture confirmation string directly
                 System.out.println(msg.checkRecipientCell());

                 // Process workflow logic options
                 msg.SentMessage(input);
                                
                 messagesEnteredSoFar++;
                 break;

                   case "2":
                   System.out.println("\nComing Soon.");
                 break;

                    case "3":
                    // Call on matching capitalized class reference
                    System.out.println("\nTotal number of messages processed: " + Messages.returnTotalMessagesText());
                    running = false;
                 break;

                 default:
                    System.out.println("Invalid selection. Please try again.");
                  break;
                        }
                    }
                }
                
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}