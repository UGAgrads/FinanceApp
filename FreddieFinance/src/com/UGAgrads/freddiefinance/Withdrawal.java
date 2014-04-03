package com.UGAgrads.freddiefinance;

/**
 * A type of transaction representing a withdrawal from the user's account.
 *
 * @author UGA Grads
 */
public class Withdrawal extends Transaction {
    /** 
     * The spending category which the user spent this money on.
     */
    private String moneyExpense;
    
    /**
    *
    * @param amountTransfered The amount that was withdrawn
    * @param dateEffective The date this withdrawal occurred
    * @param user The user who is doing the withdrawing
    * @param userAccount The user's account which is being withdrawn from
    * @param moneyExpenseString The spending category
    * @param withdrawalDescription A description of the withdrawal
    */
    public Withdrawal(double amountTransfered, String dateEffective, User user, Account userAccount, String moneyExpenseString, String withdrawalDescription) {
        super(amountTransfered, dateEffective, user, userAccount, withdrawalDescription);
        this.moneyExpense = moneyExpenseString;
    }
    
    /**
     * Returns the spending category (money expense)
     * 
     * @return Category for withdrawal
     */
    public String getSpendSourceInfo() {
        return getMoneyExpense();
    }
    
    /**
     * Returns the spending category
     * 
     *@return Category for withdrawal
     */
    public String getMoneyExpense() {
        return moneyExpense;
    }
    /**
     * The type of transaction this is
     * 
     * @return The class name, Withdrawal
     */
    public String transactionType() {
        return "Withdrawal";
    }
}
    