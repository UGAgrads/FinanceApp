package com.UGAgrads.freddiefinance;

import java.text.ParseException;

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
	            "Spending category report" );
		
		startDate = getIntent().getExtras().getString("start");
		endDate = getIntent().getExtras().getString("end");
		((TextView) findViewById(R.id.timeInterval)).setText(
	            startDate + " - " + endDate );
		HistoryPresenter hp = new HistoryPresenter(this);
		((TextView) findViewById(R.id.foodSpending)).setText(
	            String.valueOf(hp.getSpending(this, "Food")) );
		((TextView) findViewById(R.id.rentSpending)).setText(
				 String.valueOf(hp.getSpending(this, "Rent")) );
		((TextView) findViewById(R.id.clothingSpending)).setText(
				 String.valueOf(hp.getSpending(this, "Clothing")) );
		((TextView) findViewById(R.id.entertainmentSpending)).setText(
				 String.valueOf(hp.getSpending(this, "Entertainment")) );
		((TextView) findViewById(R.id.totalSpending)).setText(
					 String.valueOf(hp.getSpending(this, "Total")) );
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	
}
