package com.UGAgrads.freddiefinance;

import java.text.NumberFormat;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateAccountPresenter {
	private static DatabaseHelper db;
	public static String accountTitle; // which must be unique among other accounts
	public static String balance; // will automatically get formatted to dollar amount
	public static String interestRate; // will automatically be 0 or 100
	public static String accountType; // foolproof ;)
	
	
	public static enum FieldError {
	    NO_ERROR, EMPTY_TITLE, PREXISTING_TITLE, EMPTY_BALANCE, EMPTY_INTEREST,
	}

	/**
	 * This class handles login of a user
	 * 
	 * @param activity
	 * @return integer value encoding login success information
	 */
	public static FieldError attemptCreate(CreateAccountActivity activity) {
		db = new DatabaseHelper(activity);
		accountTitle = ((EditText) activity
				.findViewById(R.id.createTitleEditText)).getText().toString();

		// this formats balance to dollar value
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		EditText balanceEditText = (EditText) activity.findViewById(R.id.createBalanceEditText);
		balance = balanceEditText.getText().toString();
		/*try {
		balance = formatter.format(balanceEditText.getText().toString());
		} catch(Exception e) {}; **/
		
		interestRate = ((EditText) activity
				.findViewById(R.id.createInterestEditText)).getText().toString();
		accountType = ((Spinner) activity
				.findViewById(R.id.create_type_spinner)).getSelectedItem().toString();
		FieldError err = checkFields();
		if (err != FieldError.NO_ERROR) {
			return err;
		} else {
			Log.d("leSawce", "adding new account");
			Account account = new Account(LoginPresenter.loginUsername, accountTitle, accountType, balance, interestRate);
			registerNewAccount(new Account(LoginPresenter.loginUsername, accountTitle, accountType, balance, interestRate));
			return err;
		}

	}

	private static void registerNewAccount(Account newAccount) {
		db.addNewAccountToDatabase(newAccount);
	}

	
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
		if(interestRate.equals(""))  {
			
			return FieldError.EMPTY_INTEREST;
		}
		
		return FieldError.NO_ERROR;
	}


}
