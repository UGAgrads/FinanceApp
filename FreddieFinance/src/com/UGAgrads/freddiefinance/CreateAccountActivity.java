package com.UGAgrads.freddiefinance;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateAccountActivity extends Activity {

	public static String balance;
	public static String title; // must be unique
	public static String interestRate;
	public static String type;
	private CreateAccountActivity activity;
	private Toast createAccountFeedback;
	private DatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		/** sets up toast */
		CharSequence text = "";
		int duration = Toast.LENGTH_SHORT;
		createAccountFeedback = Toast.makeText(this, text, duration);
		createAccountFeedback.setMargin(.02f, .5f);
		activity = this;
		db = new DatabaseHelper(this);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		/* sets up spinner **/
		Spinner spinner = (Spinner) findViewById(R.id.create_type_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.account_types_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
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
		createAccountFeedback.setText(text);
		createAccountFeedback.show();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		// remove toast if activity is not showing
		createAccountFeedback.cancel();
	}

}
