package com.UGAgrads.freddiefinance;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

public abstract class Transaction {
	double quantity; // negative means withdrawl, positive means deposit
	String dateEntered;
	String dateEffective;

	@SuppressLint("SimpleDateFormat")
	Transaction(double amountTransfered, DateFormat dateEffective) {
		Calendar c = Calendar.getInstance();
		System.out.println("Current time => " + c.getTime());
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		dateEntered = df.format(c.getTime());
	}

}
