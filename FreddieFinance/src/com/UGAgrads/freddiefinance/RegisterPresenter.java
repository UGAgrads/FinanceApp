package com.UGAgrads.freddiefinance;

import android.widget.EditText;

/**
 * @author
 */
public class RegisterPresenter {

    /**
     * 
     */
    private static DatabaseHelper db;
    
    /**
     * 
     */
    public static String registerUsername;
    
    /**
     * 
     */
    public static String registerPassword;
    
    /**
     * 
     */
    public static String registerEmail;
    

    /**
	 * This class handles login of a user.
	 * @param activity FILL THIS IN!
	 * @return integer value encoding login success information
	 */
    public static int attemptRegister(RegisterActivity activity) {
        db = new DatabaseHelper(activity);
        registerUsername = ((EditText) activity
				.findViewById(R.id.usernameEditText)).getText().toString();
        registerEmail = ((EditText) activity.findViewById(R.id.emailEditText))
				.getText().toString();
        registerPassword = ((EditText) activity
                .findViewById(R.id.passwordEditText)).getText().toString();
        if (registerUsername.equals("")) {
            return 1;
        } else if (registerPassword.equals("")) {
            return 2;
        }
        if (registerNewUser(new User(registerUsername, registerEmail,
				registerPassword))) {
            return 0; // valid username
        } else { // username taken
            return 3;
        }
    }

    /**
     * @param newUser FILL THIS IN!
     * @return FILL THIS IN!
     */
    private static boolean registerNewUser(User newUser) {
        if (!db.doesUserAlreadyExist(newUser.getUsername())) {
            db.addNewUserToDatabase(newUser);
            return true;
        }
        return false;
    }
}
