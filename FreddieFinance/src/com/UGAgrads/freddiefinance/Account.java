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
	private long balance;
	private long interestRate;
	
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	Account(String accountOwner, String accountName, String accountType, String balance, String interestRate){
		owner = accountOwner;
		this.balance = Long.valueOf(balance);
		this.accountName = accountName;
		this.accountType = accountType;
		this.interestRate = Long.valueOf(interestRate);
	}
	
	public long getBalance(){
		return balance;
	}
	
	public String getAccountName(){
		return accountName;
	}
	
	public String getAccountOwner(){
		return owner;
	}
	
	public long getInterestRate(){
		return interestRate;
	}
	
	public String getAccountType(){
		return accountType;
	}
		
	public void setAssets(long ammount){
		balance = ammount;
	}
	
	
	
}
