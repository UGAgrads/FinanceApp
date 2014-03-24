package com.UGAgrads.freddiefinance;

import android.text.format.DateFormat;

public class Withdrawal extends Transaction {
	
	private String moneyExpense;
	
	public Withdrawal(double amountTransfered, String dateEffective, User user, Account userAccount, String moneyExpense, String withdrawalDescription) {
		super(amountTransfered, dateEffective, user, userAccount, withdrawalDescription);
		this.moneyExpense = moneyExpense;
	}
	
	public String getWithdrawalMoneyExpense(){
		return moneyExpense;
	}

	public String transactionType(){
		return "Withdrawal";
	}
}
