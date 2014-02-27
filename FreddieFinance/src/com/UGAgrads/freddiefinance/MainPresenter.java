package com.UGAgrads.freddiefinance;

import android.app.Activity;
import android.util.Log;

public class MainPresenter {
	static DatabaseHelper db;
	public static boolean registerAdmin(Activity activity) {
		db = new DatabaseHelper(activity);
		db.addNewAccountToDatabase(new Account("admin", "main savings", "Brokerage", "123", "2"));
		Log.d("leSawce", String.valueOf(db.getAccountsByOwner("admin").size()));
		if (!db.doesUserAlreadyExist("admin")) {
			db.addNewUserToDatabase(new User("admin", "", "pass123"));
			return true;
		}
		return false;
	}
	
	
}
