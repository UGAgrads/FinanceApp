package com.UGAgrads.freddiefinance;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateTransactionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_transaction);
		
		setUpSpinner();
	}
	
	private void setUpSpinner() {
		Spinner spinner = (Spinner) findViewById(R.id.transaction_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.transaction_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		        Object item = parent.getItemAtPosition(pos);
		        if (item.toString().equalsIgnoreCase("Deposit")) {
					//display the deposit fields ---> new activity?
		        	goToDeposit();
				}
				if (item.toString().equalsIgnoreCase("Withdrawal")) {
					//display the withdraw fields ---> new activity?
					goToWithdrawal();
				}
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});
	}
	
	private void goToDeposit() {
		Intent intent = new Intent(this, TransactionActivity.class);
		intent.putExtra("isDeposit", true);
		startActivity(intent);
	}
	
	private void goToWithdrawal() {
		Intent intent = new Intent(this, TransactionActivity.class);
		intent.putExtra("isDeposit", false);
		startActivity(intent);
		
	}

}