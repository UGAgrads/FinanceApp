package com.UGAgrads.freddiefinance;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_user_home);
	// Show the Up button in the action bar.
	setupActionBar();
	lv = (ListView) findViewById(R.id.listView);
	// we register for the contextmneu       
	registerForContextMenu(lv);
	// The data to show
	accountsList = new ArrayList<String>();
	accountsList.add("Embezzled Checking Account");
	accountsList.add("Doomsday Device Savings Account");
	accountsList.add("CREATE NEW ACCOUNT");
	arrayAdapt = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, accountsList);
	lv.setAdapter(arrayAdapt);
	// React to user clicks on item
	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	    public void onItemClick(AdapterView<?> parentAdapter, View view,
		    int position, long id) {

		// We know the View is a TextView so we can cast it
		TextView clickedView = (TextView) view;

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
        // Implements our logic
        Toast.makeText(this, "Item id ["+itemId+"]", Toast.LENGTH_SHORT).show();
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
	menu.add(1, 1, 1, "Details");
	menu.add(1, 2, 2, "Delete");

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
