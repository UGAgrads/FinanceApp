package com.UGAgrads.freddiefinance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.annotation.SuppressLint;

/**
 * @author UGAGrads
 */
public abstract class Transaction {
	
    /**
	 * amount for the transaction
	 */
    double transactionAmount;
    
    /**
     * date the transaction was entered
     */
    Date transactionDateEntered;
	
	/**
	 * date the transaction is supposed to occur
	 */
    Date transactionDateEffective;
	
	/**
	 * user account associated with the transaction
	 */
    Account transactionUserAccount;
	
	/**
	 * user associated with the transaction
	 */
    User transactionUser;
	
	/**
	 * description of the transaction given by the user
	 */
    String transactionDescription;
	
	/**
	 * @param amountTransfered money amount in the transaction
	 * @param dateEffectiveString string for when the 
	 * @param user the user associated with the transaction
	 * @param userAccount user account associated with the transaction
	 * @param description description of transaction given by user
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
     * @return amount transfered in transaction
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }
	
    /**
     * @return User associated with transaction
     */
    public User getTransactionUser() {
        return transactionUser;
    }
	
	/**
	 * @return username of user associated with the transaction
	 */
    public String getTransactionUsername() {
        return transactionUser.getUsername();
    }
	
	/**
	 * @return user account associated with the transaction
	 */
    public Account getTransactionUserAccount() {
        return transactionUserAccount;
    }
	
	/**
	 * @return name of account associated with the transaction
	 */
    public String getTransactionAccountName() {
        return transactionUserAccount.getAccountName();
    }
	
	/**
	 * @return date transaction was entered
	 */
    public Date getTransactionDateEntered() {
        return transactionDateEntered;
    }
	
	/**
	 * @return date transaction transpired
	 */
    public Date getTransactionDateEffective() {
        return transactionDateEffective;
    }
	
	/**
	 * @return description of the transaction
	 */
    public String getTransactionDescription() {
        return transactionDescription;
    }

	/**
	 * @return type of transaction
	 */
    public String transactionType() {
        return "Transaction";
    }

	/**
	 * @return info about the spending source entered by users
	 */
    public String getSpendSourceInfo() {
        return "";
    }

}
