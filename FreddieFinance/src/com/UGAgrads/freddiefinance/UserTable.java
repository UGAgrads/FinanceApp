package com.UGAgrads.freddiefinance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class UserTable {

	//User table in FF.db
		private static final String TABLE_USERS = "users";
		//Columns for the user table
		private static final String KEY_ID = "id";
		private static final String KEY_USERNAME = "username";
		private static final String KEY_PASSWORD = "password";
		private static final String KEY_EMAIL = "email";
		private static final String KEY_TOTAL_ASSETS = "total_assets";
		
		
		private static SQLiteDatabase db;
		
	/**
	 * Used when creating account from the Register Activity
	 * @param newUser User to be added to the table
	 * 
	 */
	public void addNewUserToDatabase(SQLiteDatabase database, User newUser){
		db = database;
		ContentValues values = new ContentValues();
		values.put(KEY_USERNAME, newUser.getUsername().toString());
		values.put(KEY_PASSWORD, newUser.getPassword().toString());
		values.put(KEY_EMAIL, newUser.getEmail().toString());
		db.insert(TABLE_USERS, null, values);
		db.close();
	}
	
	/**
	 * Finds and returns a user given the parameter username
	 * @param username Name of the user were looking for
	 * @return User if found, else Null
	 */
	public User getUserByUsername(SQLiteDatabase database, String username){
		db = database;
		Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_USERNAME,
				KEY_EMAIL, KEY_PASSWORD, KEY_ID}, KEY_USERNAME + "=?", 
				new String[] {username}, null, null, null, null);
		if(cursor != null){
			if(cursor.moveToFirst()){
				db.close();
				return new User(cursor.getString(0).toString(), cursor.getString(1).toString(), cursor.getString(2).toString());	
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
	public boolean updateUserInfo(SQLiteDatabase database, User updatingUser){
		db = database;
		ContentValues values = new ContentValues();
		values.put(KEY_USERNAME, updatingUser.getUsername().toString());
		values.put(KEY_PASSWORD, updatingUser.getPassword().toString());
		values.put(KEY_EMAIL, updatingUser.getEmail().toString());
		return db.update(TABLE_USERS, values, KEY_USERNAME + "=?", new String[]{updatingUser.getUsername()}) > 0;
		
	}
	
	
	/**
	 * Deletes a user from the database
	 * @param user User were looking to delete
	 * @return True if user was deleted, else False
	 */
	public boolean deleteUserFromDatabase(SQLiteDatabase database, User user){
		db = database;
		return db.delete(TABLE_USERS, KEY_USERNAME + "=? AND " + KEY_PASSWORD + "=? AND " + KEY_EMAIL + "=?", 
				new String[]{user.getUsername(), user.getPassword(), user.getEmail()}) > 0;
		
	}
}