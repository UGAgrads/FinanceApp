package com.UGAgrads.freddiefinance;

import android.text.format.DateFormat;

public class Deposit extends Transaction {
	
	private String mnySrc;
	

	Deposit(double amountTransfered, String dateEffective, User user, Account userAccount, String moneySource, String depositDescription) {
		super(amountTransfered, dateEffective, user, userAccount, depositDescription);
		this.mnySrc = moneySource;
	}
	
	public String getDepositMoneySource(){
		return mnySrc;
	}


}
