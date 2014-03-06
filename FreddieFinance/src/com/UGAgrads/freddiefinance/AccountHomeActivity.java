package com.UGAgrads.freddiefinance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AccountHomeActivity extends Activity {
	//Fields
	private String username;
	private String accountTitle;
	private Account account;
	
	//Views
	private TextView accountHomeTitle, accountHomeType, accountHomeBalance,
		accountHomeInterestRate;
	
	private Button historyButton, createTransactionButton;
	
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
	
	private void setupViews() {
		accountHomeTitle = (TextView) findViewById(R.id.AccountHomeTitle);
		accountHomeType = (TextView) findViewById(R.id.AccountHomeType);
		accountHomeBalance = (TextView) findViewById(R.id.AccountHomeBalance);
		accountHomeInterestRate = (TextView) findViewById(R.id.AccountHomeInterestRate);
		
		accountHomeTitle.setText(accountTitle);
		
		accountHomeType.setText("(" + account.getAccountType() + ")");
		
		accountHomeBalance.setText("Balance: " + account.getBalance());
		
		accountHomeInterestRate.setText("Interest Rate: " + account.getInterestRate());
	}
	
	private void setupButtons() {	
		historyButton = (Button) findViewById(R.id.AccountHomeTransactionHistoryButton);
		createTransactionButton = (Button) findViewById(R.id.AccountHomeNewTransactionButton);
		
		historyButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					//TODO insert code for launching transaction history activity				
				}

		});
		
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
