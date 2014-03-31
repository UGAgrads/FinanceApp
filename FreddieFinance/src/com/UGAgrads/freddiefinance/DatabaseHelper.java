package com.UGAgrads.freddiefinance;

import java.util.ArrayList;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper class for the SQLite database.
 * 
 * @author UGAgrads
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /** Database version. */
    private static final int DATABASE_VERSION = 9;
	
	/** Name of database file. */
    private static final String DATABASE_NAME = "FreddieFinance.db";

	/** User table name. */
    private static final String TABLE_USERS = "users";
	/** Key column. */
    private static final String KEY_ID = "id";
	/** Username column. */
    private static final String KEY_USERNAME = "username";
	/** Password Column. */
    private static final String KEY_PASSWORD = "password";
	/** Email Column. */
    private static final String KEY_EMAIL = "email";
	/** Total Assets column. */
    private static final String KEY_TOTAL_ASSETS = "total_assets";
	
	/** Account table name. */
    private static final String TABLE_ACCOUNTS = "accounts";
    //Columns in the Account table already defined
    	//ID
	/** Balance column. */
    private static final String KEY_BALANCE = "balance";
	/** Account name column. */
    private static final String KEY_ACCOUNT_NAME = "account_name";
	/** Account owner column. */
    private static final String KEY_OWNER = "owner";
	/** Account interest rate column. */
    private static final String KEY_INTEREST_RATE = "interest_rate";
	/** Account type column. */
    private static final String KEY_ACCOUNT_TYPE = "account_type";
	
	/** Transaction table name. */
    private static final String TABLE_TRANSACTIONS = "transactions";
	//Columns in the transaction table already defined
		//ID
		//username
		//Account name
	/** Transaction type column. */
    private static final String KEY_TRANSACTION_TYPE = "transaction_type";
	/** Transaction ammount column. */
    private static final String KEY_TRANSACTION_AMMOUNT = "transaction_ammount";
	/** Transaction date entered column. */
    private static final String KEY_DATE_ENTERED = "date_entered";
	/** Transaction date effective column. */
    private static final String KEY_DATE_EFFECTIVE = "date_effective";
	/** Transaction description column. */
    private static final String KEY_DESCRIPTION = "transaction_description";
	/** Transaction Spend/Source column. */
    private static final String KEY_SPEND_SOURCE = "spend_source";
    
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
	
	/** User table helper reference.  Contains methods only pertaining to the User table in the database. */
    private static UserTable userTable;
	/** Account table helper reference.  Contains methods only pertaining to the Account table in the database. */
    private static AccountTable accountTable;
	/** Transaction table helper reference.  Contains methods only pertaining to the Transaction table in the database. */
    private static TransactionTable transactionTable;
	
	/**
	 * Constructor that initializes the database and database helper classes.
	 * 
	 * @param context Context creating helper
	 */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        transactionTable = new TransactionTable();
        userTable = transactionTable.getUserTable();
        accountTable = transactionTable.getAccountTable();
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
		//deletes old table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
		//implements new one
        onCreate(db);
    }
	
	/**
	 * Used when creating account from the Register Activity.
	 * 
	 * @param newUser User to be added to the table
	 */
    public void addNewUserToDatabase(User newUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        userTable.addNewUserToDatabase(db, newUser);
        db.close();
    }
	
	/**
	 * Checks if user exists by calling getUserByUsername().
	 * 
	 * @param username String username we are checking to see exists
	 * @return True if user exists, False if not
	 */
    public boolean doesUserAlreadyExist(String username) {
        return !(getUserByUsername(username) == null);
    }
	
	/**
	 * Finds and returns a user given the parameter username.
	 * 
	 * @param username Name of the user were looking for
	 * @return User if found, else Null
	 */
    public User getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        User newUser = userTable.getUserByUsername(db, username);
        db.close();
        return newUser;
    }
	
	/**
	 * Updates the user info.  Takes in an existing user and updates the information by searching for the name(doesnt change).
	 * 
	 * @param updatingUser updated user info
	 * @return True if account existed and was updated, else False
	 */
    public boolean updateUserInfo(User updatingUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean updateSuccessful =  userTable.updateUserInfo(db, updatingUser);
        db.close();
        return updateSuccessful;
    }
	
	/**
	 * Deletes a user from the database.
	 * 
	 * @param user User were looking to delete
	 * @return True if account was deleted, else False
	 */
    public boolean deleteUserFromDatabase(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean deleteSuccessful = userTable.deleteUserFromDatabase(db, user);	
        db.close();
        return deleteSuccessful;
    }
	
	
	/**
	 * Used to add new Account to the Account Table of the database.
	 * 
	 * @param newAccount Account to be added to the table
	 */
    public void addNewAccountToDatabase(Account newAccount) {
        SQLiteDatabase db = this.getWritableDatabase();
        accountTable.addNewAccountToDatabase(db, newAccount);
        db.close();
    }
	
	
	/**
	 * Gets all accounts a single owner has.
	 * 
	 * @param username Used when searching through the table
	 * @return ArrayList of accounts, Null if none exist
	 */
    public ArrayList<Account> getAccountsByOwner(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Account> ownerAccounts = accountTable.getAccountsByOwner(db, username);
        db.close();
        return ownerAccounts;		
    }
	
	/**
	 * Gets account by owner and account name.
	 * 
	 * @param ownerUsername Account owner
	 * @param accountName Name of account
	 * @return Account if exists, else Null
	 */
    public Account getAccountByOwnerAndAccountName(String ownerUsername, String accountName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Account newAccount = accountTable.getAccountByOwnerAndAccountName(db, ownerUsername, accountName);
        db.close();
        return newAccount;
    }
	
	
	/**
	 * Checks if a specific user already has an account with a specific name.
	 * 
	 * @param accountName Name of the account were checking to see already exists
	 * @param owner User who we are checking accounts
	 * @return True if account already exists by specified name, False if else
	 */
    public boolean doesAccountNameAlreadyExistForOwner(String accountName, String owner) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean doesExist = accountTable.doesAccountNameAlreadyExistForOwner(db, accountName, owner);
        db.close();
        return doesExist;
    }
	
	
	/**
	 * Updates account info by searching for name then inputing new data.
	 * 
	 * @param updatingAccount Account were searching for and looking to update
	 * @return True if account found and updated, else False
	 */
    public boolean updateAccountInfo(Account updatingAccount) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean updatedInfo =  accountTable.updateAccountInfo(db, updatingAccount);
        db.close();
        return updatedInfo;
    }
	
	/**
	 * Deletes specified account from database.
	 * 
	 * @param account Account to delete from database
	 * @return True if account was found and deleted, False if else
	 */
    public boolean deleteAccountFromDatabase(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean accountDeleted =  accountTable.deleteAccountFromDatabase(db, account);
        db.close();
        return accountDeleted;
    }
	
	/**
	 * Adds new transaction transaction to the database.
	 * 
	 * @param newTransaction Transaction to be added to database
	 */
    public void addNewTransactionToDatabase(Transaction newTransaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        transactionTable.addNewTransactionToDatabase(db, newTransaction);
        db.close();
    }
	
	/**
	 * Gets all transaction the belong to a specific user and specific account.
	 * 
	 * @param owner User we are looking for transactions for
	 * @param accountName Name of the account we want transactions for
	 * @return Collection of Transactions in the form of an ArrayList
	 */
    public ArrayList<Transaction> getTransactionsByOwnerAndAccountName(String owner, String accountName) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Transaction> ownerTransactions =  transactionTable.getTransactionsByOwnerAndAccountName(db, owner, accountName);
        db.close();
        return ownerTransactions;
    }
	
	/**
	 * Gets the withdrawals over all the accounts of a specific user.
	 * 
	 * @param user Username used to search for withdrawals
	 * @return Collection of Withdrawals in the form of an ArrayList
	 */
    public ArrayList<Withdrawal> getAllAccountWithdrawals(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Withdrawal> ownerWithdrawals = transactionTable.getAllAccountWithdrawals(db, user);
        db.close();
        return ownerWithdrawals;
    }
	
	/**
	 * Gets the deposits over all the accounts of a specific user.
	 * 
	 * @param user Username used to search for deposits
	 * @return Collection of Deposits in the form of an ArrayList
	 */
    public ArrayList<Deposit> getAllAccountDeposits(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Deposit> ownerDeposits = transactionTable.getAllAccountDeposits(db, user);
        db.close();
        return ownerDeposits;
    }

}
