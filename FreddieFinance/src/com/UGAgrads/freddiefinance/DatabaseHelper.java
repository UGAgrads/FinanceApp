package com.UGAgrads.freddiefinance;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 3;

	private static final String DATABASE_NAME = "FreddieFinance.db";

	//User table in FF.db
	private static final String TABLE_USERS = "users";
	//Columns for the user table
	private static final String KEY_ID = "id";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_ACCOUNTS = "accounts";
	private static final String KEY_TOTAL_ASSETS = "total_assets";
	private static final String KEY_ACCOUNT_TYPE = "account_type"; //only used in accounts at the moment...
	
	//Account table in FF.db
	private static final String TABLE_ACCOUNTS = "accounts";
	//columns for the account table
	private static final String KEY_BALANCE = "balance";
	private static final String KEY_ACCOUNT_NAME = "account_name";
	private static final String KEY_OWNER = "owner";
	//private static final String KEY_TOTAL_ASSETS = "total_assets";
	//private static final String KEY_ACCOUNT_TYPE = "account_type";
	private static final String KEY_INTERESTRATE = "accounts";
	
	//For Future Purposes
	//private static final String KEY_TRANSACTIONS = "transactions";
	
	
	/*
	   User Table
	   ___________________________________________________________________________________Add this later________
	  |   ID  |  Username  |  Password  |  Email  |  Accounts  |  Total Assets(worth)  |  Primary Account Type  | 
	  |   1   |	 UGAgrad1  |  pass123   |admin@yay| {12-S,15-C}|		 $10,000	   |		Savings		    |

	   
	   Account Table
	   ______________________________________________________________________________________________________
	  |  ID  |  Account Name  |  Owner  |  Account Type  |  Transactions  |  Total Assets  |  Interest Rate  |
	  |  1   |     12-S       |UGAgrad1 |    Savings	 |   {1-W,14-D}   |     $15,000    |      0.1%       |
	  
	
	*/
	
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("leSawce", "dbonCreate");
		db.execSQL(
				"CREATE TABLE " + TABLE_USERS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USERNAME + " TEXT, "
				+ KEY_PASSWORD + " TEXT, " + KEY_EMAIL + " TEXT, " +  
				KEY_ACCOUNTS + " TEXT, " + KEY_TOTAL_ASSETS + " TEXT" + ")"
		);
		
		db.execSQL(
				"CREATE TABLE " + TABLE_ACCOUNTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ACCOUNT_NAME + " TEXT, "
				+ KEY_OWNER + " TEXT, " + KEY_ACCOUNT_TYPE + " TEXT, " 
				+ KEY_BALANCE + " TEXT, " + KEY_INTERESTRATE + " TEXT" + ")"
		);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//delets old table
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
		
		//implements new one
		onCreate(db);
	}
	
	/**
	 * 
	 * @param newUser
	 * 
	 * Used when creating account from the Register Activity
	 * 
	 */
	public void addNewUserToDatabase(User newUser){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_USERNAME, newUser.getUsername().toString());
		values.put(KEY_PASSWORD, newUser.getPassword().toString());
		values.put(KEY_EMAIL, newUser.getEmail().toString());
		values.put(KEY_ACCOUNTS, "{}");
		db.insert(TABLE_USERS, null, values);
		db.close();
	}
	
	/**
	 * Checks if user exists by checking for username
	 * @param username
	 * @return True if user exists, False if not
	 */
	public boolean doesUserAlreadyExist(String username){
		return !(getUserByUsername(username) == null);
	}
	
	public User getUserByUsername(String username){
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_USERNAME,
				KEY_EMAIL, KEY_PASSWORD, KEY_ID , KEY_ACCOUNTS}, KEY_USERNAME + "=?", 
				new String[] {username}, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
			try{
				if(cursor.getString(0).toString().compareTo(username) == 0){
					db.close();
					return new User(cursor.getString(0).toString(), cursor.getString(1).toString(), cursor.getString(2).toString());	
				}
			}catch(CursorIndexOutOfBoundsException e){
				db.close();
				return null;
			}
			
		}
		db.close();
		return null;
	}
	
	
	/**
	 * Used to add new Account to the Account Table of the database
	 * @param newAccount
	 */
	public void addNewAccountToDatabase(Account newAccount){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ACCOUNT_NAME, newAccount.getAccountName());
		values.put(KEY_OWNER, newAccount.getAccountOwner());
		values.put(KEY_ACCOUNT_TYPE, newAccount.getAccountType());
		values.put(KEY_BALANCE, newAccount.getBalance());
		values.put(KEY_INTERESTRATE, newAccount.getInterestRate());
		db.insert(TABLE_ACCOUNTS, null, values);
		db.close();
	}
	
	public ArrayList<Account> getAccountsByOwner(String username){
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Account> accounts = new ArrayList<Account>();
		Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] 
				{KEY_OWNER, KEY_ACCOUNT_NAME, KEY_ACCOUNT_TYPE, KEY_BALANCE, KEY_INTERESTRATE},
				KEY_OWNER + "=?", new String[] {username}, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
			try{
				accounts.add(new Account(cursor.getString(0).toString(), cursor.getString(1).toString(), 
						cursor.getString(2).toString(), cursor.getString(3).toString(), 
						cursor.getString(4).toString()
						));
				while(cursor.moveToNext()){
					accounts.add(new Account(cursor.getString(0).toString(), cursor.getString(1).toString(), 
							cursor.getString(2).toString(), cursor.getString(3).toString(), 
							cursor.getString(4).toString()
							));
				}
				db.close();
			}catch(CursorIndexOutOfBoundsException e){
				db.close();
				return accounts;
			}
		}
				
		return accounts;
		
	}
	
	
	/**
	 * Tries to find the account in the Account Table by searching by account name
	 * @param accountName
	 * @return Account if the account is found, null if it is not found
	 */
	public Account getAccountByAccountName(String accountName){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] 
				{KEY_OWNER, KEY_ACCOUNT_NAME, KEY_ACCOUNT_TYPE, KEY_BALANCE, KEY_INTERESTRATE},
				KEY_ACCOUNT_NAME + "=?", new String[] {accountName}, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
			try{
				if(cursor.getString(0).toString().compareTo(accountName) == 0){
					db.close();
					return new Account(cursor.getString(0).toString(), cursor.getString(1).toString(), 
							cursor.getString(2).toString(), cursor.getString(3).toString(), 
							cursor.getString(4).toString()
							);
				}
			}catch(CursorIndexOutOfBoundsException e){
				db.close();
				return null;
			}
		}
		db.close();
		return null;
	}
	
	/**
	 * Checks if there is an account by searching for account by account name
	 * @param accountName
	 * @return True if Account exists, False if not
	 */
	public boolean doesAccountExist(String accountName){
		return (getAccountByAccountName(accountName) != null);
	}
	
	public boolean doesAccountNameAlreadyExistForOwner(String accountName, String owner){
	    SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] 
				{KEY_OWNER, KEY_ACCOUNT_NAME, KEY_ACCOUNT_TYPE, KEY_BALANCE, KEY_INTERESTRATE},
				KEY_OWNER + "=?", new String[] {owner}, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
			try{
			    if(cursor.getString(1).toString().compareTo(accountName) == 0){
				db.close();
				return true;
			    }
			    while(cursor.moveToNext()){
				if(cursor.getString(1).toString().compareTo(accountName) == 0){
					db.close();
					return true;
				}
			    }
			}catch(CursorIndexOutOfBoundsException e){
				db.close();
				return false;
			}
		}
		db.close();
	    
	    return false;
	
	}
	
	
	
}