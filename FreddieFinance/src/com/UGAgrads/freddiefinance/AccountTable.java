package com.UGAgrads.freddiefinance;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AccountTable {
	
	private static final String TABLE_ACCOUNTS = "accounts";
	//columns for the account table
	private static final String KEY_BALANCE = "balance";
	private static final String KEY_ACCOUNT_NAME = "account_name";
	private static final String KEY_OWNER = "owner";
	private static final String KEY_INTEREST_RATE = "interest_rate";
	private static final String KEY_ACCOUNT_TYPE = "account_type";
	
	private static SQLiteDatabase db;

	/**
	 * Used to add new Account to the Account Table of the database
	 * @param newAccount Account to be added to the table
	 */
	public void addNewAccountToDatabase(SQLiteDatabase database, Account newAccount){
		db = database;
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
	public ArrayList<Account> getAccountsByOwner(SQLiteDatabase database, String username){
		db = database;
		ArrayList<Account> accounts = new ArrayList<Account>();
		Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] 
				{KEY_OWNER, KEY_ACCOUNT_NAME, KEY_ACCOUNT_TYPE, KEY_BALANCE, KEY_INTEREST_RATE},
				KEY_OWNER + "=?", new String[] {username}, null, null, null, null);
		if(cursor != null){
			if(cursor.moveToFirst()){
				accounts.add(new Account(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
			}
			while(cursor.moveToNext()){
				accounts.add(new Account(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));

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
	public Account getAccountByOwnerAndAccountName(SQLiteDatabase database, String ownerUsername, String accountName){
		db = database;
		Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] 
				{KEY_OWNER, KEY_ACCOUNT_NAME, KEY_ACCOUNT_TYPE, KEY_BALANCE, KEY_INTEREST_RATE},
				KEY_OWNER + "=? AND " + KEY_ACCOUNT_NAME + "=?" , new String[] {ownerUsername, accountName}, null, null, null, null);
		if(cursor != null){
			if(cursor.moveToFirst()){
				db.close();
				return new Account(cursor.getString(0).toString(), cursor.getString(1).toString(), 
						cursor.getString(2).toString(), cursor.getString(3).toString(), 
						cursor.getString(4).toString()
						);
				
			}
		}
				
		return null;
	}
	
	
	/**
	 * Checks if a specific user already has an account with a specific name
	 * @param accountName Name of the account were checking to see already exists
	 * @param owner User who we are checking accounts
	 * @return True if account already exists by specified name, False if else
	 */
	public boolean doesAccountNameAlreadyExistForOwner(SQLiteDatabase database, String accountName, String owner){
	    db = database;
		Cursor cursor = db.query(TABLE_ACCOUNTS, new String[] 
				{KEY_OWNER, KEY_ACCOUNT_NAME, KEY_ACCOUNT_TYPE, KEY_BALANCE, KEY_INTEREST_RATE},
				KEY_OWNER + "=? AND " + KEY_ACCOUNT_NAME + "=?", new String[] {owner, accountName}, null, null, null, null);
		if(cursor != null){
			if(cursor.moveToFirst()){
				db.close();
				return true;
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
	public boolean updateAccountInfo(SQLiteDatabase database, Account updatingAccount){
		db = database;
		ContentValues values = new ContentValues();
		values.put(KEY_ACCOUNT_NAME, updatingAccount.getAccountName());
		values.put(KEY_OWNER, updatingAccount.getAccountOwner());
		values.put(KEY_ACCOUNT_TYPE, updatingAccount.getAccountType());
		values.put(KEY_BALANCE, updatingAccount.getBalance());
		values.put(KEY_INTEREST_RATE, updatingAccount.getInterestRate());
		//Returns true if more than 0 rows were effected
		return (db.update(TABLE_ACCOUNTS, values, KEY_OWNER + "=? AND " + KEY_ACCOUNT_NAME + "=?", 
				new String[]{updatingAccount.getAccountOwner(), updatingAccount.getAccountName()}) > 0);
		
	}
	
	/**
	 * Deletes an account from the database
	 * @param account Account were looking to delete
	 * @return True if account was deleted, else False
	 */
	public boolean deleteAccountFromDatabase(SQLiteDatabase database, Account account){
		db = database;
		return db.delete(TABLE_ACCOUNTS, KEY_ACCOUNT_NAME + "=? AND " + KEY_OWNER + "=?" , 
				new String[] {account.getAccountName(), account.getAccountOwner()}) > 0;
	}
}
