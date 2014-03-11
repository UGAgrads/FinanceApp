package com.UGAgrads.freddiefinance;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TransactionActivity extends FragmentActivity implements 
DatePickerDialog.OnDateSetListener {

	public static TransactionActivity transactionActivity;
	boolean isDeposit;
	private Context context;
	String userAccountName;
	Account userAccount;
	
	//putExtra with intent --> send a boolean
	//then use that boolean to display either deposit or withdrawal
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		String monthText = String.valueOf(month + 1);
		String dayText = String.valueOf(day);
		String yearText = String.valueOf(year);

		((Button) findViewById(R.id.editDateEffective)).setText(
	            monthText + "/" + dayText + "/" + yearText);

		 Toast.makeText(context, new StringBuilder().append("Date chosen is ").
	        		append(((TextView)(findViewById(R.id.editDateEffective))).getText().toString()),
	        		Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		transactionActivity = this;
		super.onCreate(savedInstanceState);
		context = this;

		DatabaseHelper db = new DatabaseHelper(context);
		setContentView(R.layout.activity_create_transaction);

		Intent intent = getIntent();
		
		isDeposit = intent.getExtras().getBoolean("isDeposit");
		userAccountName = intent.getExtras().getString("account");

		userAccount = db.getAccountByOwnerAndAccountName(LoginPresenter.loginUsername, userAccountName);
		Log.d("mash", String.valueOf(userAccount == null));
		
		if (!isDeposit) {
			displayWithdrawalFields();
		} else displayDepositFields();

	}
	
	private void displayDepositFields() {
		setContentView(R.layout.activity_transdeposit);
		setUpSpinner2();
		setUpButton();
	}
	
	private void displayWithdrawalFields() {
		setContentView(R.layout.activity_transwithdrawal);
		setUpSpinner();
		setUpButton();
	}
	
	private void setUpSpinner() {
		Spinner spinner = (Spinner) findViewById(R.id.expense_category_spinner);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.expense_category_array,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
	}
	
	private void setUpSpinner2() {
		Spinner spinner2 = (Spinner) findViewById(R.id.money_source_spinner);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
				this, R.array.money_source_array,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner2.setAdapter(adapter2);
	}
	
//	private void updateDisplay() {
//		((Button) findViewById(R.id.editDateEffective)).setText(
//            new StringBuilder()
//                    .append(d.getMonth()).append("/")
//                    .append(d.getDay()).append("/")
//                    .append(d.getYear()).append(" "));
//    }
	/** Displays a notification when the date is updated */
//	private void displayToast() {
//        Toast.makeText(context, new StringBuilder().append("Date choosen is ").
//        		append((TextView)(findViewById(R.id.editDateEffective))).toString(),
//        		Toast.LENGTH_SHORT).show();
//             
//    }
	
	private void setUpButton() {
		
		Button dateButton = (Button) findViewById(R.id.editDateEffective);
		dateButton.setOnClickListener(new OnClickListener() {

			DatePickerFragment d;
			@Override
			public void onClick(View arg0) {
				d = new DatePickerFragment();
				d.showDatePickerDialog(arg0);
				
			}
		});
		
		//Adding a withdrawal or deposit
		Button addButton = (Button) findViewById(R.id.depositSubmit);
		if (!isDeposit)
			addButton = (Button) findViewById(R.id.withdrawSubmit);
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (TransactionPresenter.verifyTransaction
						(TransactionActivity.this, userAccount)) {
				case 0:
					String err0 = "Must enter an amount!";
					Toast.makeText(context, err0, Toast.LENGTH_SHORT).show();
					break;
				case 1:
					String err1 = "Must enter a source of money!";
					Toast.makeText(context, err1, Toast.LENGTH_SHORT).show();
					break;
				case 2:
					String err2 = "Must enter a specific description of source!";
					Toast.makeText(context, err2, Toast.LENGTH_SHORT).show();
					break;
				case 3:
					String err3 = "Must enter a reason for withdrawal!";
					Toast.makeText(context, err3, Toast.LENGTH_SHORT).show();
					break;
				case 4:
					String err4 = "Not enough money in your account!";
					Toast.makeText(context, err4, Toast.LENGTH_SHORT).show();
					break;
				case 5:
					String err5 = "Enter a date for this transaction!";
					Toast.makeText(context, err5, Toast.LENGTH_SHORT).show();
					break;
				case 6:
					TransactionPresenter.makeTheTransaction(TransactionActivity.this);
		            Toast.makeText(context, "Transaction successful", Toast.LENGTH_SHORT).show();
					break;

				}
			}
		});
		
	}

}