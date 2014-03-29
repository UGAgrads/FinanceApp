package com.UGAgrads.freddiefinance;

import java.util.ArrayList;

/**
 * @author
 */
public class User {

    /**
     * 
     */
    private String username;
    
    /**
     * 
     */
    private String password;
    
    /**
     * 
     */
    private String email;
    
    /**
     * 
     */
    private ArrayList<Account> accounts;
	
    /**
     * @param userUsername FILL IN THIS!!
     * @param userEmail FILL IN THIS!!
     * @param userPassword FILL IN THIS!!
     */
    public User(String userUsername, String userEmail, String userPassword) {
    	this.username = userUsername;
    	this.password = userPassword;
    	this.email = userEmail;
    }
	
    /**
     * @param userUsername FILL IN THIS!!
     */
    public void changeUsername(String userUsername) {
    	this.username = userUsername;
    }
	
    /**
     * @param userPassword FILL IN THIS!!
     */
    public void changePassword(String userPassword) {
    	this.password = userPassword;
    }
	
    /**
     * @param userEmail FILL IN THIS!!
     */
    public void changeEmail(String userEmail) {
    	this.email = userEmail;
    }
	
    /**
     * @return FILL IN THIS!!
     */
    public String getUsername() {
    	return this.username;
    }

	/**
	 * @return FILL THIS IN!
	 */
    public String getPassword() {
    	return this.password;
    }
	
	/**
	 * @return FILL THIS IN!!
	 */
    public String getEmail() {
    	return this.email;
    }
	
	/**
	 * @param userAccounts FILL THIS IN!
	 */
    public void populateAccounts(ArrayList<Account> userAccounts) {
    	accounts = userAccounts;
    }
	
	/**
	 * 
	 * @return FILL THIS IN!!
	 */
    public ArrayList<Account> getAccounts() {
    	return accounts;
    }
}
