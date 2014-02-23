package com.UGAgrads.freddiefinance;

import android.app.Activity;

public class MainPresenter {
	static DatabaseHelper db;
	public static boolean registerAdmin(Activity activity) {
		db = new DatabaseHelper(activity);
		if (!db.doesUserAlreadyExist("admin")) {
			db.addNewUserToDatabase(new User("admin", "", "pass123"));
			return true;
		}
		return false;
	}
}
