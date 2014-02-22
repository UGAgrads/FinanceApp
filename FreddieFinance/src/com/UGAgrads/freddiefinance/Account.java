package com.UGAgrads.freddiefinance;

public class Account {
	
	private static String owner;
	private static String accountNumber;
	private static long totalAssets;
	private static String accountType;
	private static long interestRate;
	
	Account(String newAccountOwner, String newAccountType){
		owner = newAccountOwner;
		totalAssets = 0;
		accountNumber = newAccountType.substring(0, 1) + "-" + (int)(Math.random() * 100);
		accountType = newAccountType;
		interestRate = 0;
	}
	
	public long getTotalAssets(){
		return totalAssets;
	}
	
	public String getAccountNumber(){
		return accountNumber;
	}
	
	public String getAccountOwner(){
		return owner;
	}
	
	private void setUpdateAssets(long ammount){
		totalAssets += ammount;
	}
	
	
	
	//make transactions
	//update assets
	
	
}
