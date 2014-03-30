package com.UGAgrads.freddiefinance;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

public class DatePickerFragment extends DialogFragment 
	implements DatePickerDialog.OnDateSetListener {
	
	TheListener listener;
	private int year, day, month;
	private boolean dateChanged;
	
	public interface TheListener {
		public void returnDate(String date);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		//Uses the current date for default
		Log.d("mashal", "date changed? " + String.valueOf(dateChanged));
		if (!dateChanged) {
			final Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
		}
		listener = (TheListener) getActivity();
		
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void updateDate (int year, int month, int dayOfMonth) {
		this.year = year;
		this.month = month;
		this.day = dayOfMonth;
		dateChanged = true;
		//ugggggggggggggghhhhhhh not working...the datepicker refuses to update
		//every time it is clicked. i think it's bc a new fragment is created
		//each time it is clicked...so everything is reset...so somehow need
		//to put the changed dates into the onCreateDialog...onResume also will
		//not change anything
	}

	public void onDateSet(DatePicker view, int y, int m, int d) {
		updateDate(y, m, d);
		Log.d("mashal", String.valueOf(m) +  String.valueOf(d) +  String.valueOf(y));
		Log.d("mashal", "date changed " + String.valueOf(dateChanged));
		if (listener != null) {
			m++;
			listener.returnDate(m + "/" + d + "/" + y);
		}
	}

}
