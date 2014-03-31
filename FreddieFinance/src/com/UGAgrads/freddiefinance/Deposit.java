package com.UGAgrads.freddiefinance;

/**
 * Type of transaction in which an accounts balance was increased.
 * 
 * @author UGAgrads
 */
public class Deposit extends Transaction {
	
    /** Specifies how the money was acquired. */
    private String spendSourceInfo;
	
	/**
	 * Constructor for a Deposit transaction.
	 * 
	 * @param amountTransfered Ammount of money acquired
	 * @param dateEffective Date the transaction will take effect
	 * @param user User that the transaction will take effect for
	 * @param userAccount Account the transaction will take effect for
	 * @param moneySource Source where the money came from
	 * @param depositDescription Any other details about the transaction
	 */
    Deposit(double amountTransfered, String dateEffective, User user, Account userAccount, String moneySource, String depositDescription) {
		super(amountTransfered, dateEffective, user, userAccount, depositDescription);
        this.spendSourceInfo = moneySource;
    }
	
	/**
	 * Gets the reason for the transaction.
	 * 
	 * @return How the money was acquired
	 */
    public String getSpendSourceInfo() {
        return spendSourceInfo;
    }

	/**
	 * Returns the transaction type.
	 * 
	 * @return  Deposit
	 */
    public String transactionType() {
        return "Deposit";
    }
}
