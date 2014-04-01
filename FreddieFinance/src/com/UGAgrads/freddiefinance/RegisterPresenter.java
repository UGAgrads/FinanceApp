package com.UGAgrads.freddiefinance;

import android.widget.EditText;

/**
 * @author UGA grads
 */
public class RegisterPresenter {

    /**
     * database helper
     */
    private static DatabaseHelper db;
    
    /**
     * desired username
     */
    public static String registerUsername;
    
    /**
     * desired password
     */
    public static String registerPassword;
    
    /**
     * desired email
     */
    public static String registerEmail;
    

    /**
	 * This class handles login of a user.
	 * @param activity the activity coupled with this presenter
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
     * @param newUser the user that is to be added!
     * @return whether registering the user was successful or not
     */
    private static boolean registerNewUser(User newUser) {
        if (!db.doesUserAlreadyExist(newUser.getUsername())) {
            db.addNewUserToDatabase(newUser);
            return true;
        }
        return false;
    }
}
