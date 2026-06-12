/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart3;

/**
 *
 * @author User
 */
public class User {
    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;
    private final String cellphoneNumber;

    public User(String firstName, String lastName, String userName, String password, String cellphoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUserName() { return userName; }
    public String getPassword() { return password; }
    public String getCellphoneNumber() { return cellphoneNumber; }
}
    
