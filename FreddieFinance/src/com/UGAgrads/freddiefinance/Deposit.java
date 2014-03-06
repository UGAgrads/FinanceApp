package com.UGAgrads.freddiefinance;

import android.text.format.DateFormat;

public class Deposit extends Transaction {
	
	private Source moneySource;
	
	Deposit(double amountTransfered, DateFormat dateEffective, User user, Account userAccount, Source moneySource, String depositDescription) {
		super(amountTransfered, dateEffective, user, userAccount, depositDescription);
		this.moneySource = moneySource;
	}
	
	public String getDepositMoneySource(){
		return moneySource.getValue();
	}
	
	@Override
	public String getSpendSourceInfo(){
		return getDepositMoneySource();
	}

}
