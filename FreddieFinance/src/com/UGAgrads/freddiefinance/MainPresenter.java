package com.UGAgrads.freddiefinance;

import android.app.Activity;

/**
 * @author
 */
public class MainPresenter {
	
    /**
     * 
     */
    static DatabaseHelper db;

	/**
	 * This method ensures that an admin account always exists by default.
	 * 
	 * @param activity FILL THIS IN!
	 * @return whether admin was created or not
	 */
    public static boolean registerAdmin(Activity activity) {
        db = new DatabaseHelper(activity);
        if (!db.doesUserAlreadyExist("admin")) {
            db.addNewUserToDatabase(new User("admin", "", "pass123"));
            return true;
        }
        return false;
    }
}
