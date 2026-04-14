/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart1;
/**
 *
 * @author User
 */
import java.util.Scanner;

public class PoePart1 {

    public static void main(String[] args) {
        // Using try-with-resources to automatically close the Scanner
        Scanner Sc = new Scanner(System.in);
            
            System.out.println("--- Registration Process ---");
            
            System.out.print("Enter first name: ");
            String firstName = Sc.nextLine().trim();
            
            System.out.print("Enter last name: ");
            String lastName = Sc.nextLine().trim();
            
            System.out.print("Enter Username: ");
            String username = Sc.nextLine().trim();
            
            System.out.print("Enter Cellnumber (+27...): ");
            String cellNo = Sc.nextLine().trim();
            
            System.out.print("Enter Password: ");
            String password = Sc.nextLine();
            
            // FIX: Create the object with data immediately (Matches the new Constructor)
            // Order: username, password, cell, first, last
            Login auth = new Login(username, password, cellNo, firstName, lastName);
            
            // Call registration logic
            String regStatus = auth.registerUser();
            System.out.println("\n" + regStatus);
            
            // Check if registration was successful to proceed to Login
            if (regStatus.equals("The user has successfully registered")) {
                System.out.println("\n--- LOGIN ---");
                
                System.out.print("Enter Username: ");
                String loginUser = Sc.nextLine().trim();
                
                System.out.print("Enter Password: ");
                String loginPass = Sc.nextLine();
                
                // Verify credentials
                boolean loginResult = auth.loginUser(loginUser, loginPass);
                
                // Display the final Welcome or Error message
                System.out.println(auth.returnLoginStatus(loginResult));
            }
        }
    }
