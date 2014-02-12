package com.UGAgrads.freddiefinance;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class LoginActivity extends Activity {

	EditText password, username;
	Toast incorrect;
	Logger logger;
	
	public static String loginUsername;
	public static String loginPassword;
	private DatabaseHelper db;
	
	@SuppressLint("ShowToast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// Show the Up button in the action bar.
		setupActionBar();
		final Context context = this;
		/** sets up the nonUI Login Handler */
		logger = new Logger();
		db = new DatabaseHelper(this);
		/** sets up toast */
		CharSequence text = getResources().getString(
				R.string.invalid_login);
		int duration = Toast.LENGTH_SHORT;
		incorrect = Toast.makeText(this, text, duration);
		Button loginButton = (Button) findViewById(R.id.loginSubmit);
		loginButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				loginUsername = ((EditText) findViewById(R.id.usernameEditText)).getText().toString();
				loginPassword = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
				User checkingUser = db.getUserByUsername(loginUsername);
				if(checkingUser != null){
					if(checkingUser.getUsername().compareTo(loginUsername) == 0 
							&& checkingUser.getPassword().compareTo(loginPassword) == 0){
						Intent intent = new Intent(context, UserHomeActivity.class);
						((TextView)findViewById(R.id.loginSubmitText)).setText("");
						startActivity(intent);
					}else{
						((TextView)findViewById(R.id.loginSubmitText)).setText("Invalid Password!");
					}
				}else
					((TextView)findViewById(R.id.loginSubmitText)).setText("User Doesn't Exist!");
			}	
		});
		
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
