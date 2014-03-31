package com.UGAgrads.freddiefinance;

/**
 * Presenter class used for computations in the AccountHomeActivity class.
 * 
 * @author UGAgrads
 */
public class AccountHomePresenter {
	
    /** SQLite database reference. */
    private static DatabaseHelper db;
	
	/**
	 * Gets Account by username and account name.
	 * 
	 * @param activity Activity calling the function
	 * @param accountName Name of account to find
	 * @param username Username of the owner of the account
	 * @return The specified account
	 */
    public static Account getAccount(AccountHomeActivity activity, String accountName, String username) {
        db = new DatabaseHelper(activity);
        return db.getAccountByOwnerAndAccountName(username, accountName);
    }

}
