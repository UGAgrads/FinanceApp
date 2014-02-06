package com.UGAgrads.freddiefinance;

import android.app.Activity;
import android.util.Log;
import android.widget.EditText;

public class Logger {

	public boolean attemptLogin(Activity activity) {
		EditText password = (EditText) activity.findViewById(R.id.passwordEditText);
		EditText username = (EditText) activity.findViewById(R.id.usernameEditText);
		// <Snippet Only for M4 replace with database calls for M5
		if (password.getText().toString().equals("pass123")
				&& username.getText().toString().equals("admin")) {
			return true;
		} else {
			return false;
		}
		// \Snippet>
	}

}
