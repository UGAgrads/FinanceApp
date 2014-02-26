package com.UGAgrads.freddiefinance;

import java.util.ArrayList;

public class User {

	private String username;
	private String password;
	private String email;
	private ArrayList<Account> accounts;
	
	public User(String username, String email, String password){
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public void changeUsername(String username){
		this.username = username;
	}
	
	public void changePassword(String password){
		this.password = password;
	}
	
	public void changeEmail(String email){
		this.email = email;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void populateAccounts(ArrayList<Account> userAccounts){
		accounts = userAccounts;
	}
	
	public ArrayList<Account> getAccounts(){
		return accounts;
	}
	
}