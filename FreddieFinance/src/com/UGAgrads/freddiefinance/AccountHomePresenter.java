package com.UGAgrads.freddiefinance;

public class AccountHomePresenter {
	
	private static DatabaseHelper db;
	
	public static Account getAccount(AccountHomeActivity activity, Account account) {
		db = new DatabaseHelper(activity);
		return db.getAccountByAccountName(account.getAccountName());
	}

}
