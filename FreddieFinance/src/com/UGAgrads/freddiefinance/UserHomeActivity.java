package com.UGAgrads.freddiefinance;


import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author
 */
public class UserHomeActivity extends Activity {
    
    /**
     *
     */
    ListView listView;
    
    /**
     * 
     */
    ArrayAdapter<String> arrayAdapt;
    
    /**
     * 
     */
    String accountTitle = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_user_home);
    	setupList();
    	setupTransHistoryButton();
    }

    /**
     * 
     */
    private void setupList() {
        listView = (ListView) findViewById(R.id.listView);
        registerForContextMenu(listView);
        List<String> SortedAccountNames = UserHomePresenter.getAccounts(this);
        arrayAdapt = new ArrayAdapter<String>(this,
        		R.layout.custom_list_item_1, SortedAccountNames);
        listView.setAdapter(arrayAdapt);
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parentAdapter, View view,
					int position, long id) {

				// We know the View is a TextView so we can cast it
		        TextView clickedView = (TextView) view;
		        if (clickedView.getText() != null
						&& clickedView.getText().equals("CREATE NEW ACCOUNT")) {
		            Intent intent = new Intent(UserHomeActivity.this,
							CreateAccountActivity.class);
		            startActivity(intent);
		        } else if (clickedView.getText() != null) { // must be legitimate account
		            accountTitle = (String)clickedView.getText();
		            Intent intent = new Intent(UserHomeActivity.this,
							AccountHomeActivity.class);
		            intent.putExtra("accountTitle", accountTitle);
		            startActivity(intent);
		        }
		    }
		});
	}
	

	// This method is called when user selects an Item in the Context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	
        String contextItem = (String) item.getTitle();
    	
        if (contextItem.equals("Create")) {
            Intent intent = new Intent(UserHomeActivity.this,
    				CreateAccountActivity.class);
            startActivity(intent);
    	
        } else if (contextItem.equals("Open")) {
            Intent intent = new Intent(UserHomeActivity.this,
    				AccountHomeActivity.class);
            intent.putExtra("accountTitle", accountTitle);
            startActivity(intent);
        }
        return true;
    }

	// We want to create a context Menu when the user long click on an item
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;
        String title = arrayAdapt.getItem(aInfo.position);
        menu.setHeaderTitle("Options for " + accountTitle);
        if (accountTitle.equals("CREATE NEW ACCOUNT")) {
            menu.add(1, 1, 1, "Create");
        } else {
            accountTitle = title;
            menu.add(1, 1, 1, "Edit");
            menu.add(1, 2, 2, "Delete");
            menu.add(3, 3, 3, "Open");
        }
    }

    /**
     * 
     */
    private void setupTransHistoryButton() {
        Button hist = (Button) findViewById(R.id.transactionHistoryButton);
        hist.setOnClickListener(new OnClickListener() {
    
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this,
    					ReportDateActivity.class);
                startActivity(intent);
            }
    	});
    }
}
