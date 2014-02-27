package com.UGAgrads.freddiefinance;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UserHomeActivity extends Activity {
	List<String> accountsList;
	ListView lv;
	ArrayAdapter<String> arrayAdapt;
	String newAccount = "CREATE NEW ACCOUNT";
	DatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("leSawce", "inOnCreate");
		setContentView(R.layout.activity_user_home);
		db = new DatabaseHelper(this);
		// Show the Up button in the action bar.
		setupActionBar();
		lv = (ListView) findViewById(R.id.listView);
		// we register for the contextmneu
		registerForContextMenu(lv);
		// The data to show
		accountsList = new ArrayList<String>();
		try {
		List<Account> accounts = db.getAccountsByOwner(LoginPresenter.loginUsername);
		for (Account account : accounts) {
			accountsList.add(account.getAccountName());
		}
		} catch(Exception e) {Log.d("leSawce", e.getMessage());};
		accountsList.add("CREATE NEW ACCOUNT");
		arrayAdapt = new ArrayAdapter<String>(this,
				R.layout.custom_list_item_1, accountsList);
		lv.setAdapter(arrayAdapt);
		// React to user clicks on item
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parentAdapter, View view,
					int position, long id) {

				// We know the View is a TextView so we can cast it
				TextView clickedView = (TextView) view;
				if (clickedView.getText() != null
						&& clickedView.getText().equals("CREATE NEW ACCOUNT")) {
					Intent intent = new Intent(UserHomeActivity.this,
							CreateAccountActivity.class);
					startActivity(intent);
				}
				Toast.makeText(
						UserHomeActivity.this,
						"Item with id [" + id + "] - Position [" + position
								+ "] - Account[" + clickedView.getText() + "]",
						Toast.LENGTH_SHORT).show();

			}
		});
	}
	

	// This method is called when user selects an Item in the Context menu
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (item.getTitle().equals("Create")) {
			Intent intent = new Intent(UserHomeActivity.this,
					CreateAccountActivity.class);
			startActivity(intent);
		}
		Toast.makeText(this, "Item id [" + itemId + "]", Toast.LENGTH_SHORT)
				.show();
		return true;
	}

	// We want to create a context Menu when the user long click on an item
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;
		String accountTitle = arrayAdapt.getItem(aInfo.position);
		menu.setHeaderTitle("Options for " + accountTitle);
		Log.d("leSawce", String.valueOf(newAccount.equals(accountTitle)));
		if (accountTitle.equals(newAccount)) {
			menu.add(1, 1, 1, "Create");

		} else {
			menu.add(1, 1, 1, "Edit");
			menu.add(1, 2, 2, "Delete");
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

}
