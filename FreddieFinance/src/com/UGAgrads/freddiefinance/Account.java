package com.UGAgrads.freddiefinance;


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
	private long totalAssets;
	private String accountType;
	private long interestRate;
	
	Account(String accountOwner, String accountName, String accountType, String accountInvestment){
		owner = accountOwner;
		totalAssets = Long.valueOf(accountInvestment);
		accountName = accountName;
		accountType = accountType;
		interestRate = 0;
	}
	
	public long getTotalAssets(){
		return totalAssets;
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
		
	private void setUpdateAssets(long ammount){
		totalAssets += ammount;
	}
	
	
	
}
