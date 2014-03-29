package com.UGAgrads.freddiefinance;

/**
* @author
*/
public class Withdrawal extends Transaction {
    /**
    *
    */
    private String moneyExpense;
    
    /**
    *
    * @param amountTransfered FILL THIS IN!!
    * @param dateEffective FILL THIS IN!!
    * @param user FILL THIS IN!!
    * @param userAccount FILL THIS IN!!
    * @param moneyExpenseString FILL THIS IN!!
    * @param withdrawalDescription FILL THIS IN!!
    */
    public Withdrawal(double amountTransfered, String dateEffective, User user, Account userAccount, String moneyExpenseString, String withdrawalDescription) {
        super(amountTransfered, dateEffective, user, userAccount, withdrawalDescription);
        this.moneyExpense = moneyExpenseString;
    }
    
    /**
    * @return FILL THIS IN!!
    */
    public String getSpendSourceInfo() {
        return getMoneyExpense();
    }
    
    /**
     *@return FILL THIS IN!!
     */
    public String getMoneyExpense() {
        return moneyExpense;
    }
    /**
    * @return FILL THIS IN!!
    */
    public String transactionType() {
        return "Withdrawal";
    }
}
    