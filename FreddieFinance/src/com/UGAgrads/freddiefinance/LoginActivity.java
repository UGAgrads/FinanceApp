package com.UGAgrads.freddiefinance;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class LoginActivity extends Activity {

	EditText password, username;
	public Toast incorrect;
	LoginPresenter loginPresenter;
	LoginActivity activity;

	@SuppressLint("ShowToast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		setContentView(R.layout.activity_login);
		// Show the Up button in the action bar.
		setupActionBar();
		/** sets up the nonUI Login Handler */
		loginPresenter = new LoginPresenter();
		/** sets up toast */
		CharSequence text = "";
		int duration = Toast.LENGTH_SHORT;
		incorrect = Toast.makeText(this, text, duration);
		incorrect.setMargin(.02f, .5f);
		Button loginButton = (Button) findViewById(R.id.loginSubmit);
		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (loginPresenter.attemptLogin(activity)) {
				case 0: // valid password and username
					Intent intent = new Intent(activity, UserHomeActivity.class);
					activity.startActivity(intent);
					break;
				case 1: // invalid password
					showToast(getResources().getString(R.string.invalid_login_password));
					break;
				case 2: // invalid username
					showToast(getResources().getString(R.string.invalid_login_username));
					break;
				}
			}
		});

	}

	protected void showToast(String text) {
		incorrect.setText(text);
		incorrect.show();
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

	@Override
	protected void onPause() {
		super.onPause();
		// remove toast if activity is not showing
		incorrect.cancel();
	}

}
