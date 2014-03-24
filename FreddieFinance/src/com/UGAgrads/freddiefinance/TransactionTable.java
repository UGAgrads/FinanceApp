package com.UGAgrads.freddiefinance;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TransactionTable {

	//Transaction Table in FF.db
		private static final String TABLE_TRANSACTIONS = "transactions";
		//Columns in the transaction table
		private static final String KEY_ID = "id";
		private static final String KEY_USERNAME = "username";
		private static final String KEY_ACCOUNT_NAME = "account_name";
		private static final String KEY_TRANSACTION_TYPE = "transaction_type";
		private static final String KEY_TRANSACTION_AMMOUNT = "transaction_ammount";
		private static final String KEY_DATE_ENTERED = "date_entered";
		private static final String KEY_DATE_EFFECTIVE = "date_effective";
		private static final String KEY_DESCRIPTION = "transaction_description";
		private static final String KEY_SPEND_SOURCE = "spend_source";
		
		private static SQLiteDatabase db;
		private static AccountTable accountTable;
		private static UserTable userTable;
		
		TransactionTable(){
			accountTable = new AccountTable();
			userTable = new UserTable();
		}

	/**
	 * Adds a transaction to the Database
	 * @param newTransaction Transaction to be added to the database
	 * 
	 */
	public void addNewTransactionToDatabase(SQLiteDatabase database, Transaction newTransaction){
		db = database;
		ContentValues values = new ContentValues();
		values.put(KEY_USERNAME, newTransaction.getTransactionUsername());
		values.put(KEY_ACCOUNT_NAME, newTransaction.getTransactionAccountName());
		values.put(KEY_TRANSACTION_TYPE, newTransaction.transactionType());
		values.put(KEY_TRANSACTION_AMMOUNT, String.valueOf(newTransaction.getTransactionAmmount()));
		values.put(KEY_DATE_ENTERED, newTransaction.getTransactionDateEntered());
		values.put(KEY_DATE_EFFECTIVE, newTransaction.getTransactionDateEffective());
		values.put(KEY_DESCRIPTION, newTransaction.getTransactionDescription());
		values.put(KEY_SPEND_SOURCE, newTransaction.getSpendSourceInfo());	
		db.insert(TABLE_TRANSACTIONS, null, values);
		db.close();
	}
	
	
	/**
	 * Gets an arraylist of transactions by user and account name
	 * @param owner Owner of the account
	 * @param accountName Name of the account
	 * @return ArrayList of transactions associated with a specific user/account
	 */
	public ArrayList<Transaction> getTransactionsByOwnerAndAccountName(SQLiteDatabase database, String owner, String accountName){
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		db = database;
		Cursor cursor = db.query(TABLE_TRANSACTIONS, new String[] {KEY_USERNAME, KEY_ACCOUNT_NAME, KEY_TRANSACTION_TYPE,
				KEY_TRANSACTION_AMMOUNT, KEY_DATE_ENTERED, KEY_DATE_EFFECTIVE, KEY_DESCRIPTION, KEY_SPEND_SOURCE},
				KEY_USERNAME + "=? AND " + KEY_ACCOUNT_NAME + "=?", new String[] {owner, accountName}, null, null, null, null);
		if(cursor != null){
			if(cursor.moveToFirst()){
				if(cursor.getString(2).compareTo("Deposit") == 0){
					transactionList.add(new Deposit(Double.parseDouble(cursor.getString(3)), cursor.getString(5),
							userTable.getUserByUsername(db, cursor.getString(0)),
							accountTable.getAccountByOwnerAndAccountName(db, cursor.getString(0),cursor.getString(1)),
							cursor.getString(7), cursor.getString(6)));
				}else{
					transactionList.add(new Withdrawal(Double.parseDouble(cursor.getString(3)), cursor.getString(5),
							userTable.getUserByUsername(db, cursor.getString(0)),
							accountTable.getAccountByOwnerAndAccountName(db, cursor.getString(0),cursor.getString(1)),
							cursor.getString(7), cursor.getString(6)));
				}
			}
			while(cursor.moveToNext()){
				if(cursor.getString(2).compareTo("Deposit") == 0){
					transactionList.add(new Deposit(Double.parseDouble(cursor.getString(3)), cursor.getString(5),
							userTable.getUserByUsername(db, cursor.getString(0)),
							accountTable.getAccountByOwnerAndAccountName(db, cursor.getString(0),cursor.getString(1)),
							cursor.getString(7), cursor.getString(6)));
				}else{
					transactionList.add(new Withdrawal(Double.parseDouble(cursor.getString(3)), cursor.getString(5),
							userTable.getUserByUsername(db, cursor.getString(0)),
							accountTable.getAccountByOwnerAndAccountName(db, cursor.getString(0),cursor.getString(1)),
							cursor.getString(7), cursor.getString(6)));
				}
			}
			
		}
		db.close();
		return transactionList;
	}
	
	
	/**
	 * Gets all withdrawals associated with a specified user.
	 * @param user The user we gather Withdrawals for
	 * @return ArrayList of Withdrawals
	 */
	public ArrayList<Withdrawal> getAllAccountWithdrawals(SQLiteDatabase database, String user){
		ArrayList<Withdrawal> withdrawalList = new ArrayList<Withdrawal>();
		db = database;
		Cursor cursor = db.query(TABLE_TRANSACTIONS, new String[] {KEY_USERNAME, KEY_ACCOUNT_NAME, KEY_TRANSACTION_TYPE,
				KEY_TRANSACTION_AMMOUNT, KEY_DATE_ENTERED, KEY_DATE_EFFECTIVE, KEY_DESCRIPTION, KEY_SPEND_SOURCE},
				KEY_USERNAME + "=? AND " + KEY_TRANSACTION_TYPE + "=?", new String[] {user, "Withdrawal"},
				null, null, null, null);
		if(cursor != null){
			if(cursor.moveToFirst()){
				withdrawalList.add(new Withdrawal(Double.parseDouble(cursor.getString(3)), cursor.getString(5),
						userTable.getUserByUsername(db, cursor.getString(0)),
						accountTable.getAccountByOwnerAndAccountName(db, cursor.getString(0),cursor.getString(1)),
						cursor.getString(7), cursor.getString(6)));
				while(cursor.moveToNext()){
					withdrawalList.add(new Withdrawal(Double.parseDouble(cursor.getString(3)), cursor.getString(5),
							userTable.getUserByUsername(db, cursor.getString(0)),
							accountTable.getAccountByOwnerAndAccountName(db, cursor.getString(0),cursor.getString(1)),
							cursor.getString(7), cursor.getString(6)));
				}
			}
		}
		return withdrawalList;
	}
	
	
	/**
	 * Gets all deposits associated with a specified user.
	 * @param user The user we gather Deposits for
	 * @return ArrayList of Deposits
	 */
	public ArrayList<Deposit> getAllAccountDeposits(SQLiteDatabase database, String user){
		ArrayList<Deposit> withdrawalList = new ArrayList<Deposit>();
		db = database;
		Cursor cursor = db.query(TABLE_TRANSACTIONS, new String[] {KEY_USERNAME, KEY_ACCOUNT_NAME, KEY_TRANSACTION_TYPE,
				KEY_TRANSACTION_AMMOUNT, KEY_DATE_ENTERED, KEY_DATE_EFFECTIVE, KEY_DESCRIPTION, KEY_SPEND_SOURCE},
				KEY_USERNAME + "=? AND " + KEY_TRANSACTION_TYPE + "=?", new String[] {user, "Deposit"},
				null, null, null, null);
		if(cursor != null){
			if(cursor.moveToFirst()){
				withdrawalList.add(new Deposit(Double.parseDouble(cursor.getString(3)), cursor.getString(5),
						userTable.getUserByUsername(db, cursor.getString(0)),
						accountTable.getAccountByOwnerAndAccountName(db, cursor.getString(0),cursor.getString(1)),
						cursor.getString(7), cursor.getString(6)));
				while(cursor.moveToNext()){
					withdrawalList.add(new Deposit(Double.parseDouble(cursor.getString(3)), cursor.getString(5),
							userTable.getUserByUsername(db, cursor.getString(0)),
							accountTable.getAccountByOwnerAndAccountName(db, cursor.getString(0),cursor.getString(1)),
							cursor.getString(7), cursor.getString(6)));
				}
			}
		}
		return withdrawalList;
	}
	
	public AccountTable getAccountTable(){
		return accountTable;
	}
	
	public UserTable getUserTable(){
		return userTable;
	}
}
