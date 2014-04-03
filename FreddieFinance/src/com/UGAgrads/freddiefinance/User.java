package com.UGAgrads.freddiefinance;

import java.util.ArrayList;

/**
 * A class representing the User
 * 
 * @author
 */
public class User {

    /**
     * The user's username
     */
    private String username;
    
    /**
     * The user's password
     */
    private String password;
    
    /**
     * The user's email
     */
    private String email;
    
    /**
     * The user's list of accounts
     */
    private ArrayList<Account> accounts;
	
    /**
     * Constructor for a user object
     * 
     * @param userUsername The user's username
     * @param userEmail the user's email
     * @param userPassword the user's password
     */
    public User(String userUsername, String userEmail, String userPassword) {
    	this.username = userUsername;
    	this.password = userPassword;
    	this.email = userEmail;
    }
	
    /**
     * Allows the user's username to change
     * 
     * @param userUsername The new username
     */
    public void changeUsername(String userUsername) {
    	this.username = userUsername;
    }
	
    /**
     * Allows the user's password to change
     * 
     * @param userPassword the new password
     */
    public void changePassword(String userPassword) {
    	this.password = userPassword;
    }
	
    /**
     * Allows the user's email to change
     * @param userEmail the new email
     */
    public void changeEmail(String userEmail) {
    	this.email = userEmail;
    }
	
    /**
     * @return Returns the username
     */
    public String getUsername() {
    	return this.username;
    }

	/**
	 * @return returns the password
	 */
    public String getPassword() {
    	return this.password;
    }
	
	/**
	 * @return returns the email
	 */
    public String getEmail() {
    	return this.email;
    }
	
	/**
	 * Sets the user's accounts
	 * 
	 * @param userAccounts the list of accounts
	 */
    public void populateAccounts(ArrayList<Account> userAccounts) {
    	accounts = userAccounts;
    }
	
	/**
	 * 
	 * @return the list of accounts
	 */
    public ArrayList<Account> getAccounts() {
    	return accounts;
    }
}
