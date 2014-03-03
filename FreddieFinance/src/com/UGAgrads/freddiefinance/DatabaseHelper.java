package com.UGAgrads.freddiefinance;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 4;

	private static final String DATABASE_NAME = "FreddieFinance.db";

	//User table in FF.db
	private static final String TABLE_USERS = "users";
	//Columns for the user table
	private static final String KEY_ID = "id";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_TOTAL_ASSETS = "total_assets";
	
	//Account table in FF.db
	private static final String TABLE_ACCOUNTS = "accounts";
	//columns for the account table
	private static final String KEY_BALANCE = "balance";
	private static final String KEY_ACCOUNT_NAME = "account_name";
	private static final String KEY_OWNER = "owner";
	private static final String KEY_INTEREST_RATE = "interest_rate";
	private static final String KEY_ACCOUNT_TYPE = "account_type";
	
	//Transaction Table in FF.db
	private static final String TABLE_TRANSACTIONS = "transactions";
	//Columns in the transaction table
		//ID
		//username
		//Account name
	private static final String KEY_TRANSACTION_TYPE = "transaction_type";
	private static final String KEY_TRANSACTION_AMMOUNT = "transaction_ammount";
	private static final String KEY_DATE_ENTERED = "date_entered";
	private static final String KEY_DATE_EFFECTIVE = "date_effective";
	private static final String KEY_DESCRIPTION = "transaction_description";
	private static final String KEY_SPEND_SOURCE = "spend_source";
	
	
	//For Future Purposes
	//private static final String KEY_TRANSACTIONS = "transactions";
	
	
	/*
	   User Table
	   ___________________________________________________________________
	  |   ID  |  Username  |  Password  |  Email  |  Total Assets(worth)  |
	  |   1   |	 UGAgrad1  |  pass123   |admin@yay|		   $10,000	      |

	   
	   Account Table
	   ________________________________________________________________________________
	  |  ID  |  Account Name  |  Owner  |  Account Type  |  Balance  |  Interest Rate  |
	  |  1   |     12-S       |UGAgrad1 |    Savings	 |  $15,000  |      0.1%       |
	  
	  
	   Transaction Table
	   ________________________________________________________________________________________________________________________
	  |  ID  |  User  |  Acount Name  |  Type  |  Amount  |  Date Entered  |  Date Effective  |  Description  |  SPEND/SOURCE  |
 	   	
 	  
	*/
	
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//These are to create the User and Account Tables for the database
		//They are only used when we update the database or run the app for the first time on a device
		db.execSQL(
				"CREATE TABLE " + TABLE_USERS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USERNAME + " TEXT, "
				+ KEY_PASSWORD + " TEXT, " + KEY_EMAIL + " TEXT, " + KEY_TOTAL_ASSETS + " TEXT" + ")"
		);
		
		db.execSQL(
				"CREATE TABLE " + TABLE_ACCOUNTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ACCOUNT_NAME + " TEXT, "
				+ KEY_OWNER + " TEXT, " + KEY_ACCOUNT_TYPE + " TEXT, " 
				+ KEY_BALANCE + " TEXT, " + KEY_INTEREST_RATE + " TEXT" + ")"
		);
		
		db.execSQL("CREATE TABLE " + TABLE_TRANSACTIONS + "(" 
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USERNAME + " TEXT, " 
				+ KEY_ACCOUNT_NAME + " TEXT, " + KEY_TRANSACTION_TYPE + " TEXT, " + KEY_TRANSACTION_AMMOUNT + " TEXT, "
				+ KEY_DATE_ENTERED + " TEXT, " + KEY_DATE_EFFECTIVE + " TEXT, " + KEY_DESCRIPTION + " TEXT, "
				+ KEY_SPEND_SOURCE + " TEXT" + ")"
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
	 * Used when creating account from the Register Activity
	 * @param newUser User to be added to the table
	 * 
	 */
	public void addNewUserToDatabase(User newUser){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_USERNAME, newUser.getUsername().toString());
		values.put(KEY_PASSWORD, newUser.getPassword().toString());
		values.put(KEY_EMAIL, newUser.getEmail().toString());
		db.insert(TABLE_USERS, null, values);
		db.close();
	}
	
	/**
	 * Checks if user exists by calling getUserByUsername()
	 * @param username String username we are checking to see exists
	 * @return True if user exists, False if not
	 */
	public boolean doesUserAlreadyExist(String username){
		return !(getUserByUsername(username) == null);
	}
	
	/**
	 * Finds and returns a user given the parameter username
	 * @param username Name of the user were looking for
	 * @return User if found, else Null
	 */
	public User getUserByUsername(String username){
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_USERNAME,
				KEY_EMAIL, KEY_PASSWORD, KEY_ID}, KEY_USERNAME + "=?", 
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
	 * Updates the user info.  Takes in an existing user and updates the information by searching for the name(doesnt change)
	 * @param updatingUser updated user info
	 * @return True if account existed and was updated, else False
	 */
	public boolean updateUserInfo(User updatingUser){
		
		return false;
	}
	
	
	/**
	 * Used to add new Account to the Account Table of the database
	 * @param newAccount Account to be added to the table
	 */
	public void addNewAccountToDatabase(Account newAccount){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ACCOUNT_NAME, newAccount.getAccountName());
		values.put(KEY_OWNER, newAccount.getAccountOwner());
		values.put(KEY_ACCOUNT_TYPE, newAccount.getAccountType());
		values.put(KEY_BALANCE, newAccount.getBalance());
		values.put(KEY_INTEREST_RATE, newAccount.getInterestRate());
		db.insert(TABLE_ACCOUNTS, null, values);
		db.close();
	}
	
	
	/**
	 * Gets all accounts a single owner has
	 * @param username Used when searching through the table
	 * @return ArrayList of accounts, Null if none exist
	 */
	public ArrayList<Account> getAccountsByOwner(String username){
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Account> accounts = new ArrayList<Account>();
		Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] 
				{KEY_OWNER, KEY_ACCOUNT_NAME, KEY_ACCOUNT_TYPE, KEY_BALANCE, KEY_INTEREST_RATE},
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
	 * Gets account by owner and account name
	 * @param ownerUsername Account owner
	 * @param accountName Name of account
	 * @return Account if exists, else Null
	 */
	public Account getAccountByOwnerAndAccountName(String ownerUsername, String accountName){
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Account> accounts = new ArrayList<Account>();
		Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] 
				{KEY_OWNER, KEY_ACCOUNT_NAME, KEY_ACCOUNT_TYPE, KEY_BALANCE, KEY_INTEREST_RATE},
				KEY_OWNER + "=?", new String[] {ownerUsername}, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
			try{
				if(cursor.getString(1).toString().compareTo(accountName) == 0)
					return new Account(cursor.getString(0).toString(), cursor.getString(1).toString(), 
							cursor.getString(2).toString(), cursor.getString(3).toString(), 
							cursor.getString(4).toString()
							);
				while(cursor.moveToNext()){
					if(cursor.getString(1).toString().compareTo(accountName) == 0)
						return new Account(cursor.getString(0).toString(), cursor.getString(1).toString(), 
								cursor.getString(2).toString(), cursor.getString(3).toString(), 
								cursor.getString(4).toString()
								);
				}
				db.close();
			}catch(CursorIndexOutOfBoundsException e){
				db.close();
				return null;
			}
		}
				
		return null;
		
	}
	
	
	/**
	 * Tries to find the account in the Account Table by searching by account name
	 * @param accountName
	 * @return Account if the account is found, null if it is not found
	 */
	public Account getAccountByAccountName(String accountName){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] 
				{KEY_OWNER, KEY_ACCOUNT_NAME, KEY_ACCOUNT_TYPE, KEY_BALANCE, KEY_INTEREST_RATE},
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
	
	/**
	 * Checks if a specific user already has an account with a specific name
	 * @param accountName Name of the account were checking to see already exists
	 * @param owner User who we are checking accounts
	 * @return True if account already exists by specified name, False if else
	 */
	public boolean doesAccountNameAlreadyExistForOwner(String accountName, String owner){
	    SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] 
				{KEY_OWNER, KEY_ACCOUNT_NAME, KEY_ACCOUNT_TYPE, KEY_BALANCE, KEY_INTEREST_RATE},
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
	
	
	/**
	 * Updates account info by searching for name then inputing new data
	 * @param updatingAccount Account were searching for and looking to update
	 * @return True if account found and updated, else False
	 */
	public boolean updateAccountInfo(Account updatingAccount){
		
		return false;
	}
	
	public void addNewTransactionToDatabase(Transaction newTransaction){
		
		
	}
	
	
}