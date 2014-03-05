package com.UGAgrads.freddiefinance;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AccountHomeActivity extends Activity {
	
	private TextView accountHomeTitle;
	private String accountTitle;
	private Account account;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_home);
		
		accountTitle = getIntent().getStringExtra("accountTitle");
		
		setupViews();
		
	}
	
	private void setupViews() {
		
	}

}
