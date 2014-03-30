package com.UGAgrads.freddiefinance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author
 */
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainPresenter.registerAdmin(this);
    }


    /** 
     * Called when the user clicks the login button.
     * @param view FILL THIS IN!
     */
    public void toLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

	/** 
	 * Called when the user clicks the register button.
	 *@param view FILL THIS IN!
	 */
    public void toRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
