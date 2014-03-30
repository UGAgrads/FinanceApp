package com.UGAgrads.freddiefinance;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.annotation.SuppressLint;

/**
 * @author
 */
public class RegisterActivity extends Activity {

    /**
     * 
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
     * @param text FILL THIS IN!
     */
    protected void showToast(String text) {
        registerFeedback.setText(text);
        registerFeedback.show();
    }
	
    /**
     * FILL THIS IN!
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
     * FILL THIS IN!
     */ 
    @SuppressLint("ShowToast") 
    private void setupToast() {
        CharSequence text = "";
        int duration = Toast.LENGTH_SHORT;
        registerFeedback = Toast.makeText(this, text, duration);
        registerFeedback.setMargin(.02f, .5f);
    }
	
    /**
     * FILL THIS IN!
     */
    @Override
    protected void onPause() {
        super.onPause();
		// remove toast if activity is not showing
        registerFeedback.cancel();
    }
}
