/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart3;

/**
 *
/**
 * Handles validation rules for account registrations.
 * @author User
 */
public class Registration {

    public boolean checkUserName(String userName) {
        return userName != null && userName.contains("_") && userName.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()\\-+].*");
        return hasUppercase && hasNumber && hasSpecialChar;
    }

    public boolean checkPhoneNumber(String cellphoneNumber) {
        return cellphoneNumber != null && cellphoneNumber.startsWith("+27") && cellphoneNumber.length() == 13;
    }

    public String registerUser(String firstName, String lastName, String userName, String password, String cellphoneNumber) {
        if (!checkUserName(userName)) {
            return "The username is incorrectly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        } else if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        } else if (!checkPhoneNumber(cellphoneNumber)) {
            return "Cell phone number is incorrectly formatted. Must start with +27 and be 13 characters total.";
        } else {
            return "The user has successfully registered";
        }
    }

  
}
    
