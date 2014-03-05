package com.UGAgrads.freddiefinance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserHomePresenter {
	private static ArrayList<String> accountsList;
	private static DatabaseHelper db;

	public static ArrayList<String> getAccounts(UserHomeActivity activity) {
		
		db = new DatabaseHelper(activity);
		
		// The data to show
		accountsList = new ArrayList<String>();

		List<Account> accounts = db
				.getAccountsByOwner(LoginPresenter.loginUsername);
		for (Account account : accounts) {
			accountsList.add(account.getAccountName());
		}

		Collections.sort(accountsList);

		/** create new account always at end */
		accountsList.add("CREATE NEW ACCOUNT");
		
		return accountsList;

	}
}
