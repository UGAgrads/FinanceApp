package com.UGAgrads.freddiefinance;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

public class DatePickerFragmentK extends DialogFragment 
	implements DatePickerDialog.OnDateSetListener {
	
	TheListener listener;
	
	public interface TheListener {
		public void returnDate(String date);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		//Uses the current date for default
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		listener = (TheListener) getActivity();
		
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	public void onDateSet(DatePicker view, int y, int m, int d) {
		if (listener != null) {
			m++;
			listener.returnDate(m + "/" + d + "/" + y);
		}
	}

}
