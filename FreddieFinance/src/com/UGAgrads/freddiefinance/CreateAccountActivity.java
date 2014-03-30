package com.UGAgrads.freddiefinance;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * @author
 */
public class CreateAccountActivity extends Activity {

	/** FILL THIS IN! */
    private Toast createAccountFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_create_account);

    	setupToast();

    	setupSpinner();
		
    	setupCreateButton();
    }

    /**
     * @param text FILL THIS IN!
     */
    protected void showToast(String text) {
    	createAccountFeedback.setText(text);
    	createAccountFeedback.show();
    }

    /**
     * FILL THIS IN!
     */
    @SuppressLint("ShowToast")
    private void setupToast() {
    	CharSequence text = "";
    	int duration = Toast.LENGTH_SHORT;
    	createAccountFeedback = Toast.makeText(this, text, duration);
    	createAccountFeedback.setMargin(.02f, .5f);
    }

    /**
     * FILL THIS IN!
     */
    private void setupSpinner() {
    	Spinner spinner = (Spinner) findViewById(R.id.create_type_spinner);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
    	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.account_types_array,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
    	spinner.setAdapter(adapter);
    }

    /**
     * FILL THIS IN!
     */
    private void setupCreateButton() {
    	Button createButton = (Button) findViewById(R.id.createSubmit);
    	createButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	switch (CreateAccountPresenter.attemptCreate(CreateAccountActivity.this)) {
                    case EMPTY_TITLE:
                    	showToast("Must enter a title!");
                    	break;
                    case PREXISTING_TITLE:
                        showToast("You already have an account by that name!");
                        break;
                    case EMPTY_BALANCE:
                    	showToast("Must enter a starting balance!");
                    	break;
                    case EMPTY_INTEREST:
                    	showToast("Must enter an interest rate! (0 is possible)");
                    	break;
                    case NO_ERROR:
                    	showToast("Account successfully created!");
                    	break;
            	}
            }
    	});
    }
	

    @Override
    protected void onPause() {
    	super.onPause();
		// remove toast if activity is not showing
    	createAccountFeedback.cancel();
    }

}
