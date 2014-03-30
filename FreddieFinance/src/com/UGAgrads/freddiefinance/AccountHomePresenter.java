package com.UGAgrads.freddiefinance;

/**
 * @author
 */
public class AccountHomePresenter {
	
    /** FILL THIS IN! */
    private static DatabaseHelper db;
	
	/**
	 * @param activity FILL THIS IN!
	 * @param accountName FILL THIS IN!
	 * @param username FILL THIS IN!
	 * @return FILL THIS IN!
	 */
    public static Account getAccount(AccountHomeActivity activity, String accountName, String username) {
        db = new DatabaseHelper(activity);
        return db.getAccountByOwnerAndAccountName(username, accountName);
    }

}
