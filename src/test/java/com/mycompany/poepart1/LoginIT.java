/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests specifically for her Login class implementation.
 */
public class LoginIT {

    // Test Data from the rubric
    private final String validUser = "kyl_1";
    private final String validPass = "Ch&&sec@ke99!";
    private final String validCell = "+27838968976";
    private final String firstName = "John";
    private final String lastName = "Doe";

    @Test
    public void testUsernameValidation() {
        // Setup valid user
        Login loginValid = new Login(validUser, validPass, validCell, firstName, lastName);
        assertTrue(loginValid.checkUsername(validUser), "Username should be valid");

        // Setup invalid user (too long)
        Login loginInvalid = new Login("kyle!!!!!!!", validPass, validCell, firstName, lastName);
        assertFalse(loginInvalid.checkUsername("kyle!!!!!!!"), "Username should be invalid");
    }

    @Test
    public void testPasswordComplexity() {
        // Setup valid password
        Login loginValid = new Login(validUser, validPass, validCell, firstName, lastName);
        assertTrue(loginValid.CheckPasswordComplexity(validPass), "Password complexity should pass");

        // Setup invalid password (no caps/numbers)
        Login loginInvalid = new Login(validUser, "password", validCell, firstName, lastName);
        assertFalse(loginInvalid.CheckPasswordComplexity("password"), "Password complexity should fail");
    }

    @Test
    public void testRegistrationReturnMessage() {
        Login login = new Login(validUser, validPass, validCell, firstName, lastName);
        
        // Exact string match from her registerUser() method
        String expected = "The user has successfully registered";
        assertEquals(expected, login.registerUser(), "Registration message should match successfully");
    }

    @Test
    public void testLoginAuthentication() {
        Login login = new Login(validUser, validPass, validCell, firstName, lastName);

        // Test correct login
        assertTrue(login.loginUser(validUser, validPass), "Login should return true for correct credentials");

        // Test incorrect login
        assertFalse(login.loginUser(validUser, "wrong_password"), "Login should return false for wrong password");
    }

    @Test
    public void testLoginStatusMessage() {
        Login login = new Login(validUser, validPass, validCell, firstName, lastName);
        
        // Test Success Message
        String expectedWelcome = "Welcome John, Doe it is great to see you again.";
        assertEquals(expectedWelcome, login.returnLoginStatus(true));
        
        // Test Failure Message
        String expectedError = "Username or password incorrect, please try again.";
        assertEquals(expectedError, login.returnLoginStatus(false));
    }
}