package com.UGAgrads.freddiefinance;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

public class RegisterActivity extends Activity {

	public static String registerUsername;
	public static String registerEmail;
	public static String registerPassword;
	private DatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		db = new DatabaseHelper(this);
		// Show the Up button in the action bar.
		setupActionBar();
		Button registerButton = (Button) findViewById(R.id.registerSubmit);
		registerButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				registerUsername = ((EditText) findViewById(R.id.usernameEditText)).getText().toString();
				registerEmail = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
				registerPassword = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
				if(registerNewUser(new User(registerUsername, registerEmail, registerPassword))){
					((TextView)findViewById(R.id.registerSubmitText)).setText("Successfully Registered");
				}else{
					((TextView)findViewById(R.id.registerSubmitText)).setText("Invalid Username!");
				}
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	private boolean registerNewUser(User newUser){
		if(!db.doesUserAlreadyExist(newUser.getUsername())){
			db.addNewUserToDatabase(newUser);
			return true;
		}
		return false;
		
	}



}
