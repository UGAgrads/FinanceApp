package com.UGAgrads.freddiefinance;

import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateAccountPresenter {
	private static DatabaseHelper db;
	public static String accountTitle; // must be unique among other accounts of same user
	public static String balance; // will automatically get formatted to floored to two decimal dollar amount
	public static String interestRate; 
	public static String accountType; 
	
	
	public static enum FieldError {
	    NO_ERROR, EMPTY_TITLE, PREXISTING_TITLE, EMPTY_BALANCE, EMPTY_INTEREST,
	}

	/**
	 * This method handles creation of a user account
	 * 
	 * @param activity
	 * @return FieldError representing possible errors
	 */
	public static FieldError attemptCreate(CreateAccountActivity activity) {
		db = new DatabaseHelper(activity);
		accountTitle = ((EditText) activity
				.findViewById(R.id.createTitleEditText)).getText().toString();

		// this formats balance to dollar value
		EditText balanceEditText = (EditText) activity.findViewById(R.id.createBalanceEditText);
		balance = formatBalance(balanceEditText.getText().toString());
		
		interestRate = ((EditText) activity
				.findViewById(R.id.createInterestEditText)).getText().toString() + "%";
		accountType = ((Spinner) activity
				.findViewById(R.id.create_type_spinner)).getSelectedItem().toString();
		FieldError err = checkFields();
		if (err != FieldError.NO_ERROR) {
			return err;
		} else {
			Account account = new Account(LoginPresenter.loginUsername, accountTitle, accountType, balance, interestRate);
			db.addNewAccountToDatabase(account);
			return err;
		}

	}
	
	/**
	 * This method formats a string in US dollars
	 * (rounded down 2 decimal places with a dollar sign in front)
	 * 
	 * @param String string before being formatted
	 * @return String string formatted as US currency
	 */
	private static String formatBalance(String unformatted) {
		
		// covers invalid input
		if (unformatted.equals(".") || unformatted.equals("")) {
			return "";
		}
		
		String formatted = "$";
		
		if (!unformatted.contains(".")) {
			return formatted + String.valueOf(Integer.valueOf(unformatted)) + ".00"; // necessary to get rid of leading zeros
		}	
		
		if (unformatted.charAt(unformatted.length() - 1) == '.') { // means decimal at the end
			return formatted + String.valueOf(Integer.valueOf(unformatted.substring(0, unformatted.length() - 1))) + ".00";
		}
		
		String[] splitArray = unformatted.split("[.]"); 
		
		formatted += (splitArray[0].length() == 0) ? "0" :  String.valueOf(Integer.valueOf(splitArray[0]));
		
		formatted += ".";
	
		formatted += (splitArray[1].length() == 1) ? splitArray[1] + "0" :  splitArray[1].substring(0, 2);
		
		return formatted;
		
	}

	/**
	 * This method checks for invalid input
	 * 
	 * @return FieldError encoding the nature of the error
	 */
	private static FieldError checkFields() {
		if (accountTitle.equals("")) 
			return FieldError.EMPTY_TITLE; // must enter an account title!	
		try {
			if (db.doesAccountNameAlreadyExistForOwner(accountTitle, LoginPresenter.loginUsername)){
				return FieldError.PREXISTING_TITLE;
			}
		} catch (Exception e) {Log.d("preexist", e.getMessage());};
		
		if (balance.equals("")) {
			return FieldError.EMPTY_BALANCE;
		}
		Log.d("leSawce", "rate" + interestRate);
		if(interestRate.equals("%"))  {
			
			return FieldError.EMPTY_INTEREST;
		}
		
		return FieldError.NO_ERROR;
	}


}