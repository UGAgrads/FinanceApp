package com.UGAgrads.freddiefinance;

import android.text.format.DateFormat;

public class Withdrawal extends Transaction {
	
	private Expense moneyExpense;
	
	public Withdrawal(double amountTransfered, DateFormat dateEffective, User user, Account userAccount, Expense moneyExpense, String withdrawalDescription) {
		super(amountTransfered, dateEffective, user, userAccount, withdrawalDescription);
		this.moneyExpense = moneyExpense;
	}
	
	public String getWithdrawalMoneyExpense(){
		return moneyExpense.getValue();
	}

	@Override
	public String getSpendSourceInfo(){
		return getWithdrawalMoneyExpense();
	}
}
