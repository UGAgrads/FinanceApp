package com.UGAgrads.freddiefinance;

import java.util.ArrayList;

/**
 * This class is used as a data storage class for Users individual banking accounts.
 * 
 * @author UGAgrads
 */
public class Account {
	
    /** User the account belongs to. */
    private String accountOwner;
    /** Name of the account. */
    private String accountName;
	/** Type of account. */
    private String accountType;
	/** Account balance. */
    private String balance;
	/** Account interest rate. */
    private String interestRate;
	/** Collection of transactions linked to this account. */
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	/**
	 * Constructs a new Account object.
	 * 
	 * @param owner Owner of the account
	 * @param accountName Name of the account
	 * @param accountType Account type
	 * @param balance Account balance
	 * @param interestRate Account interest rate
	 */
    public Account(String owner, String accountName, String accountType, String balance, String interestRate) {
        this.accountOwner = owner;
        this.balance = balance;
        this.accountName = accountName;
        this.accountType = accountType;
        this.interestRate = interestRate;;
    }
	
	/**
	 * Gets account balance.
	 * 
	 * @return Account balance
	 */
    public String getBalance() {
        return CreateAccountPresenter.formatBalance(balance);
    }
	
	/**
	 * Sets the account balance.
	 * 
	 * @param value Amount to set account balance to
	 */
    public void setBalance(String value) {
        balance = value;
    }
	
	/**
	 * Gets account name.
	 * 
	 * @return Account name as string
	 */
    public String getAccountName() {
        return accountName;
    }
	
	/**
	 * Gets account owner.
	 * 
	 * @return Account owner as string
	 */
    public String getAccountOwner() {
        return accountOwner;
    }
	
	/**
	 * Gets account interest rate.
	 * 
	 * @return Account interest rate as string
	 */
    public String getInterestRate() {
        return interestRate;
    }
	
	/**
	 * Gets the accounts type.
	 * 
	 * @return Account type as string
	 */
    public String getAccountType() {
        return accountType;
    }
	
	/**
	 * Gets a collection of the transactions associated with the account.
	 * 
	 * @return Collection of transactions in an ArrayList
	 */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
	
	
}
