package com.UGAgrads.freddiefinance;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPresenter {
	private DatabaseHelper db;
	public static String loginUsername;
	public static String loginPassword;

	/**
	 * This class handles login of a user
	 * 
	 * @param activity
	 * @return integer value encoding login success information
	 */
	public int attemptLogin(LoginActivity activity) {
		db = new DatabaseHelper(activity);
		loginUsername = ((EditText) activity.findViewById(R.id.usernameEditText))
				.getText().toString();
		loginPassword = ((EditText) activity.findViewById(R.id.passwordEditText))
				.getText().toString();
		User checkingUser = db.getUserByUsername(loginUsername);
		if (checkingUser != null) {
			if (checkingUser.getUsername().compareTo(loginUsername) == 0
					&& checkingUser.getPassword().compareTo(
							loginPassword) == 0) { // valid password and username
				return 0;
			} else { // invalid password
				return 1;  
			}
		} else { // invalid username
			return 2;
		}
	}

}
