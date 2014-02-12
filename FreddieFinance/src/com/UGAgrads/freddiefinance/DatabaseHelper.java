package com.UGAgrads.freddiefinance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "FreddieFinance.db";

	//User table in FF.db
	private static final String TABLE_USERS = "users";
	//Columns for the user table
	private static final String KEY_ID = "id";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_EMAIL = "email";
	
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE " + TABLE_USERS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME + " TEXT,"
				+ KEY_PASSWORD + " TEXT," + KEY_EMAIL + " TEXT" + ")"
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
