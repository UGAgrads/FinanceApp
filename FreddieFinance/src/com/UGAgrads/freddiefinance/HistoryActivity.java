package com.UGAgrads.freddiefinance;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author
 */
public class HistoryActivity extends Activity {

    /** FILL THIS IN! */
    private String startDate, endDate;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
        setContentView(R.layout.activity_history);
		
        displaySpendingReport();
    }
	
    /**
     * FILL THIS IN!
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
	
    /**
     * @return FILL THIS IN!
     */
    public String getStartDate() {
        return startDate;
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public String getEndDate() {
        return endDate;
    }
	
	
}
