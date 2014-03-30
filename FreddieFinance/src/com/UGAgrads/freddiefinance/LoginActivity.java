package com.UGAgrads.freddiefinance;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

/**
 * @author
 */
public class LoginActivity extends Activity {

    /**
     * 
     */
    EditText password, username;
	
	/**
	 * 
	 */
    public Toast incorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
		
        setupToast();
		
        setupLoginButton();
    }

    /**
     * @param text FILL THIS IN!
     */
    protected void showToast(String text) {
        incorrect.setText(text);
        incorrect.show();
    }
	
    /**
     * 
     */
    @SuppressLint("ShowToast") 
    private void setupToast() {
        CharSequence text = "";
        int duration = Toast.LENGTH_SHORT;
        incorrect = Toast.makeText(this, text, duration);
        incorrect.setMargin(.02f, .5f);
    }
	
	/**
	 * 
	 */
    private void setupLoginButton() {
        Button loginButton = (Button) findViewById(R.id.loginSubmit);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (LoginPresenter.attemptLogin(LoginActivity.this)) { 
                    case 0: // valid password and username
                        Intent intent = new Intent(LoginActivity.this, UserHomeActivity.class);
                        LoginActivity.this.startActivity(intent);
                        break;
                    case 1: // invalid password
                        showToast(getResources().getString(R.string.invalid_login_password));
                        break;
                    case 2: // invalid username
                        showToast(getResources().getString(R.string.invalid_login_username));
                        break;
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
		// remove toast if activity is not showing
        incorrect.cancel();
    }
}
