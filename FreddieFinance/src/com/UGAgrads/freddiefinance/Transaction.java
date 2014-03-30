package com.UGAgrads.freddiefinance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.annotation.SuppressLint;

/**
 * @author
 */
public abstract class Transaction {
	
    /**
	 * 
	 */
    double transactionAmount;
    
    /**
     * 
     */
    Date transactionDateEntered;
	
	/**
	 * 
	 */
    Date transactionDateEffective;
	
	/**
	 * 
	 */
    Account transactionUserAccount;
	
	/**
	 * 
	 */
    User transactionUser;
	
	/**
	 * 
	 */
    String transactionDescription;
	
	/**
	 * @param amountTransfered FILL THIS IN!
	 * @param dateEffectiveString  FILL THIS IN!
	 * @param user FILL THIS IN!
	 * @param userAccount FILL THIS IN!
	 * @param description FILL THIS IN!
	 */
    @SuppressLint("SimpleDateFormat")
    Transaction(double amountTransfered, String dateEffectiveString, User user, Account userAccount, String description) {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        this.transactionDateEntered = c.getTime();
        try {
            this.transactionDateEffective = df.parse(dateEffectiveString);
        } catch (ParseException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.transactionAmount = amountTransfered;
        this.transactionUser = user;
        this.transactionUserAccount = userAccount;
        this.transactionDescription = description;
    }
	
    /**
     * @return FILL THIS IN!
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }
	
    /**
     * @return FILL THIS IN!
     */
    public User getTransactionUser() {
        return transactionUser;
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public String getTransactionUsername() {
        return transactionUser.getUsername();
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public Account getTransactionUserAccount() {
        return transactionUserAccount;
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public String getTransactionAccountName() {
        return transactionUserAccount.getAccountName();
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public Date getTransactionDateEntered() {
        return transactionDateEntered;
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public Date getTransactionDateEffective() {
        return transactionDateEffective;
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public String getTransactionDescription() {
        return transactionDescription;
    }

	/**
	 * @return FILL THIS IN!
	 */
    public String transactionType() {
        return "Transaction";
    }

	/**
	 * @return FILL THIS IN!
	 */
    public String getSpendSourceInfo() {
        return "";
    }

}
