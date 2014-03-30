package com.UGAgrads.freddiefinance;

/**
 * @author
 */
public class Deposit extends Transaction {
	
    /** FILL THIS IN! */
    private String spendSourceInfo;
	
	/**
	 * @param amountTransfered FILL THIS IN!
	 * @param dateEffective FILL THIS IN!
	 * @param user FILL THIS IN!
	 * @param userAccount FILL THIS IN!
	 * @param moneySource FILL THIS IN!
	 * @param depositDescription FILL THIS IN!
	 */
    Deposit(double amountTransfered, String dateEffective, User user, Account userAccount, String moneySource, String depositDescription) {
		super(amountTransfered, dateEffective, user, userAccount, depositDescription);
        this.spendSourceInfo = moneySource;
    }
	
	/**
	 * @return FILL THIS IN!
	 */
    public String getSpendSourceInfo() {
        return spendSourceInfo;
    }

	/**
	 * @return  FILL THIS IN!
	 */
    public String transactionType() {
        return "Deposit";
    }
}
