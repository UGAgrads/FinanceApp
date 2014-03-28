package com.UGAgrads.freddiefinance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

public abstract class Transaction {
	double quantity;
	Date dateEntered;
	Date dateEffective;
	Account userAccount;
	User user;
	String description;
	

	@SuppressLint("SimpleDateFormat")
	Transaction(double amountTransfered, String dateEffective, User user, Account userAccount, String transactionDescription) {
		Calendar c = Calendar.getInstance();
		System.out.println("Current time => " + c.getTime());
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		this.dateEntered = c.getTime();
		try {
			this.dateEffective = df.parse(dateEffective);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.quantity = amountTransfered;
		this.user = user;
		this.userAccount = userAccount;
		this.description = transactionDescription;
	}
	
	public double getTransactionAmount(){
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
	
	public Date getTransactionDateEntered(){
		return dateEntered;
	}
	
	public Date getTransactionDateEffective(){
		return dateEffective;
	}
	
	public String getTransactionDescription(){
		return description;
	}

	public String transactionType(){
		return "Transaction";
	}

	public String getSpendSourceInfo() {
		return "";
	}

}
