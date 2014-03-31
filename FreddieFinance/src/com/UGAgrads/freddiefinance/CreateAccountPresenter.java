package com.UGAgrads.freddiefinance;

import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Presenter class used for computations in the CreateAccountActivity class.
 * 
 * @author UGAgrads
 */
public class CreateAccountPresenter {
	
    /** SQLite database reference. */
    private static DatabaseHelper db;
    /** Name of the account that is trying to be created. */
    public static String accountTitle; // must be unique among other accounts of same user
	/** Starting balance of the account that is trying to be created. */
    public static String balance; // will automatically get formatted to floored to two decimal dollar amount
	/** Interest rate of the account that is trying to be created. */
    public static String interestRate; 
	/** Type of account that is trying to be created. */
    public static String accountType; 
	
    /** Contains types of errors we can encounter. */
    public static enum FieldError {
        /** No error encountered => all fields have contents. */
	    NO_ERROR,
	    /** Account name EditText is empty. */
	    EMPTY_TITLE, 
	    /** An account already exists with the specified name. */
	    PREXISTING_TITLE, 
	    /** Balance EditText is empty. */
	    EMPTY_BALANCE, 
	    /** Interest rate EditText is empty. */
	    EMPTY_INTEREST,
    }

	/**
	 * This method handles creation of a user account.
	 * 
	 * @param activity Activity calling the method
	 * @return FieldError representing possible errors
	 */
    public static FieldError attemptCreate(CreateAccountActivity activity) {
        db = new DatabaseHelper(activity);
        accountTitle = ((EditText) activity
				.findViewById(R.id.createTitleEditText)).getText().toString();

		// this formats balance to dollar value
        EditText balanceEditText = (EditText) activity.findViewById(R.id.createBalanceEditText);
        balance = balanceEditText.getText().toString();
		
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
	 * This method formats a string in US dollars.
	 * (rounded down 2 decimal places with a dollar sign in front)
	 * 
	 * @param unformatted string before being formatted
	 * @return String string formatted as US currency
	 */
    public static String formatBalance(String unformatted) {
		
        if (unformatted.equals(".") || unformatted.equals("")) {
            return "";
        }
		
        String formatted = "";
		
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
	 * This method checks for invalid input.
	 * 
	 * @return FieldError encoding the nature of the error
	 */
    private static FieldError checkFields() {
        if (accountTitle.equals("")) 
			return FieldError.EMPTY_TITLE; // must enter an account title!	
        try {
            if (db.doesAccountNameAlreadyExistForOwner(accountTitle, LoginPresenter.loginUsername)) {
                return FieldError.PREXISTING_TITLE;
            }
        } catch (Exception e) { Log.d("preexist", e.getMessage()); };
		
        if (balance.equals("")) {
            return FieldError.EMPTY_BALANCE;
        }
        Log.d("leSawce", "rate" + interestRate);
        if (interestRate.equals("%"))  {
			
            return FieldError.EMPTY_INTEREST;
        }
		
        return FieldError.NO_ERROR;
    }

}
