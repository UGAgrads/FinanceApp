package com.UGAgrads.freddiefinance;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText password, username;
	Toast incorrect;
	Logger logger;

	@SuppressLint("ShowToast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// Show the Up button in the action bar.
		setupActionBar();
		/** sets up the nonUI Login Handler */
		logger = new Logger();

		/** sets up toast */
		CharSequence text = getResources().getString(
				R.string.invalid_login);
		int duration = Toast.LENGTH_SHORT;
		incorrect = Toast.makeText(this, text, duration);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	/** Called when the user clicks the login button */
	public void tryLogin(View view) {
		if (logger.attemptLogin(this)) {
			incorrect.cancel();
			// <Snippet Only for M4 replace with database calls for M5
			Intent intent = new Intent(this, UserHomeActivity.class);
			startActivity(intent);
			// \Snippet>
		} else {
			incorrect.show();
		}

	}


	@Override
	protected void onPause() {
		super.onPause();
		// remove toast if activity is not showing
		incorrect.cancel();
	}

}
