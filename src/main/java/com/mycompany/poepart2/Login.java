/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart2;

/**
 *
 * @author user
 */
public class Login {
    //First declare your variables
    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;
    private final String cellphoneNumber;

    //Create the constructor
    public Login(String firstName, String lastName, String userName, String password, String cellphoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;

    }
    public boolean checkPasswordComplexity() {
        if (password.length() < 8){
            return false;
        }
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()\\-+].*");
        return hasUppercase && hasNumber && hasSpecialChar;
    }

    public boolean checkUserName() {
        return userName.contains("_") && userName.length()<=5;

    }

    public boolean checkPhoneNumber() {
        boolean startsWithCode = cellphoneNumber.startsWith("+27");
        boolean correctLength = cellphoneNumber.length() == 13;
        return startsWithCode && correctLength;
    }

    public String registerUser() {
        if(!checkUserName()) {
            return "The username is incorrectly formatted;  please ensure that your username contains an underscore and is no more than five characters in length.";
        } else if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        } else {
            return "The user has successfully registered";
        }
    }

    public boolean loginUser(String enteredUserName, String enteredPassword) {
        return this.userName.equals(enteredUserName) && this.password.equals(enteredPassword);
    }
    public String returnLoginStatus(String entereduserName, String enteredpassword) {
        if(loginUser(entereduserName, enteredpassword)){
            return "Welcome " + this.firstName + " " + this.lastName + ",it is good to see you again. ";

        } else { return "Username or password incorrect, please try again.";

        }
    }
}
