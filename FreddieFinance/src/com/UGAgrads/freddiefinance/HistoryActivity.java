package com.UGAgrads.freddiefinance;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HistoryActivity extends Activity {

	private String startDate, endDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_history);
		
		displaySpendingReport();
	}
	
	private void displaySpendingReport() {
		((TextView) findViewById(R.id.reportType)).setText(
	            "Spending category report for " + "username" );
		startDate = getIntent().getExtras().getString("start");
		endDate = getIntent().getExtras().getString("end");
		((TextView) findViewById(R.id.timeInterval)).setText(
	            startDate + " - " + endDate );
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
}
