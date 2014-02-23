package com.UGAgrads.freddiefinance;

public class User {

	private static String username;
	private static String password;
	private static String email;
	
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
	
	
	
}