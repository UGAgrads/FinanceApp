package com.UGAgrads.freddiefinance;

import java.util.ArrayList;


/*
  Brainstorming Account Creation page inputs...
  
  Account Name:
  Account Type [Dropdown Box]
  Starting Investment:
  Interest Rate: [Fixed??]
  
 */

public class Account {
	
	private String owner;
	private String accountName;
	private String accountType;
	private String balance;
	private String interestRate;
	
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	Account(String accountOwner, String accountName, String accountType, String balance, String interestRate){
		owner = accountOwner;
		this.balance = balance;
		this.accountName = accountName;
		this.accountType = accountType;
		this.interestRate = interestRate;;
	}
	
	public String getBalance(){
		return balance;
	}
	
	public String getAccountName(){
		return accountName;
	}
	
	public String getAccountOwner(){
		return owner;
	}
	
	public String getInterestRate(){
		return interestRate;
	}
	
	public String getAccountType(){
		return accountType;
	}
	
	
	
}
