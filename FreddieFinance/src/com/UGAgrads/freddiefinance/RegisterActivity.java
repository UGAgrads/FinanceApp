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
import android.widget.Toast;
import android.annotation.TargetApi;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

public class RegisterActivity extends Activity {

	public static String registerUsername;
	public static String registerEmail;
	public static String registerPassword;
	private RegisterActivity activity;
	private Toast registerFeedback;
	private DatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		/** sets up toast */
		CharSequence text = "";
		int duration = Toast.LENGTH_SHORT;
		registerFeedback = Toast.makeText(this, text, duration);
		registerFeedback.setMargin(.02f, .5f);
		activity = this;
		db = new DatabaseHelper(this);
		// Show the Up button in the action bar.
		setupActionBar();
		Button registerButton = (Button) findViewById(R.id.registerSubmit);
		registerButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				switch(RegisterPresenter.attemptRegister(activity)) {
				case 0:
					showToast("Successfully Registered");
					break;
				case 1:
					showToast("Must enter a Username!");
					break;
				case 2:
					showToast("Must enter a Password!");
					break;
				case 3:
					showToast("Username already taken!");
					break;
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

	
	protected void showToast(String text) {
		registerFeedback.setText(text);
		registerFeedback.show();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		// remove toast if activity is not showing
		registerFeedback.cancel();
	}

}
