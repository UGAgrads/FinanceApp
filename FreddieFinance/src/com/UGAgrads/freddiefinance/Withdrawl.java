package com.UGAgrads.freddiefinance;

import android.text.format.DateFormat;

public class Withdrawl extends Transaction {
	
	private String moneyExpense;
	
	public Withdrawl(double amountTransfered, String dateEffective, User user, Account userAccount, String moneyExpense, String withdrawalDescription) {
		super(amountTransfered, dateEffective, user, userAccount, withdrawalDescription);
		this.moneyExpense = moneyExpense;
	}
	
	public String getWithdrawalMoneyExpense(){
		return moneyExpense;
	}

}
