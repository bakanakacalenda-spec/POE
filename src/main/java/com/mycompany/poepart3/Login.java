/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart3;


/**
 * Handles authenticating active login sessions.
 * @author 
 */
public class Login {
    private User registeredUser;
    public void setRegisteredUser(User user) {
        this.registeredUser = user;
    }

    public boolean loginUser(String enteredUserName, String enteredPassword) {
        if (registeredUser == null) return false;
        return registeredUser.getUserName().equals(enteredUserName) && 
               registeredUser.getPassword().equals(enteredPassword);
    }

    public String returnLoginStatus(String enteredUserName, String enteredPassword) {
        if (loginUser(enteredUserName, enteredPassword)) {
            return "Welcome " + registeredUser.getFirstName() + " " + registeredUser.getLastName() + ", it is good to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

   
}