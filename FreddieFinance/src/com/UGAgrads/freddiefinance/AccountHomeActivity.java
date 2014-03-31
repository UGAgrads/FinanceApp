package com.UGAgrads.freddiefinance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Activity class that contains the data required for the Account Home page. 
 * This page displays the information of specific accounts.
 * It also has the ability send you to the Create Transaction page.
 * 
 * @author UGAgrads
 */
public class AccountHomeActivity extends Activity {
	
	/** Account owners username. */
    private String username;
	/** Name of account. */
    private String accountTitle;
	/** Accout object.  */
    private Account account;
	
	/** TextView containing the Account Name. */
    private TextView accountHomeTitle;
    /** TextView containing the Account Type. */
    private TextView accountHomeType;
    /** TextView containing the Account Balance. */
    private TextView accountHomeBalance;
    /** TextView containing the Account Interest Rate. */
    private TextView accountHomeInterestRate;
	/** Button to redirect to CreateTransactionActivity. */
    private Button createTransactionButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_account_home);
		
    	username = LoginPresenter.loginUsername; 
    	accountTitle = getIntent().getStringExtra("accountTitle");	
    	account = AccountHomePresenter.getAccount(this, accountTitle, username);
    	setupViews();
    	setupButtons();
    }
	
    /**
     * Refreshes the page when resumed.
     */
    protected void onResume() {
    	super.onResume();
    	setContentView(R.layout.activity_account_home);

    	username = LoginPresenter.loginUsername; 
    	accountTitle = getIntent().getStringExtra("accountTitle");	
    	account = AccountHomePresenter.getAccount(this, accountTitle, username);
		
    	setupViews();
		
    	setupButtons();
    }

    /**
     * Sets TextView values.
     */
    private void setupViews() {
    	accountHomeTitle = (TextView) findViewById(R.id.AccountHomeTitle);
    	accountHomeType = (TextView) findViewById(R.id.AccountHomeType);
    	accountHomeBalance = (TextView) findViewById(R.id.AccountHomeBalance);
    	accountHomeInterestRate = (TextView) findViewById(R.id.AccountHomeInterestRate);
		
    	accountHomeTitle.setText(accountTitle);
		
    	accountHomeType.setText("(" + account.getAccountType() + ")");
		
    	accountHomeBalance.setText("Balance: $" + account.getBalance());
		
    	accountHomeInterestRate.setText("Interest Rate: " + account.getInterestRate());
    }
	
    /**
     * Sets button controls.
     */
    private void setupButtons() {	
    	createTransactionButton = (Button) findViewById(R.id.AccountHomeNewTransactionButton);

    	createTransactionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent = new Intent(AccountHomeActivity.this, CreateTransactionActivity.class);
            	intent.putExtra("accountName", accountTitle);
            	intent.putExtra("username", username);
            	startActivity(intent);
            }
    	});
    }
}
