package com.UGAgrads.freddiefinance;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.annotation.SuppressLint;

/**
 * @author UGAGrads
 */
public class RegisterActivity extends Activity {

    /**
     *  text that is displayed based on register success/failure
     */
    private Toast registerFeedback;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupToast();
        setupRegisterButton();
    }

    /**
     * @param text text to be shown by the toast
     */
    protected void showToast(String text) {
        registerFeedback.setText(text);
        registerFeedback.show();
    }
	
    /**
     * sets up UI elements and button listeners
     */
    private void setupRegisterButton() {
        Button registerButton = (Button) findViewById(R.id.registerSubmit);
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(RegisterPresenter.attemptRegister(RegisterActivity.this)) {
                    case 0:
                        showToast("Successfully Registered");
                        break;
                    case 1:
                        showToast("Must enter a Username!");
                        break;
                    case 2:
                        showToast("Must enter a Password!");
                        break;
                    case 3:
                        showToast("Username already taken!");
                        break;
                }
            }
        });
    }
	
    /**
     * sets up toast
     */ 
    @SuppressLint("ShowToast") 
    private void setupToast() {
        CharSequence text = "";
        int duration = Toast.LENGTH_SHORT;
        registerFeedback = Toast.makeText(this, text, duration);
        registerFeedback.setMargin(.02f, .5f);
    }
	
    /**
     * removes the toast on activity left
     */
    @Override
    protected void onPause() {
        super.onPause();
		// remove toast if activity is not showing
        registerFeedback.cancel();
    }
}
