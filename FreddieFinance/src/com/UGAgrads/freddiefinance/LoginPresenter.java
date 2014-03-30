package com.UGAgrads.freddiefinance;

import android.widget.EditText;

/**
 * @author
 */
public class LoginPresenter {
	/**
	 * 
	 */
    private static DatabaseHelper db;
    
    /**
     * 
     */
    public static String loginUsername;
	
	/**
	 * 
	 */
    public static String loginPassword;

	/**
	 * This class handles login of a user.
	 * 
	 * @param activity FILL THIS IN!
	 * @return integer value encoding login success information
	 */
    public static int attemptLogin(LoginActivity activity) {
        db = new DatabaseHelper(activity);
        loginUsername = ((EditText) activity.findViewById(R.id.usernameEditText))
				.getText().toString();
        loginPassword = ((EditText) activity.findViewById(R.id.passwordEditText))
				.getText().toString();
        User checkingUser = db.getUserByUsername(loginUsername);
        if (checkingUser != null) {
            if (checkingUser.getUsername().compareTo(loginUsername) == 0
					&& checkingUser.getPassword().compareTo(
							loginPassword) == 0) { // valid password and username
                return 0;
            } else { // invalid password
                return 1;  
            }
        } else { // invalid username
            return 2;
        }
    }
}
