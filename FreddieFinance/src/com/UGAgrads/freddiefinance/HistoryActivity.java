package com.UGAgrads.freddiefinance;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Activity class that contains the data for the Transaction History page.
 * This page displays the History of specified accounts over a specified date range.
 * 
 * @author UGAgrads
 */
public class HistoryActivity extends Activity {

    /** Start date of the date range Transactions are being searched for. */
    private String startDate;
    /** End date of the date range Transactions are being searched for. */
    private String endDate;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
        setContentView(R.layout.activity_history);
		
        displaySpendingReport();
    }
	
    /**
     * Uses HistoryPresenter helper class to gather the Transaction history and display it.
     */
    private void displaySpendingReport() {
        ((TextView) findViewById(R.id.reportType)).setText(
	            "Spending category report" );
		
        startDate = getIntent().getExtras().getString("start");
        endDate = getIntent().getExtras().getString("end");
        ((TextView) findViewById(R.id.timeInterval)).setText(
	            startDate + " - " + endDate );
        HistoryPresenter hp = new HistoryPresenter(this);
        ((TextView) findViewById(R.id.foodSpending)).setText(
	            String.valueOf(hp.getSpending("Food")) );
        ((TextView) findViewById(R.id.rentSpending)).setText(
				 String.valueOf(hp.getSpending("Rent")) );
        ((TextView) findViewById(R.id.clothingSpending)).setText(
				 String.valueOf(hp.getSpending("Clothing")) );
        ((TextView) findViewById(R.id.entertainmentSpending)).setText(
				 String.valueOf(hp.getSpending("Entertainment")) );
        ((TextView) findViewById(R.id.totalSpending)).setText(
					 String.valueOf(hp.getSpending("Total")) );
    }
	
    /**
     * @return Start date of date range entered by user.
     */
    public String getStartDate() {
        return startDate;
    }
	
	/**
	 * @return End date of date range entered by users.
	 */
    public String getEndDate() {
        return endDate;
    }
	
    public void setStartDate(String date) {
    	startDate = date;
    }
    
    public void setEndDate(String date) {
    	endDate = date;
    }
	
}
