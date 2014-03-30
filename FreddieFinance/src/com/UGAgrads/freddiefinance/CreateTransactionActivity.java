package com.UGAgrads.freddiefinance;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * @author
 */
public class CreateTransactionActivity extends Activity {
	
    /** FILL THIS IN! */
    String accnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_transaction);
		
        Intent intent = getIntent();
        accnt = intent.getExtras().getString("accountName");
        setUpSpinner();
    }
	
    /**
     * FILL THIS IN!
     */
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
                    goToDeposit();
                }
                if (item.toString().equalsIgnoreCase("Withdrawal")) {
                    goToWithdrawal();
                }
            }
		    
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
	
    /**
     * FILL THIS IN!
     */
    private void goToDeposit() {
        Intent intent = new Intent(this, TransactionActivity.class);
        intent.putExtra("isDeposit", true);
        intent.putExtra("account", accnt);
        startActivity(intent);
    }
	
	/**
	 * FILL THIS IN!
	 */
    private void goToWithdrawal() {
        Intent intent = new Intent(this, TransactionActivity.class);
        intent.putExtra("isDeposit", false);
        intent.putExtra("account", accnt);
        startActivity(intent);
		
	}

}