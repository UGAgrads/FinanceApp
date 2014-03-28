package com.UGAgrads.freddiefinance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReportDateActivity extends Activity implements DatePickerFragmentK.TheListener {

	private Button mStartDate;
	private Button mEndDate;
	private Button mCreateReport;
	private TextView mStartDateText;
	private TextView mEndDateText;
	private int startY, startM, startD, endY, endM, endD;
	private int i = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_date);

		mStartDateText = (TextView) findViewById(R.id.startingDateText);
		mEndDateText = (TextView) findViewById(R.id.endingDateText);
		
		mStartDate = (Button) findViewById(R.id.startDate);
		mStartDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment picker = new DatePickerFragmentK();
				picker.show(getFragmentManager(), "datePicker");
				i = 1;
			}
		});
		
		mEndDate = (Button) findViewById(R.id.endDate);
		mEndDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment picker = new DatePickerFragmentK();
				picker.show(getFragmentManager(), "datePicker");
				i = 2;
			}
		});
		
		mCreateReport = (Button) findViewById(R.id.createReport);
		mCreateReport.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String startDate = startM + "/" + startD + "/" + startY;
				String endDate = endM + "/" + endD + "/" + endY;
				Date start = Calendar.getInstance().getTime();
				Date end = Calendar.getInstance().getTime();
				SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
				try {
					start = df.parse(startDate);
					end = df.parse(endDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (start.compareTo(end) > 0) {
					Toast.makeText(ReportDateActivity.this, "Please check your dates", 
							Toast.LENGTH_SHORT).show();
				} else {
				
					Intent intent = new Intent(ReportDateActivity.this, HistoryActivity.class);
					intent.putExtra("start", startDate);
					intent.putExtra("end", endDate);
					startActivity(intent);
				}
			}
		});
	}

	public void returnDate(String date) {
		if (i == 1 ) {
			mStartDateText.setText(date);
			String[] tokens = date.split("/");
			startM = Integer.parseInt(tokens[0]);
			startD = Integer.parseInt(tokens[1]);
			startY = Integer.parseInt(tokens[2]);
		} else {
			mEndDateText.setText(date);
			String[] tokens = date.split("/");
			endM = Integer.parseInt(tokens[0]);
			endD = Integer.parseInt(tokens[1]);
			endY = Integer.parseInt(tokens[2]);
		}
	}
}
