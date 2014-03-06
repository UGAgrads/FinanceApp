package com.UGAgrads.freddiefinance;

public class AccountHomePresenter {
	
	private static DatabaseHelper db;
	
	public static Account getAccount(AccountHomeActivity activity, String accountName, String username) {
		db = new DatabaseHelper(activity);
		return db.getAccountByOwnerAndAccountName(username, accountName);
	}

}
