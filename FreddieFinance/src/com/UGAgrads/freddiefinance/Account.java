package com.UGAgrads.freddiefinance;

import java.util.ArrayList;

/**
 * @author
 */
public class Account {
	
    /** FILL THIS IN! */
    private String accountOwner;
    /** FILL THIS IN! */
    private String accountName;
	/** FILL THIS IN! */
    private String accountType;
	/** FILL THIS IN! */
    private String balance;
	/** FILL THIS IN! */
    private String interestRate;
	/** FILL THIS IN! */
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	/**
	 * @param owner FILL THIS IN!
	 * @param accountName FILL THIS IN!
	 * @param accountType FILL THIS IN!
	 * @param balance FILL THIS IN!
	 * @param interestRate FILL THIS IN!
	 */
    Account(String owner, String accountName, String accountType, String balance, String interestRate) {
        this.accountOwner = owner;
        this.balance = balance;
        this.accountName = accountName;
        this.accountType = accountType;
        this.interestRate = interestRate;;
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public String getBalance() {
        return CreateAccountPresenter.formatBalance(balance);
    }
	
	/**
	 * @param value FILL THIS IN!
	 */
    public void setBalance(String value) {
        balance = value;
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public String getAccountName() {
        return accountName;
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public String getAccountOwner() {
        return accountOwner;
    }
	
	/**
	 * 
	 * @return FILL THIS IN!
	 */
    public String getInterestRate() {
        return interestRate;
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public String getAccountType() {
        return accountType;
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
	
	
}
