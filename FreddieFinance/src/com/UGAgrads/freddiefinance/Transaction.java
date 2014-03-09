package com.UGAgrads.freddiefinance;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

public abstract class Transaction {
	double quantity;
	String dateEntered;
	String dateEffective;
	Account userAccount;
	User user;
	String description;
	

	@SuppressLint("SimpleDateFormat")
	Transaction(double amountTransfered, String dateEffective, User user, Account userAccount, String transactionDescription) {
		Calendar c = Calendar.getInstance();
		System.out.println("Current time => " + c.getTime());
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		this.dateEntered = df.format(c.getTime());
		this.dateEffective = dateEffective;
		this.quantity = amountTransfered;
		this.user = user;
		this.userAccount = userAccount;
		this.description = transactionDescription;
	}
	
	public double getTransactionAmmount(){
		return quantity;
	}
	
	public User getTransactionUser(){
		return user;
	}
	
	public String getTransactionUsername(){
		return user.getUsername();
	}
	
	public Account getTransactionUserAccount(){
		return userAccount;
	}
	
	public String getTransactionAccountName(){
		return userAccount.getAccountName();
	}
	
	public String getTransactionDateEntered(){
		return dateEntered;
	}
	
	public String getTransactionDateEffective(){
		return dateEffective;
	}
	
	public String getTransactionDescription(){
		return description;
	}
	
	public String getSpendSourceInfo(){
		return "";
	}

}
