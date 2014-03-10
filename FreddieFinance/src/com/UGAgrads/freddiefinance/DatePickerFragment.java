package com.UGAgrads.freddiefinance;

import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment  {

	private int year;
	private int month;
	private int day;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), (TransactionActivity) getActivity(),
				year, month, day);
	}
	
	
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    Log.d("mashal", "activity null " + String.valueOf(getActivity()==null));
	    newFragment.show(TransactionActivity.transactionActivity.getFragmentManager(), "datePicker");
	    // added a static reference of TransactionActivty to itself in its class 
	}
	
	public int getMonth() {
		return month + 1;
	}
	public int getYear() {
		return year;
	}
	public int getDay() {
		return day;
	}

     
    
}