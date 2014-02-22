package com.UGAgrads.freddiefinance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 2;

	private static final String DATABASE_NAME = "FreddieFinance.db";

	//User table in FF.db
	private static final String TABLE_USERS = "users";
	//Columns for the user table
	private static final String KEY_ID = "id";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_NAME = "name";
	private static final String KEY_ACCOUNTS = "accounts";
	private static final String KEY_TOTAL_ASSETS = "total_assets";
	private static final String KEY_ACCOUNT_TYPE = "account_type";
	
	//Account table in FF.db
	private static final String TABLE_ACCOUNTS = "accounts";
	//columns for the account table
	//private static final String KEY_ID = "id";
	private static final String KEY_ACCOUNT_NAME = "account_name";
	private static final String KEY_OWNER = "owner";
	private static final String KEY_TRANSACTIONS = "transactions";
	//private static final String KEY_TOTAL_ASSETS = "total_assets";
	//private static final String KEY_ACCOUNT_TYPE = "account_type";
	private static final String KEY_INTERESTRATE = "accounts";
	
	
	/*
	   User Table
	   _________________________________________________________________________________________________________
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
		db.execSQL(
				"CREATE TABLE " + TABLE_USERS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USERNAME + " TEXT, "
				+ KEY_PASSWORD + " TEXT, " + KEY_EMAIL + " TEXT, " + KEY_NAME + " TEXT, "+ 
				KEY_ACCOUNTS + " TEXT, " + KEY_TOTAL_ASSETS + " TEXT, " + KEY_ACCOUNT_TYPE + " TEXT" + ")"
		);
		
		db.execSQL(
				"CREATE TABLE " + TABLE_ACCOUNTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ACCOUNT_NAME + " TEXT, "
				+ KEY_OWNER + " TEXT, " + KEY_ACCOUNT_TYPE + " TEXT, " + KEY_TRANSACTIONS + " TEXT, "
				+ KEY_TOTAL_ASSETS + " TEXT, " + KEY_INTERESTRATE + "TEXT" + ")"
		);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//delets old table
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		
		//implements new one
		onCreate(db);
	}
	
	public void addNewUserToDatabase(User newUser){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_USERNAME, newUser.getUsername().toString());
		values.put(KEY_PASSWORD, newUser.getPassword().toString());
		values.put(KEY_EMAIL, newUser.getEmail().toString());
		db.insert(TABLE_USERS, null, values);
		db.close();
	}
	
	public boolean doesUserAlreadyExist(String username){
		//get readable db to search
		return !(getUserByUsername(username) == null);
	}
	
	public User getUserByUsername(String username){
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_USERNAME,
				KEY_EMAIL, KEY_PASSWORD, KEY_ID }, KEY_USERNAME + "=?",
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
	
	
}
