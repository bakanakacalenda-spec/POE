/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart1;

import java.util.regex.Pattern;

/**
 *
 * @author User
 */

public class Login {
    private final String storedUsername;
    private final String storedPassword;
    private final String storedFirstName;
    private final String storedLastName;
    private final String cellNumber;

    // Fixed: This is now a proper Constructor
    public Login(String username, String pass, String cell, String first, String last) {
        this.storedUsername = username;
        this.storedPassword = pass;
        this.cellNumber = cell;
        this.storedFirstName = first;
        this.storedLastName = last;
    }

    public boolean checkUsername(String kyl_1) {
        return storedUsername != null && storedUsername.contains("_") && storedUsername.length() <= 5;
    }

    public boolean checkCellNumber(String string) {
        String regex = "^\\+27[0-9]{10}$"; // Adjust to {10} if you want 13 total chars (+27 + 10 digits)
        return Pattern.compile(regex).matcher(cellNumber).matches();
    }

    public boolean CheckPasswordComplexity(String ch6k1ing) {
        if (storedPassword == null || storedPassword.length() < 8) return false;
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : storedPassword.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    public String registerUser() {
        if (!checkUsername("kyl_1")) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!CheckPasswordComplexity("Ch@6k1ing")) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        return "The user has successfully registered";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(this.storedUsername) && enteredPassword.equals(this.storedPassword);
    }

    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) {
            return "Welcome " + storedFirstName + ", " + storedLastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }


}