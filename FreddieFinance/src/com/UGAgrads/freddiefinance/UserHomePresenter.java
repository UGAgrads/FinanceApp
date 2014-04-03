package com.UGAgrads.freddiefinance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Presenter class for the UserActivity
 * 
 * @author UGA Grads
 */
public class UserHomePresenter {
    /**
     * The list of accounts
     */
    private static ArrayList<String> accountsList;
    /**
     * A reference to the SQLite database
     */
    private static DatabaseHelper db;

    /**
     * Gets the list of user accounts
     * 
     * @param activity Reference to the UserHomeActivity
     * @return The list of the user's accounts
     */
    public static ArrayList<String> getAccounts(UserHomeActivity activity) {
        db = new DatabaseHelper(activity);
        accountsList = new ArrayList<String>();
        List<Account> accounts = db.getAccountsByOwner(LoginPresenter.loginUsername);
        for (Account account : accounts) {
            accountsList.add(account.getAccountName());
        }

        Collections.sort(accountsList);

		/** create new account always at end */
        accountsList.add("CREATE NEW ACCOUNT");
        return accountsList;
    }
}
